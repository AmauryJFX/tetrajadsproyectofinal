package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Mensajes extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mensajes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView lv = view.findViewById(R.id.lvMessages);

        String[] samples = new String[] {
                "Solicitud: cita de revisión · Hoy 09:12",
                "Confirmación: cita aceptada · Ayer",
                "Reagendada: cambio de hora · 2d",
                "Cancelación: cita cancelada · 3d"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, samples);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener((parent, v, position, id) -> {
            String item = samples[position];
            new AlertDialog.Builder(requireContext())
                    .setTitle("Mensaje")
                    .setMessage(item)
                    .setPositiveButton("Aceptar", (d, w) -> { /* acción mínima */ })
                    .setNegativeButton("Rechazar", (d, w) -> { /* acción mínima */ })
                    .setNeutralButton("Cerrar", null)
                    .show();
        });
    }
}