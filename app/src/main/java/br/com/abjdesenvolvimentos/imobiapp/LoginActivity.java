package br.com.abjdesenvolvimentos.imobiapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText senha;
    private TextView cad_user;
    private Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        cad_user = (TextView) findViewById(R.id.cad_user);
        cad_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(i);
            }
        });
        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
        entrar = (Button) findViewById(R.id.salvar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ListaImoveisActivity.class);
                startActivity(i);
            }
        });

        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        usuario.setText(settings.getString("usuario", ""));
        senha.setText(settings.getString("senha", ""));

    }

    public void salvarLogin(View view) {

        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("usuario", usuario.getText().toString());
        editor.putString("senha", senha.getText().toString());

        editor.commit();
    }

    @Override
    public void onBackPressed(){
        sair();
    }

    public void sair() {
        finish();
    }

    }
