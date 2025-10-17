package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;

public class SellFragment extends Fragment implements View.OnClickListener {

    private MaterialButton btnPublicar;

    // refs a los campos
    private Chip chipCurso, chipClase;
    private TextInputEditText etTitulo, etDescripcion, etPrecio, etArchivoNombre, etFecha, etHora;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sell, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener vistas
        chipCurso        = view.findViewById(R.id.chipCurso);
        chipClase        = view.findViewById(R.id.chipClase);
        etTitulo         = view.findViewById(R.id.etTitulo);
        etDescripcion    = view.findViewById(R.id.etDescripcion);
        etPrecio         = view.findViewById(R.id.etPrecio);
        etArchivoNombre  = view.findViewById(R.id.etArchivoNombre);
        etFecha          = view.findViewById(R.id.etFecha);
        etHora           = view.findViewById(R.id.etHora);

        btnPublicar = view.findViewById(R.id.btnPublicar);
        btnPublicar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnPublicar) {

            boolean esCurso = chipCurso != null && chipCurso.isChecked();

            String titulo       = textOf(etTitulo);
            String descripcion  = textOf(etDescripcion);
            String precio       = textOf(etPrecio);

            // Armamos el bundle con lo que el usuario ESCRIBIO
            Bundle args = new Bundle();
            args.putBoolean("esCurso", esCurso);
            args.putString("titulo", titulo);
            args.putString("descripcion", descripcion);
            args.putString("precio", precio);

            if (esCurso) {
                String archivoNombre = textOf(etArchivoNombre);
                args.putString("archivoNombre", archivoNombre);
                // si manejas un Uri real en tu fragment (fileUri), pasalo aqui:
                // args.putString("fileUri", fileUri != null ? fileUri.toString() : "");
            } else {
                // Para clase 1 a 1: pasamos fecha/hora como TEXTO
                String fechaStr = textOf(etFecha);
                String horaStr  = textOf(etHora);
                args.putString("fechaStr", fechaStr);
                args.putString("horaStr", horaStr);

                // Si tuvieras millis y enteros, también podrías pasarlos:
                // args.putLong("fechaUtc", selectedDateUtc);
                // args.putInt("hora", selectedHour);
                // args.putInt("minuto", selectedMinute);
            }

            // Crear destino y pasar argumentos
            PublicarVenta destino = new PublicarVenta();
            destino.setArguments(args);

            // Navegar SIN NavController (como ya lo tienes montado)
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainContainer, destino)
                    .addToBackStack(null)
                    .commit();
        }
    }

    private String textOf(TextInputEditText et) {
        if (et == null || et.getText() == null) return "";
        return et.getText().toString().trim();
    }
}
