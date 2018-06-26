package br.com.abjdesenvolvimentos.imobiapp.banco;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.abjdesenvolvimentos.imobiapp.dominio.Imoveis;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Usuario;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "db_imobiapp.db";
    private static final int VERSAO_BANCO = 4;
    private Context context;
    private SQLiteDatabase dbInstancia = null;

    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // tabela de usuarios
        db.execSQL("CREATE TABLE usuarios(id INTERGER PRIMARY KEY AUTOINCREMENT, login TEXT, senha TEXT, " +
                "email TEXT)");

        // tabela de imoveis
        db.execSQL("CREATE TABLE imoveis(id INTERGER PRIMARY KEY AUTOINCREMENT, descricao TEXT, " +
                "preco DOUBLE, cidade TEXT, quartos INTERGER, comodos INTERGER, banheiros INTERGER, " +
                "tipo TEXT, status TEXT, corretora TEXT, telefone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS usuarios");
        db.execSQL("DROP TABLE IF EXISTS imoveis");
        onCreate(db);
    }

    public void abrirDB() throws SQLException {

        // verifica se o banco está aberto
        if (this.dbInstancia == null) {
            this.dbInstancia = this.getWritableDatabase(); // só consegue salvar no banco se abrir para escrita
        }

    }
    public void fecharDB() throws SQLException {

        if (this.dbInstancia != null) {
            if(this.dbInstancia.isOpen()) {
                this.dbInstancia.close();
            }
        }
    }
    public void salvarUsuario(Usuario usuario) throws SQLException {
        // toda função sera preciso colocar o throws SQLException

        // sempre abrir e fechar o banco
        abrirDB();
        // dbInstancia = db.getWritableDatabase();

        // todas as classes precisam ter o content value para que haja contato com o banco
        dbInstancia.insertOrThrow("usuarios", null, usuario.getContentValues());

        // db.close();
        fecharDB();
    }

    public void salvarImoveis(Imoveis imoveis) throws SQLException {
        // toda função sera preciso colocar o throws SQLException

        // sempre abrir e fechar o banco
        abrirDB();

        // todas as classes precisam ter o content value para que haja contato com o banco
        dbInstancia.insertOrThrow("imoveis", null, imoveis.getContentValues());

        // Toast.makeText(ListarActivity.class, imoveis.getDescricao() + "cadastrado", Toast.LENGTH_LONG).show();

        fecharDB();
    }

    public ArrayList<HashMap<String, String>> listarImoveis() throws SQLException {
        ArrayList<HashMap<String, String>> imoveisList = new ArrayList<HashMap<String, String>>();
        try {
            abrirDB();

            SQLiteDatabase meuBanco = this.getReadableDatabase();

            String query = "SELECT descricao, preco, cidade, quartos, banheiros, comodos, tipo," +
                    "status, corretora, telefone FROM imoveis ORDER BY nome";
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

                imoveisList.add(imoveis);
            }

            fecharDB();

        }catch (Exception e) {
            e.printStackTrace();
        }
        return imoveisList;
    }

    public void deleteImoveis(int id) throws SQLException {

        try {
            abrirDB();

            // SQLiteDatabase meuBanco = db.getReadableDatabase();
            dbInstancia.delete("imoveis", id+"= ?", new String[]{String.valueOf(id)});

            fecharDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateImoveis(Imoveis imoveis) throws SQLException {

        try {
            abrirDB();

            dbInstancia.update("imoveis", imoveis.getContentValues(),
                    imoveis.getId()+"=?", new String[]{String.valueOf(imoveis.getId())});

            fecharDB();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Imoveis> listar() throws SQLException {

        ArrayList<Imoveis> imoveis = new ArrayList<Imoveis>();
        SQLiteDatabase meuBanco = this.getReadableDatabase();

        Cursor c = meuBanco.rawQuery("SELECT * FROM imoveis ORDER BY preco", null);

        if(c.moveToFirst()) {
            do {

                Imoveis im = new Imoveis();
                im.setDescricao(c.getString(c.getColumnIndex("descricao")));
                im.setPreco(c.getDouble(c.getColumnIndex("preco")));
                im.setCidade(c.getString(c.getColumnIndex("cidade")));
                im.setQuartos(c.getInt(c.getColumnIndex("quartos")));
                im.setBanheiros(c.getInt(c.getColumnIndex("banheiros")));
                im.setComodos(c.getInt(c.getColumnIndex("comodos")));
                im.setTipo(c.getString(c.getColumnIndex("tipo")));
                im.setStatus(c.getString(c.getColumnIndex("status")));
                im.setCorretora(c.getString(c.getColumnIndex("corretora")));
                im.setTelefone(c.getString(c.getColumnIndex("telefone")));
                imoveis.add(im);
            } while (c.moveToNext());
        }


//        while (! minhaConsulta.isAfterLast()) {
//            imoveis.set(minhaConsulta.getString(0, ));
//            minhaConsulta.moveToNext();
//        }
        meuBanco.close(); // fechar o banco
        c.close(); //fechar a consulta
        return imoveis; // retornar o array list
    }

}
