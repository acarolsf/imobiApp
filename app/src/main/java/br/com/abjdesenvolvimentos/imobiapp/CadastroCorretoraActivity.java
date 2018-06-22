package br.com.abjdesenvolvimentos.imobiapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import br.com.abjdesenvolvimentos.imobiapp.dao.CorretoraDao;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Corretora;

public class CadastroCorretoraActivity extends AppCompatActivity {

    private EditText nome;
    private EditText cidade;
    private EditText telefone;
    private EditText cnpj;
    private EditText email;
    private Button cancelar;
    private Button salvar;
    private ImageView foto;
    private Corretora corretora;
    private CorretoraDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_corretora);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.cad_corretora);

        nome = (EditText) findViewById(R.id.nome_txt);
        cidade = (EditText) findViewById(R.id.cidade_txt);
        telefone = (EditText) findViewById(R.id.telefone_txt);
        cnpj = (EditText) findViewById(R.id.cnpj_txt);
        email = (EditText) findViewById(R.id.email_txt);
        foto = (ImageView) findViewById(R.id.foto_c);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Escolha uma imagem", Snackbar.LENGTH_LONG)
//                        .setAction("Escolher uma imagem", null).show();
//            }
//        });


        Bitmap bitmap = ((BitmapDrawable)foto.getDrawable()).getBitmap();
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,saida);
        final byte[] img = saida.toByteArray();



        cancelar = (Button) findViewById(R.id.cancelar_u);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sair();
            }
        });
        salvar = (Button) findViewById(R.id.save_u);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                corretora = new Corretora();
                corretora.setNome(nome.getText().toString());
                corretora.setCidade(cidade.getText().toString());
                corretora.setTelefone(telefone.getText().toString());
                corretora.setCnpj(Integer.parseInt(cnpj.getText().toString()));
                corretora.setEmail(email.getText().toString());
                //corretora.setFoto(img);
                dao.salvar(corretora);
                finish();
            }
        });
    }
    public void onBackPressed(){
        sair();
    }
    public void sair(){
        new AlertDialog
                .Builder(this)
                .setMessage("Vai perder o que está aqui. Deseja realmente sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        CadastroCorretoraActivity.this.finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
}
