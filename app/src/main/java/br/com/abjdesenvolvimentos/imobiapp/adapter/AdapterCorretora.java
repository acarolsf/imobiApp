package br.com.abjdesenvolvimentos.imobiapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.abjdesenvolvimentos.imobiapp.R;
import br.com.abjdesenvolvimentos.imobiapp.dominio.Corretora;

public class AdapterCorretora extends ArrayAdapter<Corretora> {

    private Context context;
    private ArrayList<Corretora> listaCorretoras;

    public AdapterCorretora(Context context, ArrayList<Corretora> listaCorretoras) {
        super(context, R.layout.linha_corretora, listaCorretoras);
        this.context = context;
        this.listaCorretoras = listaCorretoras;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Corretora corretora;
        ImageView fotoC_linha;
        TextView nome_linha;
        TextView telefone_linha;
        TextView email_linha;
        Bitmap raw;
        byte [] fotoArray;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linha = inflater.inflate(R.layout.linha_corretora, parent, false);

        fotoC_linha = (ImageView) linha.findViewById(R.id.fotoC_linha);
        nome_linha = (TextView) linha.findViewById(R.id.nome_linha);
        telefone_linha = (TextView) linha.findViewById(R.id.telefone_linha);
        email_linha = (TextView) linha.findViewById(R.id.email_linha);

        corretora = listaCorretoras.get(position);

        nome_linha.setText(corretora.getNome());
        telefone_linha.setText(corretora.getTelefone());
        email_linha.setText(corretora.getEmail());
        fotoArray = corretora.getFoto();

        if(fotoArray != null) {
            raw = BitmapFactory.decodeByteArray(fotoArray, 0, fotoArray.length);
            fotoC_linha.setImageBitmap(raw);
        }

        return linha;
    }

}
