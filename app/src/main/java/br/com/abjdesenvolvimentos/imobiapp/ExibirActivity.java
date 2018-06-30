
package br.com.abjdesenvolvimentos.imobiapp;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import java.util.ArrayList;

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
    private String tel;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        configuraBotoes();

        db = new DBHelper(this);
        ArrayList<Imoveis> imoveisArrayList = db.listar();
        Bundle extra = getIntent().getExtras();

        if(extra != null) {
            int id = extra.getInt("imoveis");
            Toast.makeText(ExibirActivity.this, "Id intent: "+id,Toast.LENGTH_SHORT).show();

            for(Imoveis i : imoveisArrayList) {
                if (id == i.getId()) {
                    Toast.makeText(ExibirActivity.this, "Id encontrada: "+i.getId(),Toast.LENGTH_SHORT).show();

                    System.out.println(i.getId());
                    desc.setText(i.getDescricao());
                    cidade.setText(i.getCidade());
                    preco.setText(String.valueOf(i.getPreco()));
                    corretora.setText(i.getCorretora());
                    status.setText(i.getStatus());
                    telefone.setText(i.getTelefone());
                    quartos.setText(String.valueOf(i.getQuartos()));
                    banheiros.setText(String.valueOf(i.getBanheiros()));
                    comodos.setText(String.valueOf(i.getComodos()));
                    tel = String.valueOf(i.getTelefone());
                    break;
                } else {
                    Toast.makeText(ExibirActivity.this, "Imóvel não existe",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCall = new Intent();
                intentCall.setAction(Intent.ACTION_DIAL);
                intentCall.setData(Uri.parse("tel:" + tel));
                startActivity(intentCall);
            }
        });
    }

    private void configuraBotoes() {
        call = (Button) findViewById(R.id.button);
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
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.sobre))
                .setPositiveButton("Voltar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
