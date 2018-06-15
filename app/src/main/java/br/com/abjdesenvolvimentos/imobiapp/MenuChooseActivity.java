 package br.com.abjdesenvolvimentos.imobiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuChooseActivity extends AppCompatActivity {

    private Button corretoras;
    private Button imoveis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_choose);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        botoes();
    }

    private void botoes() {
        corretoras = (Button) findViewById(R.id.corretora);
        corretoras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuChooseActivity.this, ListarActivity.class);
                i.putExtra("key", "c");
                startActivity(i);
            }
        });

        imoveis = (Button) findViewById(R.id.imoveis);
        imoveis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuChooseActivity.this, ListarActivity.class);
                i.putExtra("key", "i");
                startActivity(i);
            }
        });
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
