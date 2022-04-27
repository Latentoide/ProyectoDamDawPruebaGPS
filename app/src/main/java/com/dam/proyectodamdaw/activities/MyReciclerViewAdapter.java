package com.dam.proyectodamdaw.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dam.proyectodamdaw.Parameters;
import com.dam.proyectodamdaw.R;
import com.dam.proyectodamdaw.base.ImageDownloader;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyReciclerViewAdapter extends RecyclerView.Adapter<MyReciclerViewAdapter.ViewHolder> {
    private LayoutInflater myInflator;
    private View.OnClickListener myClicker;
    private Root root;

    public MyReciclerViewAdapter(Context miContenidito, Root rootito){
        root = rootito;
        myInflator = (LayoutInflater) miContenidito.getSystemService(miContenidito.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyReciclerViewAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){
        View view = myInflator.inflate(R.layout.view_layout, parent, false);
        view.setOnClickListener(myClicker);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReciclerViewAdapter.ViewHolder viewHolder, int position){
        ImageDownloader.downloadImage(Parameters.URL_ICON_PRE + root.list.get(position).weather.get(0).icon + Parameters.URL_ICON_POST, viewHolder.imageView);
        viewHolder.tempMedia.setText(root.list.get(position).main.temp + "");
        viewHolder.tempMax.setText(root.list.get(position).main.temp_max+"");
        viewHolder.tempMin.setText(root.list.get(position).main.temp_min+"");
        viewHolder.desc.setText(root.list.get(position).weather.get(0).description);

        Date d = new Date((long)root.list.get(position).dt*1000);
        viewHolder.dia.setText(new SimpleDateFormat("EEEE").format(d));
        viewHolder.hora.setText(new SimpleDateFormat("HH:mm").format(d));
        viewHolder.fecha.setText(new SimpleDateFormat("dd/MM/YYYY").format(d));
    }

    @Override
    public int getItemCount(){
        return root.list.size();
    }

    public void setOnClickListener(View.OnClickListener clickeado){
        myClicker = clickeado;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView hora, dia, tempMin, tempMax, tempMedia, desc, fecha;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.myIm);
            dia = itemView.findViewById(R.id.Dia);
            hora = itemView.findViewById(R.id.hora);
            tempMin = itemView.findViewById(R.id.tempMin);
            tempMax = itemView.findViewById(R.id.tempMax);
            tempMedia = itemView.findViewById(R.id.tempMedia);
            desc = itemView.findViewById(R.id.desc);
            fecha = itemView.findViewById(R.id.fecha);

        }
    }
}
