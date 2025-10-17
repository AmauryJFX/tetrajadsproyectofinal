package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;
import java.util.List;

public class PublicacionAdapter extends RecyclerView.Adapter<PublicacionAdapter.ViewHolder> {

    private List<PublicacionVM> items = new ArrayList<>();
    private List<PublicacionVM> itemsFiltrados = new ArrayList<>();

    public void setItems(List<PublicacionVM> lista) {
        this.items = lista;
        this.itemsFiltrados = new ArrayList<>(lista);
        notifyDataSetChanged();
    }

    public void filter(Boolean soloCursos, String query) {
        itemsFiltrados.clear();
        query = query.toLowerCase();

        for (PublicacionVM p : items) {
            boolean coincideTipo = (soloCursos == null) || (p.esCurso == soloCursos);
            boolean coincideTexto = p.titulo.toLowerCase().contains(query)
                    || p.extra.toLowerCase().contains(query);
            if (coincideTipo && coincideTexto) {
                itemsFiltrados.add(p);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_publicacion, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        PublicacionVM p = itemsFiltrados.get(position);

        h.tvTitulo.setText(p.titulo);
        h.tvPrecio.setText(p.precio);
        h.tvExtra.setText(p.extra);
        h.tvTipo.setText(p.esCurso ? "Curso" : "Clase 1 a 1");
    }

    @Override
    public int getItemCount() {
        return itemsFiltrados.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitulo, tvPrecio, tvExtra, tvTipo;
        MaterialCardView card;

        public ViewHolder(@NonNull View v) {
            super(v);
            card = v.findViewById(R.id.cardPublicacion);
            tvTitulo = v.findViewById(R.id.tvTitulo);
            tvPrecio = v.findViewById(R.id.tvPrecio);
            tvExtra = v.findViewById(R.id.tvExtra);
            tvTipo = v.findViewById(R.id.tvTipo);
        }
    }

    // Modelo de datos interno
    public static class PublicacionVM {
        public boolean esCurso;
        public String titulo;
        public String precio;
        public String extra;

        public PublicacionVM(boolean esCurso, String titulo, String precio, String extra) {
            this.esCurso = esCurso;
            this.titulo = titulo;
            this.precio = precio;
            this.extra = extra;
        }
    }
}
