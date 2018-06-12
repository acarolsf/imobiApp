package br.com.abjdesenvolvimentos.imobiapp.dominio;

import android.content.ContentValues;

public class Cliente {
    private int id;
    private String nome;
    private String endereco;
    private String cidade;
    private String cpf;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();

        cv.put("nome", this.nome);
        cv.put("endereco", this.endereco);
        cv.put("cidade", this.cidade);
        cv.put("cpf", this.cpf);
        cv.put("email", this.email);

        return cv;
    }
}
