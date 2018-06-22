package br.com.abjdesenvolvimentos.imobiapp.dao;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.abjdesenvolvimentos.imobiapp.CadastroCorretoraActivity;
import br.com.abjdesenvolvimentos.imobiapp.banco.DBHelper;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Corretora;

public class CorretoraDao extends SQLException {

    private DBHelper db;
    private SQLiteDatabase dbInstancia = null; // instancia que permite fazer os select, delete, updat

    // salvar no banco

    public void salvar(Corretora corretora) throws SQLException {
        // toda função sera preciso colocar o throws SQLException

        // sempre abrir e fechar o banco
        db.abrirDB();

        // todas as classes precisam ter o content value para que haja contato com o banco
        dbInstancia.insert("corretoras", null, corretora.getContentValues());

        db.fecharDB();
    }

    public ArrayList<String> listar() throws SQLException {
        try {
            db.abrirDB();

            ArrayList<String> corretoraList = new ArrayList<String>();
            SQLiteDatabase meuBanco = db.getReadableDatabase();

            String query = "SELECT nome, telefone, email, imagem FROM corretoras ORDER BY nome";
            Cursor cursor = meuBanco.rawQuery(query, null);

            cursor.moveToFirst();
            while (! cursor.isAfterLast()) {
                corretoraList.add(cursor.getString(0));
                cursor.moveToNext();
            }

            meuBanco.close();
            cursor.close();
//            while (cursor.moveToNext()){
//                HashMap<String,String> corretora = new HashMap<>();
//                corretora.put("nome",cursor.getString(cursor.getColumnIndex("nome")));
//                corretora.put("cidade", cursor.getString(cursor.getColumnIndex("cidade")));
//                corretora.put("telefone",cursor.getString(cursor.getColumnIndex("telefone")));
//                corretora.put("email",cursor.getString(cursor.getColumnIndex("email")));
//                corretora.put("imagem", cursor.getString(cursor.getColumnIndex("imagem")));
//                corretoraList.add(corretora);
//            }

            db.fecharDB();
            return corretoraList;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(int id) throws SQLException {
        db.abrirDB();

        // SQLiteDatabase meuBanco = db.getReadableDatabase();
        dbInstancia.delete("corretora", id+"= ?", new String[]{String.valueOf(id)});

        db.fecharDB();
    }

    public void update(Corretora corretora) throws SQLException {
        db.abrirDB();

        dbInstancia.update("corretoras", corretora.getContentValues(), corretora.getId()+"=?", new String[]{String.valueOf(corretora.getId())});

        db.fecharDB();
    }



//    public ArrayList<String> listar() throws SQLException {
//        ArrayList<String> corretoras = new ArrayList<String>();
//        SQLiteDatabase meuBanco = db.getReadableDatabase();
//
//        Cursor minhaConsulta = meuBanco.rawQuery("SELECT nome FROM corretoras ORDER BY nome", null);
//
//        minhaConsulta.moveToFirst();
//
//        while (! minhaConsulta.isAfterLast()) {
//            corretoras.add(minhaConsulta.getString(0));
//            minhaConsulta.moveToNext();
//        }
//        meuBanco.close(); // fechar o banco
//        minhaConsulta.close(); //fechar a consulta
//        return corretoras; // retornar o array list
//    }
}
