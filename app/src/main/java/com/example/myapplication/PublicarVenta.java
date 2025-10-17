package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PublicarVenta extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Cambia por tu layout real si usas otro nombre
        return inflater.inflate(R.layout.fragment_publicarventa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);
        TextView tv = v.findViewById(R.id.tvResumen);

        Bundle args = getArguments();
        if (args == null) return;

        boolean esCurso = args.getBoolean("esCurso");
        String titulo = args.getString("titulo", "");
        String descripcion = args.getString("descripcion", "");
        String precio = args.getString("precio", "");

        StringBuilder sb = new StringBuilder();
        sb.append(esCurso ? "PUBLICACION: CURSO\n" : "PUBLICACION: CLASE 1 a 1\n");
        sb.append("Titulo: ").append(titulo).append("\n");
        sb.append("Descripcion: ").append(descripcion).append("\n");
        sb.append("Precio: ").append(precio).append(" MXN\n");

        if (esCurso) {
            String nombre = args.getString("archivoNombre", "(sin nombre)");
            String uriStr = args.getString("fileUri", "");
            sb.append("Archivo: ").append(nombre);
            if (!TextUtils.isEmpty(uriStr)) sb.append("\nURI: ").append(uriStr);
        } else {
            long fechaUtc = args.getLong("fechaUtc", -1);
            int hora = args.getInt("hora", -1);
            int minuto = args.getInt("minuto", -1);
            sb.append("Fecha (UTC ms): ").append(fechaUtc).append("\n");
            sb.append("Hora: ").append(String.format("%02d:%02d", hora, minuto));
        }
        v.findViewById(R.id.btnRegresar).setOnClickListener(view -> {
            requireActivity()
                    .getSupportFragmentManager()
                    .popBackStack(); // regresa a SellFragment
        });
        tv.setText(sb.toString());
    }

}
