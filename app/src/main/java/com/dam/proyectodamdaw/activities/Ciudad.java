package com.dam.proyectodamdaw.activities;

import android.widget.ImageView;

import java.io.Serializable;

public class Ciudad implements Serializable {
    private float lon;
    private float lat;
    private String nombre;
    private String img;

    public Ciudad(String nombre, float lon, float lat, String img){
        this.nombre = nombre;
        this.lon = lat;
        this.lat = lon;
        this.img = img;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public String getNombre() {
        return nombre;
    }

    public String getImg() {
        return img;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
