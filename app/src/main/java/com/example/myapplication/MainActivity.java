package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    EditText editCodigo, editNome, editData, editDescricao;
    Button btnSalvar, btnExcluir, btnLimpar;
    ListView ListViewLembretes;
    BancoDados db = new BancoDados(this);
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editNome = (EditText) findViewById(R.id.editNome);
        editData = (EditText) findViewById(R.id.editData);
        editDescricao = (EditText) findViewById(R.id.editDescricao);

        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);

        ListViewLembretes = (ListView) findViewById(R.id.ListViewLembretes);
        listarLembretes();

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpacampo();

            }
        });


        ListViewLembretes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String conteudo = (String) ListViewLembretes.getItemAtPosition(position);
                makeText(MainActivity.this, "Selecionado: " + conteudo, Toast.LENGTH_LONG).show();

            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = editCodigo.getText().toString();
                String nome = editNome.getText().toString();
                String data = editData.getText().toString();
                String descricao = editDescricao.getText().toString();

                if (nome.isEmpty()) {

                } else if (codigo.isEmpty()) {
                    // Aqui o problema é que Lembrete está recebendo um inteiro e você tenta passar uma String


                    db.addLembrete(new Lembrete(nome, data, descricao));
//                    Toast.makeText() Você duplicou o comando.
                    Toast.makeText(MainActivity.this, "Salvo com sucesso", Toast.LENGTH_LONG).show();

                    limpacampo();
                    listarLembretes();

                } else {
                    // Mudanças aqui
//                    SimpleDateFormat stf = new SimpleDateFormat(data);

//                        Date d = stf.parse(String.valueOf(stf));
//                        Log.e("FGV", d.toString());
                    db.atualizalembrete(new Lembrete(Integer.parseInt(codigo), nome, data, descricao));
                    Toast.makeText(MainActivity.this, "Atualizado com sucesso", Toast.LENGTH_LONG).show();
                    limpacampo();
                    listarLembretes();

                }


            }


        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = editCodigo.getText().toString();
                if (codigo.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Nenhum lembrete selecionado", Toast.LENGTH_LONG).show();
                } else {
                    Lembrete lembrete = new Lembrete();
                    lembrete.setCodigo(Integer.parseInt(codigo));
                    db.apagarlembrete(lembrete);

                    Toast.makeText(MainActivity.this, "Excluido com sucesso", Toast.LENGTH_LONG).show();
                    limpacampo();
                    listarLembretes();
                }
            }
        });


//        Lembrete lembrete = db.selecionarlembrete(0);

//        Log.d("Lembrete selecionado", "Codigo: " + lembrete.getCodigo() + " Nome:" + lembrete.getNome()
//                + " Data:" + lembrete.getData() + "Descrição: " + lembrete.getDescricao());

    }

    private Date convertToDate(String data) {
        SimpleDateFormat stf = new SimpleDateFormat(data);
        Date d = new Date();
        try {
            d = stf.parse(String.valueOf(stf));
            return d;
        } catch (ParseException e) {
            Log.e("FGV", "Falha ao converter string to Date");
            e.printStackTrace();

        }
        return d;

    }

    void limpacampo() {
        editCodigo.setText("");
        editNome.setText("");
        editData.setText("");
        editDescricao.setText("");

        editNome.requestFocus();
    }

    public void listarLembretes() {
        List<Lembrete> lembretes = db.listartodoslembretes();

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
        ListViewLembretes.setAdapter(adapter);

        for (Lembrete c : lembretes) {
            //Log.d("Lista" , "\nID: " + c.getCodigo() + " Nome: " + c.getNome());
            arrayList.add(c.getCodigo() + "-" + c.getNome());
            adapter.notifyDataSetChanged();
        }


    }


}
