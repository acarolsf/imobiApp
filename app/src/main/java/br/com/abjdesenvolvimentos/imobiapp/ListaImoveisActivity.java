package br.com.abjdesenvolvimentos.imobiapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.abjdesenvolvimentos.imobiapp.banco.DBHelper;
import br.com.abjdesenvolvimentos.imobiapp.adapter.AdapterImovel;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Imoveis;

public class ListaImoveisActivity extends AppCompatActivity{

    private ListView lista;
    private Toolbar toolbar;
    private Imoveis imovelSelecionado;
    private FloatingActionButton fab;
    private DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imoveis);

        configuraBotoes();
        atualizaLista();
    }

    public void configuraBotoes() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        lista = (ListView) findViewById(R.id.lista);
        fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    public void atualizaLista() {

        setSupportActionBar(toolbar);

        db = new DBHelper(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Cadastrar imóvel", Snackbar.LENGTH_LONG)
                        .setAction("Cadastro", null).show();
                Intent i = new Intent(ListaImoveisActivity.this, CadastroImoveisActivity.class);
                startActivity(i);
                }
        });
        ArrayList<Imoveis> imoveis = db.listar();

        final AdapterImovel adapter = new AdapterImovel(this, imoveis);

        lista.setAdapter(adapter);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                imovelSelecionado = (Imoveis) parent.getItemAtPosition(position);
                abrir(imovelSelecionado);
                atualizaLista();
                Toast.makeText(ListaImoveisActivity.this, imovelSelecionado.getDescricao() + " selecionado",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                imovelSelecionado = (Imoveis) parent.getItemAtPosition(position);
                Toast.makeText(ListaImoveisActivity.this, imovelSelecionado.getDescricao() + "selecionado",Toast.LENGTH_SHORT).show();
                   Intent i = new Intent(ListaImoveisActivity.this, ExibirActivity.class);
                i.putExtra("imoveis", imovelSelecionado.getId());
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaLista();
    }
    @Override
    public void onBackPressed(){
        sair();
    }
    public void abrir(final Imoveis imovel){
        final CharSequence[] items = {"Alterar", "Deletar"};
        new AlertDialog
                .Builder(this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which] == "Alterar") {
                            Intent i = new Intent(ListaImoveisActivity.this, EditarActivity.class);
                            i.putExtra("id_imovel", imovel.getId());
                            startActivity(i);
                        } else {
                            delete(imovel);
                        }
                    }
                })
                .show();
    }

    public void sair(){
        new AlertDialog
                .Builder(this)
                .setMessage("Deseja realmente sair?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ListaImoveisActivity.this.finish();
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }
    public void delete(Imoveis imovel) {

        db.deleteImoveis(imovel.getId());

        Toast.makeText(ListaImoveisActivity.this, "Imóvel deletado com sucesso!", Toast.LENGTH_LONG).show();

    }
}
