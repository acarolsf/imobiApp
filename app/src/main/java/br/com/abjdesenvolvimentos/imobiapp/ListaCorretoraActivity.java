package br.com.abjdesenvolvimentos.imobiapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.abjdesenvolvimentos.imobiapp.adapter.AdapterCorretora;
import br.com.abjdesenvolvimentos.imobiapp.dao.CorretoraDao;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Corretora;

public class ListaCorretoraActivity extends AppCompatActivity {

    private ListView lista;
    private AdapterCorretora adapador;
    Corretora corretora;
    FloatingActionButton fab;
    CorretoraDao daoC = new CorretoraDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_corretora);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        lista = (ListView) findViewById(R.id.lista);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Cadastrar corretora", Snackbar.LENGTH_LONG)
                        .setAction("Cadastro", null).show();
            }
        });
        //listar corretoras
        ArrayList<String> corretoras = daoC.listar();
//        if(corretoras == null){
//            Intent i = new Intent(ListaCorretoraActivity.this, CadastroCorretoraActivity.class);
//            startActivity(i);
//        } else {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, corretoras);

            lista.setAdapter(adapter);

            lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Corretora corretoraSelecionada = (Corretora) parent.getItemAtPosition(position);
                    // Intent i = new Intent(ListarActivity.this, ExibirImoveisActivity.class)
                    Toast.makeText(ListaCorretoraActivity.this, corretoraSelecionada.getNome() + "selecionada", Toast.LENGTH_SHORT).show();

                }
            });
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Cadastrar", Snackbar.LENGTH_LONG)
                            .setAction("Cadastrar", null).show();
                    Intent i = new Intent(ListaCorretoraActivity.this, CadastroCorretoraActivity.class);
                    startActivity(i);
                }
            });
    }

}
