package com.example.myapplication;

import java.util.Date;

public class Lembrete {

    int codigo;
    String nome;
    Date data;
    String descricao;

    public Lembrete() {

    }

    public Lembrete(int _codigo, String _nome, Date _data, String _descricao) {
        this.codigo = _codigo;
        this.nome = _nome;
        this.data = _data;
        this.descricao = _descricao;
    }

    public Lembrete(int _nome, String _data, String _descricao) {
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;

    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}