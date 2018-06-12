package br.com.abjdesenvolvimentos.imobiapp.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "db_imobiapp.db";
    private static final int VERSAO_BANCO = 1;
    private Context context;
    private SQLiteDatabase dbInstancia = null;

    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE clientes(id INTERGER PRIMARY KEY AUTOINCREMENT, nome TEXT, " +
                "endereco TEXT, cidade TEXT, cpf TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS db_imobiapp");
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
}
