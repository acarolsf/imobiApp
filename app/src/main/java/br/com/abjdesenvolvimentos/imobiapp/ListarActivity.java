package br.com.abjdesenvolvimentos.imobiapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.abjdesenvolvimentos.imobiapp.dao.CorretoraDao;
import br.com.abjdesenvolvimentos.imobiapp.dao.ImoveisDao;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Corretora;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Imoveis;

public class ListarActivity extends AppCompatActivity {

    private ListView lista;
    Imoveis imoveis;
    Corretora corretora;
    ImoveisDao daoI = new ImoveisDao();
    CorretoraDao daoC = new CorretoraDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        lista = (ListView) findViewById(R.id.lista);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if(extra != null){
            if(extra.getString("main") != "main") {
                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, "Cadastro", Snackbar.LENGTH_LONG)
                                .setAction("Cadastrar", null).show();
                    }
                });
            }

            if(extra.getString("key") == "c") {
                //listar corretoras
                ArrayList<String> corretoras = daoC.listar();

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, corretoras);

                lista.setAdapter(adapter);
            }

            if(extra.getString("key") == "i") {
                ArrayList<String> imoveis = daoI.listar();

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, imoveis);

                lista.setAdapter(adapter);
                lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Imoveis imovelSelecionado = (Imoveis) parent.getItemAtPosition(position);
                            // Intent i = new Intent(ListarActivity.this, ExibirA)
                            Toast.makeText(ListarActivity.this, imovelSelecionado.getDescricao() + "selecinado",Toast.LENGTH_SHORT).show(); }
                    });
            }
        }
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
            finish();
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
