
package br.com.abjdesenvolvimentos.imobiapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.abjdesenvolvimentos.imobiapp.banco.DBHelper;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Imoveis;

public class ExibirActivity extends AppCompatActivity {

    private TextView desc;
    private TextView cidade;
    private TextView preco;
    private TextView corretora;
    private TextView status;
    private TextView telefone;
    private TextView quartos;
    private TextView banheiros;
    private TextView comodos;
    private Button call;
    Imoveis imovel;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        configuraBotoes();

        SQLiteDatabase meuBanco = db.getReadableDatabase();
        Bundle extra = getIntent().getExtras();
        if(extra != null) {
            String nome = extra.getString("imovel");
            Cursor cursor = meuBanco.rawQuery("SELECT * FROM imoveis", null);
            while (cursor.moveToNext()) {
                if (nome == imovel.getDescricao()) {
                    desc.setText(imovel.getDescricao());
                    cidade.setText(imovel.getCidade());
                    preco.setText(String.valueOf(imovel.getPreco()));
                    corretora.setText(imovel.getCorretora());
                    status.setText(imovel.getStatus());
                    telefone.setText(imovel.getTelefone());
                    quartos.setText(imovel.getQuartos());
                    banheiros.setText(imovel.getBanheiros());
                    comodos.setText(imovel.getComodos());
                }
            }
        }
    }

    private void configuraBotoes() {
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCall = new Intent();
                intentCall.setAction(Intent.ACTION_DIAL);
                intentCall.setData(Uri.parse("tel:" + imovel.getTelefone()));
                startActivity(intentCall);
            }
        });
        desc = (TextView) findViewById(R.id.desc_exib);
        cidade = (TextView) findViewById(R.id.cidade_exib);
        preco = (TextView) findViewById(R.id.preco_exib);
        corretora = (TextView) findViewById(R.id.corretora_exib);
        status = (TextView) findViewById(R.id.status_exib);
        telefone = (TextView) findViewById(R.id.telefone_exib);
        quartos = (TextView) findViewById(R.id.bed_linha2);
        banheiros = (TextView) findViewById(R.id.bath_exib);
        comodos = (TextView) findViewById(R.id.comodo_exib);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home) {
            finish();
            return true;
        }
        if(id == R.id.sobre){
            mensagemSobre();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onBackPressed(){
        finish();
    }
    public void mensagemSobre(){
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.sobre))
                .setIcon(R.drawable.ic_launcher_foreground)
                .setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
