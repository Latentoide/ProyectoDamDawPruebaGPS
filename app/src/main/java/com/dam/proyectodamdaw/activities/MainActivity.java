package com.dam.proyectodamdaw.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.proyectodamdaw.Parameters;
import com.dam.proyectodamdaw.api.Connector;
import com.dam.proyectodamdaw.base.BaseActivity;
import com.dam.proyectodamdaw.base.CallInterface;
import com.dam.proyectodamdaw.R;
import com.dam.proyectodamdaw.base.ImageDownloader;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends BaseActivity implements CallInterface, View.OnClickListener {
    private final String TAG = MainActivity.class.getName();
    Root root;
    private RecyclerView recyclerView;
    private Ciudad ciudad;
    private TextView nameCiu;
    private float lat,lon;
    boolean compr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout(R.layout.activity_main);
        nameCiu = findViewById(R.id.nomCiu);
        compr = getIntent().getExtras().getBoolean("latLon");
        if(compr){
            lat = getIntent().getExtras().getFloat("lat");
            lon = getIntent().getExtras().getFloat("lon");
        }else{
            ciudad = (Ciudad) getIntent().getExtras().getSerializable("select");
            lat = ciudad.getLat();;
            lon = ciudad.getLon();
        }
        nameCiu.setText(ciudad.getNombre());
        Log.d(TAG, "Ejecutando onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Ejecutando onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Ejecutando onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Ejecutando onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Ejecutando onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showProgress();
        executeCall(this);
        Log.d(TAG, "Ejecutando onResume");
    }

    @Override
    public void doInBackground() {
        if(root == null){
            root = Connector.getConector().get(Root.class,"forecast?lat=" + lat + "&lon="+ lon +"&appid=4d56a19050d401d7ba6b982145243362&lang=es&units=metric");
        }
    }

    @Override
    public void doInUI() {
        hideProgress();
        if(root != null){
            recyclerView = findViewById(R.id.recicled);

            MyReciclerViewAdapter adaptador = new MyReciclerViewAdapter(this,root);
            adaptador.setOnClickListener(this);
            recyclerView.setAdapter(adaptador);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearLayoutManager);
        }
    }

    @Override
    public void onClick(View view){
        Intent myIntent = new Intent(MainActivity.this, weatherDetail.class);
        int i = recyclerView.getChildAdapterPosition(view);
        myIntent.putExtra("info", root.list.get(i));

        startActivity(myIntent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("root", root);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        root = (Root) savedInstanceState.getSerializable("root");
        doInUI();
    }

}