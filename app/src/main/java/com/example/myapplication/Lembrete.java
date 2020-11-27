package com.example.myapplication;

public class Lembrete {

    int codigo;
    String nome;
    String data;
    String descricao;

    public Lembrete() {

    }

    public Lembrete(int _codigo, String _nome, String _data, String _descricao) {
        this.codigo = _codigo;
        this.nome = _nome;
        this.data = _data;
        this.descricao = _descricao;
    }

//    public Lembrete(int _nome, String _data, String _descricao) {

    public Lembrete(String _nome, String _data, String _descricao) {
        // Aqui você precisa usar os parâmetros da função.
        this.nome = _nome;
        this.data = _data;
        this.descricao = _descricao;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}