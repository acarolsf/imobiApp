package br.com.abjdesenvolvimentos.imobiapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.abjdesenvolvimentos.imobiapp.dao.ImoveisDao;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Imoveis;

public class CadastroImoveisActivity extends AppCompatActivity {

    private EditText descricao;
    private EditText preco;
    private Spinner cidades;
    private Spinner comodos;
    private Spinner quartos;
    private Spinner banheiros;
    private Spinner status;
    private EditText tipo;
    private EditText corretora;
    private Button cancelar;
    private Button salvar;
    private ImoveisDao dao;
    Resources res = getResources();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_imoveis);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.cad_imoveis);

        descricao = (EditText) findViewById(R.id.descricao_txt);
        preco = (EditText) findViewById(R.id.preco_txt);
        cidades = (Spinner) findViewById(R.id.cidades_txt);
        comodos = (Spinner) findViewById(R.id.comodos_txt);
        quartos = (Spinner) findViewById(R.id.quartos_txt);
        banheiros = (Spinner) findViewById(R.id.banheiros_txt);
        status = (Spinner) findViewById(R.id.status_txt);
        tipo = (EditText) findViewById(R.id.tipo_txt);
        corretora = (EditText) findViewById(R.id.corretora_txt);

        cancelar = (Button) findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sair();
            }
        });
        salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Imoveis imovel = new Imoveis();
                dao = new ImoveisDao();
                imovel.setDescricao(descricao.getText().toString());
                imovel.setPreco(Double.parseDouble(preco.getText().toString()));
                imovel.setCidade(cidades.getSelectedItem().toString());
                imovel.setComodos(Integer.parseInt(comodos.getSelectedItem().toString()));
                imovel.setQuartos(Integer.parseInt(quartos.getSelectedItem().toString()));
                imovel.setBanheiros(Integer.parseInt(banheiros.getSelectedItem().toString()));
                imovel.setStatus(status.getSelectedItem().toString());
                imovel.setTipo(tipo.getText().toString());
                imovel.setCorretora(corretora.getText().toString());
                dao.salvar(imovel);
                Intent i = new Intent(CadastroImoveisActivity.this, ListarActivity.class);
                i.putExtra("key", "i");
                i.putExtra("main", "notMain");
                startActivity(i);
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
                        CadastroImoveisActivity.this.finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
}
