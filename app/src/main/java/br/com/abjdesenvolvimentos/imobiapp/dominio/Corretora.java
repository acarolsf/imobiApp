package br.com.abjdesenvolvimentos.imobiapp.dominio;

import android.content.ContentValues;

public class Corretora {
    private int id;
    private String nome;
    private String cidade;
    private String telefone;
    private int cnpj;
    private String email;
    private byte[] foto;

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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getCnpj() {
        return cnpj;
    }

    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();

        cv.put("nome", this.nome);
        cv.put("cidade", this.cidade);
        cv.put("telefone", this.telefone);
        cv.put("cnpj", this.cnpj);
        cv.put("email", this.email);
        cv.put("imagem", this.foto);

        return cv;
    }
}
