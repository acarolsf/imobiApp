package br.com.abjdesenvolvimentos.imobiapp.dominio;

import android.content.ContentValues;

public class Usuario {
    private int id;
    private String login;
    private String senha;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();

        cv.put("login", this.login);
        cv.put("senha", this.senha);
        cv.put("email", this.email);

        return cv;
    }
}
