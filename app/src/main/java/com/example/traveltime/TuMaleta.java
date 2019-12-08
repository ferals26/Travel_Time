package com.example.traveltime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TuMaleta extends AppCompatActivity {

    private ExpandableListView expLV;
    private ExpLVAdapter adapter;
    private ArrayList<String> listCategorias;
    private Map<String, ArrayList<String>> mapChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tu_maleta);

        expLV = (ExpandableListView) findViewById(R.id.Expandible);
        listCategorias = new ArrayList<>();
        mapChild = new HashMap<>();

        cargarDatos();
    }

    private void cargarDatos(){
        ArrayList<String> listMano = new ArrayList<>();
        ArrayList<String> listDocumentable = new ArrayList<>();


        listCategorias.add("Maleta de mano");
        listCategorias.add("Maleta documentable");


        listMano.add("Papeles");
        listMano.add("Cartera");
        listMano.add("Espejo");

        listDocumentable.add("Ropa");
        listDocumentable.add("Zapatos");


        mapChild.put(listCategorias.get(0), listMano);
        mapChild.put(listCategorias.get(1), listDocumentable);

        adapter = new ExpLVAdapter(this, listCategorias, mapChild);
        expLV.setAdapter(adapter);
    }
}