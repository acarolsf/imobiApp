package br.com.abjdesenvolvimentos.imobiapp.dominio;

import android.content.ContentValues;

public class Imoveis {
    private int id;
    private String descricao;
    private double preco;
    private String cidade;
    private int qnt_quartos;
    private int qnt_banheiros;
    private int qnt_comodos;
    private String tipo;
    private String status;
    private Corretora corretora;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getQnt_quartos() {
        return qnt_quartos;
    }

    public void setQnt_quartos(int qnt_quartos) {
        this.qnt_quartos = qnt_quartos;
    }

    public int getQnt_banheiros() {
        return qnt_banheiros;
    }

    public void setQnt_banheiros(int qnt_banheiros) {
        this.qnt_banheiros = qnt_banheiros;
    }

    public int getQnt_comodos() {
        return qnt_comodos;
    }

    public void setQnt_comodos(int qnt_comodos) {
        this.qnt_comodos = qnt_comodos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Corretora getCorretora() {
        return corretora;
    }

    public void setCorretora(Corretora corretora) {
        this.corretora = corretora;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();

        cv.put("descricao", this.descricao);
        cv.put("preco", this.preco);
        cv.put("cidade", this.cidade);
        cv.put("quartos", this.qnt_quartos);
        cv.put("comodos", this.qnt_comodos);
        cv.put("banheiros", this.qnt_banheiros);
        cv.put("tipo", this.tipo);
        cv.put("status", this.status);
        cv.put("corretora", String.valueOf(this.corretora));

        return cv;
    }
}
