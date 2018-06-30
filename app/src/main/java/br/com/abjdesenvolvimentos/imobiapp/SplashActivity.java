package br.com.abjdesenvolvimentos.imobiapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        Handler h = new Handler();
        h.postDelayed(this, 3000);
    }

    @Override
    public void run() {
        Intent intent = new Intent(SplashActivity.this, ListaImoveisActivity.class);
        startActivity(intent);
        finish();
    }
}

