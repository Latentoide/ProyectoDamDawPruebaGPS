package com.dam.proyectodamdaw.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.dam.proyectodamdaw.Parameters;
import com.dam.proyectodamdaw.R;
import com.dam.proyectodamdaw.base.ImageDownloader;

import java.text.SimpleDateFormat;
import java.util.Date;

public class weatherDetail extends AppCompatActivity {
    ImageView im;
    TextView hora, fecha, max, min, desc, dia;
    List l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        l = (List) getIntent().getExtras().getSerializable("info");
        im = findViewById(R.id.miImagenSola);
        hora = findViewById(R.id.horaSolo);
        fecha = findViewById(R.id.fechaSola);
        max = findViewById(R.id.maxSolo);
        min = findViewById(R.id.minSolo);
        desc = findViewById(R.id.descSola);
        dia = findViewById(R.id.diaSolo);
        ImageDownloader.downloadImage(Parameters.URL_ICON_PRE+ l.weather.get(0).icon+ Parameters.URL_ICON_POST, im);
        Date d = new Date((long)l.dt*1000);
        hora.setText(new SimpleDateFormat("HH:mm").format(d));
        fecha.setText(new SimpleDateFormat("dd/MM/YYYY").format(d));
        max.setText(l.main.temp_max + "");
        max.setText(l.main.temp_min + "");
        max.setText(l.weather.get(0).description);
        dia.setText(new SimpleDateFormat("EEEE").format(d));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("hora", hora.getText().toString());
        outState.putString("dia", dia.getText().toString());
        outState.putString("fecha", fecha.getText().toString());
        outState.putString("max", max.getText().toString());
        outState.putString("min", min.getText().toString());
        outState.putString("desc", desc.getText().toString());
        outState.putString("miIcono", l.weather.get(0).icon);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        hora.setText(savedInstanceState.getString("hora"));
        dia.setText(savedInstanceState.getString("dia"));
        fecha.setText(savedInstanceState.getString("fecha"));
        max.setText(savedInstanceState.getString("max"));
        min.setText(savedInstanceState.getString("min"));
        desc.setText(savedInstanceState.getString("desc"));
        ImageDownloader.downloadImage(Parameters.URL_ICON_PRE+ savedInstanceState.getString("miIcono")+ Parameters.URL_ICON_POST, im);
    }
}