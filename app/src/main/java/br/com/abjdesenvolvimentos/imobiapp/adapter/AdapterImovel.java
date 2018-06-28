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
import br.com.abjdesenvolvimentos.imobiapp.dominio.Imoveis;

public class AdapterImovel extends ArrayAdapter<Imoveis> {
    private Context context;
    private ArrayList<Imoveis> listaImoveis;
    private Imoveis imoveis;
    private TextView descricao_linha;
    private TextView corretora_linha;
    private TextView cidade_linha;
    private TextView bed_linha;
    private TextView bath_linha;
    private TextView comodo_linha;
    private TextView preco_linha;

    public AdapterImovel(Context context, ArrayList<Imoveis> listaImoveis) {
        super(context, R.layout.linha_imoveis, listaImoveis);
        this.context = context;
        this.listaImoveis = listaImoveis;
    }

    public View getView(int position, View convertView, ViewGroup parent) {



        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View linha = inflater.inflate(R.layout.linha_imoveis, parent, false);

        descricao_linha = (TextView) linha.findViewById(R.id.descricao_linha);
        corretora_linha = (TextView) linha.findViewById(R.id.corretora_linha);
        cidade_linha = (TextView) linha.findViewById(R.id.cidade_linha);
        bed_linha = (TextView) linha.findViewById(R.id.bed_linha);
        bath_linha = (TextView) linha.findViewById(R.id.bath_linha);
        comodo_linha = (TextView) linha.findViewById(R.id.comodo_linha);
        preco_linha = (TextView) linha.findViewById(R.id.preco_linha);

        imoveis = listaImoveis.get(position);

        descricao_linha.setText(imoveis.getDescricao());
        corretora_linha.setText(imoveis.getCorretora());
        cidade_linha.setText(imoveis.getCidade());
        bed_linha.setText(Integer.toString(imoveis.getQuartos()));
        bath_linha.setText(Integer.toString(imoveis.getBanheiros()));
        comodo_linha.setText(Integer.toString(imoveis.getComodos()));
        preco_linha.setText(Double.toString(imoveis.getPreco()));


        return linha;

    }
}
