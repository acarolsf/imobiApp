package br.com.abjdesenvolvimentos.imobiapp.dominio;

import android.content.ContentValues;

public class Usuario {
    private int id;
    private String login;
    private String senha;

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

    public ContentValues getContentValues() {
        ContentValues cv = new ContentValues();

        cv.put("login", this.login);
        cv.put("senha", this.senha);

        return cv;
    }
}
