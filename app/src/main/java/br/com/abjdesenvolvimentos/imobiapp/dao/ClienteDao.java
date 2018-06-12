package br.com.abjdesenvolvimentos.imobiapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.abjdesenvolvimentos.imobiapp.banco.DBHelper;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Cliente;

public class ClienteDao {

    private Context context;
    private DBHelper db = new DBHelper(context);
    private SQLiteDatabase dbInstancia = null;

    public void salvar(Cliente cliente) throws SQLException {
        // toda função sera preciso colocar o throws SQLException

        // sempre abrir e fechar o banco
        db.abrirDB();

        // todas as classes precisam ter o content value para que haja contato com o banco
        dbInstancia.insert("clientes", null, cliente.getContentValues());

        Toast.makeText(context, cliente.getNome() + "cadastrado", Toast.LENGTH_LONG).show();

        db.fecharDB();
    }

    public ArrayList<String> listar () throws SQLException {
        ArrayList<String> contatosList = new ArrayList<String>();
        SQLiteDatabase meuBanco = db.getReadableDatabase();

        Cursor minhaConsulta = meuBanco.rawQuery("SELECT cpf, nome FROM clientes ORDER BY nome", null);

        minhaConsulta.moveToFirst();

        while (! minhaConsulta.isAfterLast()) {
            contatosList.add(minhaConsulta.getString(0));
            minhaConsulta.moveToNext();
        }
        meuBanco.close(); // fechar o banco
        minhaConsulta.close(); //fechar a consulta

        return contatosList; // retornar o array list
    }

    public void alterar (Cliente cliente) throws SQLException {

    }

    public void deletar (Cliente cliente) throws SQLException {


    }
}
