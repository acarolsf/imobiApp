package br.com.abjdesenvolvimentos.imobiapp.banco;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

        DBHelper db = new DBHelper(context);
        // verifica se o banco está aberto
        if (this.dbInstancia == null) {
            this.dbInstancia = db.getWritableDatabase(); // só consegue salvar no banco se abrir para escrita
        }

    }
    public void fecharDB() throws SQLException {

        if (this.dbInstancia != null) {
            if(this.dbInstancia.isOpen()) {
                this.dbInstancia.close();
            }
        }
    }
}
