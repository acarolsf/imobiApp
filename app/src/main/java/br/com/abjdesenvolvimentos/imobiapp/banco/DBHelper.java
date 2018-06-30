package br.com.abjdesenvolvimentos.imobiapp.banco;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.com.abjdesenvolvimentos.imobiapp.dominio.Imoveis;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "db_imobiapp.db";
    private static final int VERSAO_BANCO = 10;
    private Context context;
    private SQLiteDatabase dbInstancia = null;

    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        // tabela de usuarios
//        db.execSQL("CREATE TABLE IF NOT EXISTS usuarios(id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, senha TEXT, " +
//                "email TEXT)");

        // tabela de imoveis
        db.execSQL("CREATE TABLE imoveis(id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", descricao TEXT, " +
                "preco DOUBLE, cidade TEXT, quartos INTERGER, comodos INTERGER, banheiros INTERGER, " +
                "tipo TEXT, status TEXT, corretora TEXT, telefone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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
    public void salvarImoveis(Imoveis imoveis) throws SQLException {
        // toda função sera preciso colocar o throws SQLException

        abrirDB();

        // todas as classes precisam ter o content value para que haja contato com o banco
        dbInstancia.insertOrThrow("imoveis", null, imoveis.getContentValues());

        //fecharDB();, não estou fechando pois deu erro no re-open, então no final da aplicação, portanto,
        // quando eu pergunto se o usuário quer fechar o arquivo, eu fecho o banco.
    }


    public void deleteImoveis(int id) throws SQLException {

            abrirDB();

            dbInstancia.execSQL("DELETE FROM imoveis WHERE id=" + id );

    }

    public void updateImoveis(Imoveis imoveis) throws SQLException {

            abrirDB();
            dbInstancia.update("imoveis", imoveis.getContentValues(),
                    imoveis.getId()+"=?", new String[]{String.valueOf(imoveis.getId())});

    }


    public ArrayList<Imoveis> listar() throws SQLException {

        ArrayList<Imoveis> imoveis = new ArrayList<Imoveis>();
        SQLiteDatabase meuBanco = this.getReadableDatabase();

        Cursor c = meuBanco.rawQuery("SELECT * FROM imoveis ORDER BY preco", null);

        c.moveToFirst();
        while(!c.isAfterLast()) {

            Imoveis im = new Imoveis();
            im.setId(c.getInt(c.getColumnIndex("id")));
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

            c.moveToNext();
        }


        meuBanco.close(); // fechar o banco
        c.close(); //fechar a consulta
        return imoveis; // retornar o array list
    }

}
