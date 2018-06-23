package br.com.abjdesenvolvimentos.imobiapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.abjdesenvolvimentos.imobiapp.dao.UsuarioDao;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private EditText email;
    private Button cancelar;
    private Button salvar;
    private Usuario user;
    private UsuarioDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.cad_usuario);

        dao = new UsuarioDao();
        login = (EditText) findViewById(R.id.loginU_txt);
        senha = (EditText) findViewById(R.id.pass_txt);
        email = (EditText) findViewById(R.id.emailU_txt);

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
                user = new Usuario();
                user.setLogin(login.getText().toString());
                user.setSenha(senha.getText().toString());
                user.setEmail(email.getText().toString());
                dao.salvar(user);
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
                        CadastroUsuarioActivity.this.finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
}
