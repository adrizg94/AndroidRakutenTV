package com.seas.androidrakutentv.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.seas.androidrakutentv.R;
import com.seas.androidrakutentv.beans.Film;
import com.seas.androidrakutentv.utils.Statics;
import com.seas.androidrakutentv.views.ListFilmsActivity;
import com.seas.androidrakutentv.views.ViewFilmActivity;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ListFilmsAdapter extends RecyclerView.Adapter<ListFilmsAdapter.ListFilmsViewHolder> {

    private ArrayList<Film> listFilms;
    private Context context;
    private ListFilmsActivity view;

    public ListFilmsAdapter (ArrayList<Film> listFilms, Context context, ListFilmsActivity view) {
        this.listFilms = listFilms;
        this.context = context;
        this.view = view;
    }

    public static class ListFilmsViewHolder extends RecyclerView.ViewHolder {
        public ImageView imagen;
        public TextView titulo;
        public TextView visitas;
        public TextView puntuacion;
        public TextView precio;
        public ImageButton btnView;

        public ListFilmsViewHolder(View v) {
            super(v);
            imagen = v.findViewById(R.id.imgFilm);
            titulo = v.findViewById(R.id.txtTitulo);
            visitas = v.findViewById(R.id.txtVisitas);
            puntuacion = v.findViewById(R.id.txtPuntuacion);
            precio = v.findViewById(R.id.txtPrecio);
            btnView = v.findViewById(R.id.btnView);
        }

    }

    @NonNull
    @NotNull
    @Override
    public ListFilmsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_film, parent, false);
        return new ListFilmsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ListFilmsViewHolder holder, int position) {
        Picasso.get().load(Statics.urlImages + listFilms.get(position).getUrl() + ".jpg").into(holder.imagen);
        holder.titulo.setText(listFilms.get(position).getTitulo());
//        holder.sinopsis.setText(String.valueOf(listFilms.get(position).getPrecio()));
        holder.puntuacion.setText("Visitas: " + (listFilms.get(position).getPuntuacion()));
        holder.visitas.setText("Votos: " + (listFilms.get(position).getVotos()));
        holder.precio.setText("Alquiler: " + (listFilms.get(position).getPrecio()) + "€");
        holder.btnView.setOnClickListener(v -> {

            Statics.film.setUrl(listFilms.get(position).getUrl());
            Statics.film.setTitulo(listFilms.get(position).getTitulo());
            Statics.film.setEstreno(listFilms.get(position).getEstreno());
            Statics.film.setDuracion(listFilms.get(position).getDuracion());
            Statics.film.setSinopsis(listFilms.get(position).getSinopsis());
            Statics.film.setTrailer(listFilms.get(position).getTrailer());
            Statics.film.setPrecio(listFilms.get(position).getPrecio());

            Intent viewFilm = new Intent(context, ViewFilmActivity.class);
            view.setViewFilmIntent(viewFilm);
        });
    }

    @Override
    public int getItemCount() {
        return listFilms.size();
    }
}
