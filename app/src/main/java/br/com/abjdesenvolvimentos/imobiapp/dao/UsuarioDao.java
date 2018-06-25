package br.com.abjdesenvolvimentos.imobiapp.dao;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import br.com.abjdesenvolvimentos.imobiapp.banco.DBHelper;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Usuario;

public class UsuarioDao extends SQLException {

    private DBHelper db;
    private SQLiteDatabase dbInstancia = null; // instancia que permite fazer os select, delete, updat

    public void salvar(Usuario usuario) throws SQLException {
        // toda função sera preciso colocar o throws SQLException

        // sempre abrir e fechar o banco
        //db.abrirDB();
        dbInstancia = db.getWritableDatabase();

        // todas as classes precisam ter o content value para que haja contato com o banco
        dbInstancia.insertOrThrow("usuarios", null, usuario.getContentValues());

        db.close();
        //db.fecharDB();
    }
}
