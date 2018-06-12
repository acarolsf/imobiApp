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

public class LoginActivity extends AppCompatActivity {

    private EditText usuario;
    private EditText senha;
    private Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);
        entrar = (Button) findViewById(R.id.salvar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, MenuImobi.class);
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
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onBackPressed(){
        sair();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == android.R.id.home){
            sair();
            return true;
        }
        if(id == R.id.menu_sair){
            sair();
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }
    public void sair() {
        finish();
    }

    }
