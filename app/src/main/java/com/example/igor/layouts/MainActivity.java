package com.example.igor.layouts;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBManager dbManager = new DBManager(this);
    ListAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ListView controle;
//        JSONArray itens;
//        itens = dbManager.getAllItens();
//        adaptador = new ListAdapter(getApplication(), R.layout.listview, itens);
//        controle = (ListView)findViewById(R.id.ListViewItens);
//        controle.setAdapter(adaptador);


        try {
            updateList();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void updateList() throws JSONException {
        ListView controle;
        JSONArray itens;
        itens = dbManager.getAllItens();
        adaptador = new ListAdapter(getApplication(), R.layout.listview, itens);
        controle = (ListView)findViewById(R.id.ListViewItens);
        controle.setAdapter(adaptador);

    }

    public void alert(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("OK- deleta todo banco | Cancel- add")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dbManager.deleteAll();
                        try {
                            updateList();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editNome = (EditText)findViewById(R.id.editTextNome);
                        String item = editNome.getText().toString();
                        dbManager.addItem(item);
                        try {
                            updateList();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
