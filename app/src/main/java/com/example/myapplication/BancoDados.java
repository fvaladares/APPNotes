package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_LEMBRETE = "bd_lembretes";

    private static final String TABELA_LEMBRETE = "tb_lembretes";

    private static final String COLUNA_CODIGO = "codigo";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_DATA = "bd_lembretes";
    private static final String COLUNA_DESCRICAO = "descricao";
    private SQLiteDatabase db;

    public BancoDados(MainActivity context) {

        super(context, BANCO_LEMBRETE, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_COLUNA = "CREATE TABLE " + TABELA_LEMBRETE + "("
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY, " + COLUNA_NOME + " TEXT, "
                + COLUNA_DATA + " TEXT, " + COLUNA_DESCRICAO + " TEXT )";

        db.execSQL(QUERY_COLUNA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void addLembrete(Lembrete lembrete) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, lembrete.getNome());
        /* values.put(COLUNA_DATA, lembrete.getData()); não conseguir informar com tipo data */
        values.put(COLUNA_DESCRICAO, lembrete.getDescricao());

        db.insert(TABELA_LEMBRETE, null, values);
        db.close();
    }

    void apagarlembrete(Lembrete lembrete) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_LEMBRETE, COLUNA_CODIGO + " =  ?", new String[]{String.valueOf(lembrete.getCodigo())});
        db.close();
    }

    Lembrete selecionarlembrete(int codigo) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_LEMBRETE, new String[]{COLUNA_CODIGO, COLUNA_DATA,
                        COLUNA_NOME, COLUNA_DESCRICAO}, COLUNA_CODIGO + " = ? ",
                new String[]{String.valueOf(codigo)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Lembrete lembrete = new Lembrete(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return lembrete;

    }

    public void atualizalembrete(Lembrete lembrete) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, lembrete.getNome());
        /* values.put(COLUNA_DATA, lembrete.getData()); não conseguir informar com tipo data */
        values.put(COLUNA_DESCRICAO, lembrete.getDescricao());

        db.update(TABELA_LEMBRETE, values, COLUNA_CODIGO + " = ?",
                new String[]{String.valueOf(lembrete.getCodigo())});

    }

    public List<Lembrete> listartodoslembretes() {
        List<Lembrete> lembreteList = new ArrayList<Lembrete>();
        String query = " SELECT * FROM " + TABELA_LEMBRETE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                Lembrete lembrete;
                lembrete = new Lembrete();
                lembrete.setCodigo(Integer.parseInt(c.getString(0)));
                lembrete.setNome(c.getString(1));
                //lembrete.setData(c.getString(2));
                lembrete.setDescricao(c.getString(2));

                lembreteList.add(lembrete);

            }
            while (c.moveToNext());
        }
        return lembreteList;
    }

}

