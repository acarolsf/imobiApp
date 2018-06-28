package br.com.abjdesenvolvimentos.imobiapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.abjdesenvolvimentos.imobiapp.banco.DBHelper;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Imoveis;

public class EditarActivity extends AppCompatActivity {

    private EditText descricao;
    private EditText preco;
    private Spinner cidades;
    private EditText telefone;
    private Spinner comodos;
    private Spinner quartos;
    private Spinner banheiros;
    private Spinner status;
    private EditText tipo;
    private EditText corretora;
    private Button cancelar;
    private Button salvar;
    private Imoveis imovel;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        configurarBotoes();
        db = new DBHelper(this);
        ArrayList<Imoveis> listaImoveis = db.listar();
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sair();
            }
        });

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            int id = extra.getInt("id_imovel");
            for (Imoveis im: listaImoveis) {
                if (im.getId() == id) {
                    descricao.setText(im.getDescricao());
                    preco.setText(String.valueOf(im.getPreco()));
                    telefone.setText(im.getTelefone());
                    tipo.setText(im.getTipo());
                    corretora.setText(im.getCorretora());
                }

            }
        }

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imovel = new Imoveis();
                imovel.setDescricao(descricao.getText().toString());
                imovel.setPreco(Double.parseDouble(preco.getText().toString()));
                imovel.setTelefone(telefone.getText().toString());
                imovel.setCidade(cidades.getSelectedItem().toString());
                imovel.setComodos(Integer.parseInt(comodos.getSelectedItem().toString()));
                imovel.setQuartos(Integer.parseInt(quartos.getSelectedItem().toString()));
                imovel.setBanheiros(Integer.parseInt(banheiros.getSelectedItem().toString()));
                imovel.setStatus(status.getSelectedItem().toString());
                imovel.setTipo(tipo.getText().toString());
                imovel.setCorretora(corretora.getText().toString());
                db.updateImoveis(imovel);
                finish();
                Toast.makeText( EditarActivity.this, "Imóvel alterado com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void configurarBotoes() {

        descricao = (EditText) findViewById(R.id.descricao_txt);
        preco = (EditText) findViewById(R.id.preco_txt);
        telefone = (EditText) findViewById(R.id.telefone_txt);
        cidades = (Spinner) findViewById(R.id.cidades_txt);
        comodos = (Spinner) findViewById(R.id.comodos_txt);
        quartos = (Spinner) findViewById(R.id.quartos_txt);
        banheiros = (Spinner) findViewById(R.id.banheiros_txt);
        status = (Spinner) findViewById(R.id.status_txt);
        tipo = (EditText) findViewById(R.id.tipo_txt);
        corretora = (EditText) findViewById(R.id.corretora_txt);
        cancelar = (Button) findViewById(R.id.cancelar);
        salvar = (Button) findViewById(R.id.salvar);
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
                        EditarActivity.this.finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
}
