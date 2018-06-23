package br.com.abjdesenvolvimentos.imobiapp;

import android.content.Intent;
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

import br.com.abjdesenvolvimentos.imobiapp.dao.ImoveisDao;
import br.com.abjdesenvolvimentos.imobiapp.adapter.AdapterImovel;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Imoveis;

public class ListaImoveisActivity extends AppCompatActivity {

    private ListView lista;
    private Imoveis imoveis;
    private FloatingActionButton fab;
    private ImoveisDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imoveis);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dao = new ImoveisDao();
        lista = (ListView) findViewById(R.id.lista);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Cadastrar imóvel", Snackbar.LENGTH_LONG)
                        .setAction("Cadastro", null).show();
                Intent i = new Intent(ListaImoveisActivity.this, CadastroImoveisActivity.class);
                startActivity(i);
            }
        });
        ArrayList<HashMap<String, String>> imoveis = dao.listar();

        if(imoveis == null) {
            Intent i = new Intent (ListaImoveisActivity.this, CadastroImoveisActivity.class);
            startActivity(i);
        }

        final ArrayAdapter<HashMap<String, String>> adapter = new ArrayAdapter<HashMap<String, String>>(this,
                android.R.layout.simple_list_item_1, imoveis);

        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Imoveis imovelSelecionado = (Imoveis) parent.getItemAtPosition(position);
                // Intent i = new Intent(ListarActivity.this, ExibirImoveisActivity.class)
                Toast.makeText(ListaImoveisActivity.this, imovelSelecionado.getDescricao() + "selecinado",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
