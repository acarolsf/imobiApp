package br.com.abjdesenvolvimentos.imobiapp.dao;

import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.HashMap;

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

    public ArrayList<HashMap<String, String>> listar() throws SQLException {
        ArrayList<HashMap<String, String>> imoveisList = new ArrayList<HashMap<String, String>>();
        try {
            db.abrirDB();

            SQLiteDatabase meuBanco = db.getReadableDatabase();

            String query = "SELECT descricao, preco, cidade, quartos, banheiros, comodos, tipo," +
                    "status, corretora, telefone, imagem FROM imoveis ORDER BY nome";
            Cursor cursor = meuBanco.rawQuery(query, null);

            while (cursor.moveToNext()){
                HashMap<String,String> imoveis = new HashMap<>();
                imoveis.put("descricao",cursor.getString(cursor.getColumnIndex("descricao")));
                imoveis.put("preco", cursor.getString(cursor.getColumnIndex("preco")));
                imoveis.put("cidade",cursor.getString(cursor.getColumnIndex("cidade")));
                imoveis.put("quartos",cursor.getString(cursor.getColumnIndex("quartos")));
                imoveis.put("banheiros", cursor.getString(cursor.getColumnIndex("banheiros")));
                imoveis.put("comodos", cursor.getString(cursor.getColumnIndex("comodos")));
                imoveis.put("tipo", cursor.getString(cursor.getColumnIndex("tipo")));
                imoveis.put("status", cursor.getString(cursor.getColumnIndex("status")));
                imoveis.put("corretora", cursor.getString(cursor.getColumnIndex("corretora")));
                imoveis.put("telefone", cursor.getString(cursor.getColumnIndex("telefone")));
                imoveis.put("imagem", cursor.getString(cursor.getColumnIndex("imagem")));

                imoveisList.add(imoveis);
            }

            db.fecharDB();

        }catch (Exception e) {
            e.printStackTrace();
        }
        return imoveisList;
    }

    public void delete(int id) throws SQLException {

        try {
            db.abrirDB();

            // SQLiteDatabase meuBanco = db.getReadableDatabase();
            dbInstancia.delete("imoveis", id+"= ?", new String[]{String.valueOf(id)});

            db.fecharDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Imoveis imoveis) throws SQLException {

        try {
            db.abrirDB();

            dbInstancia.update("imoveis", imoveis.getContentValues(),
                    imoveis.getId()+"=?", new String[]{String.valueOf(imoveis.getId())});

            db.fecharDB();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    public ArrayList<String> listar() throws SQLException {
//
//
//        ArrayList<String> imoveis = null;
//        SQLiteDatabase meuBanco = db.getReadableDatabase();
//
//        Cursor minhaConsulta = meuBanco.rawQuery("SELECT descricao FROM imoveis ORDER BY preco", null);
//
//        minhaConsulta.moveToFirst();
//
//        while (! minhaConsulta.isAfterLast()) {
//            imoveis.add(minhaConsulta.getString(0));
//            minhaConsulta.moveToNext();
//        }
//        meuBanco.close(); // fechar o banco
//        minhaConsulta.close(); //fechar a consulta
//        return imoveis; // retornar o array list
//    }



}
