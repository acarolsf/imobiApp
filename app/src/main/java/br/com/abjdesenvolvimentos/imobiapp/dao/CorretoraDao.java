package br.com.abjdesenvolvimentos.imobiapp.dao;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import br.com.abjdesenvolvimentos.imobiapp.banco.DBHelper;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Corretora;

public class CorretoraDao extends SQLException {

    private DBHelper db;
    private SQLiteDatabase dbInstancia = null; // instancia que permite fazer os select, delete, updat

    public void salvar(Corretora corretora) throws SQLException {
        // toda função sera preciso colocar o throws SQLException

        // sempre abrir e fechar o banco
        db.abrirDB();

        // todas as classes precisam ter o content value para que haja contato com o banco
        dbInstancia.insert("corretoras", null, corretora.getContentValues());

        db.fecharDB();
    }


    public ArrayList<String> listar() {
        ArrayList<String> corretoras = new ArrayList<String>();
        SQLiteDatabase meuBanco = db.getReadableDatabase();

        Cursor minhaConsulta = meuBanco.rawQuery("SELECT nome FROM corretoras ORDER BY nome", null);

        minhaConsulta.moveToFirst();

        while (! minhaConsulta.isAfterLast()) {
            corretoras.add(minhaConsulta.getString(0));
            minhaConsulta.moveToNext();
        }
        meuBanco.close(); // fechar o banco
        minhaConsulta.close(); //fechar a consulta
        return corretoras; // retornar o array list
    }
}
