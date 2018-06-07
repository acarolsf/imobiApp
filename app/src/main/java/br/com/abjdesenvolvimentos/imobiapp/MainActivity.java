package br.com.abjdesenvolvimentos.imobiapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText usuario;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = (EditText) findViewById(R.id.usuario);
        senha = (EditText) findViewById(R.id.senha);

        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        usuario.setText(settings.getString("usuario", ""));
        senha.setText(settings.getString("senha", ""));
        settings.getBoolean("autosave", false);

    }

    public void salvarLogin(View view) {

        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();


        editor.putString("usuario", usuario.getText().toString());
        editor.putString("senha", senha.getText().toString());
        editor.putBoolean("autosave", false);

        editor.commit();
        finish();
    }
}
