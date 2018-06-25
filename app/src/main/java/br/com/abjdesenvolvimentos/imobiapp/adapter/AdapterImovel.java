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

    public AdapterImovel(Context context, ArrayList<Imoveis> listaImoveis) {
        super(context, R.layout.linha_imoveis, listaImoveis);
        this.context = context;
        this.listaImoveis = listaImoveis;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Imoveis imoveis;
        TextView descricao_linha;
        TextView corretora_linha;
        TextView cidade_linha;
        TextView bed_linha;
        TextView bath_linha;
        TextView comodo_linha;
        TextView preco_linha;

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
        bed_linha.setText(imoveis.getQuartos());
        bath_linha.setText(imoveis.getBanheiros());
        comodo_linha.setText(imoveis.getComodos());
        preco_linha.setText(Double.toString(imoveis.getPreco()));


        return linha;

    }
}
