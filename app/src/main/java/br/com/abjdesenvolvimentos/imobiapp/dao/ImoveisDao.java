package br.com.abjdesenvolvimentos.imobiapp.dao;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;

import br.com.abjdesenvolvimentos.imobiapp.banco.DBHelper;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Imoveis;

public class ImoveisDao extends SQLException{

    private DBHelper db;
    private SQLiteDatabase dbInstancia = null; // instancia que permite fazer os select, delete, updat

    public void salvar(Imoveis imoveis) throws SQLException {
        // toda função sera preciso colocar o throws SQLException

        // sempre abrir e fechar o banco
        db.abrirDB();

        // todas as classes precisam ter o content value para que haja contato com o banco
        dbInstancia.insert("imoveis", null, imoveis.getContentValues());

        // Toast.makeText(ListarActivity.class, imoveis.getDescricao() + "cadastrado", Toast.LENGTH_LONG).show();

        db.fecharDB();
    }


    public ArrayList<String> listar() {

        ArrayList<String> imoveis = new ArrayList<String>();
        SQLiteDatabase meuBanco = db.getReadableDatabase();

        Cursor minhaConsulta = meuBanco.rawQuery("SELECT descricao FROM imoveis ORDER BY preco", null);

        minhaConsulta.moveToFirst();

        while (! minhaConsulta.isAfterLast()) {
            imoveis.add(minhaConsulta.getString(0));
            minhaConsulta.moveToNext();
        }
        meuBanco.close(); // fechar o banco
        minhaConsulta.close(); //fechar a consulta
        return imoveis; // retornar o array list
    }



}
