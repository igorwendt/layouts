package com.example.igor.layouts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by igor on 24/02/17.
 */

public class ListAdapter extends ArrayAdapter {

    Context contexto;
    int id;
    JSONArray itens;

    public ListAdapter (Context contexto, int id, JSONArray itens){
        super(contexto, id, new String[itens.length()]);
        this.contexto = contexto;
        this.id = id;
        this.itens = itens;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        TextView item;
        TextView idItem;

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(contexto);
            view = inflater.inflate(id, parent, false);
        }

        idItem = (TextView)view.findViewById(R.id.idItem);
        item = (TextView)view.findViewById(R.id.nomeItem);


        try {
            item.setText(itens.getJSONObject(position).getString("nome"));
            idItem.setText(itens.getJSONObject(position).getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return view;
    }
}
