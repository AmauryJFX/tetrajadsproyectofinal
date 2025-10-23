package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;

public class Estadisticas extends Fragment {

    // Views
    private TextView tvCursosCompletados;
    private TextView tvClases1a1;
    private TextView tvIngresosTotales;
    private TextView tvCursosActivos;

    private TextView tvIngresos30d;
    private TextView tvUltimaActividad;
    private MaterialButton btnVolverEstadisticas;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_estadisticas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Bind views
        tvCursosCompletados = view.findViewById(R.id.tvCursosCompletados);
        tvClases1a1 = view.findViewById(R.id.tvClases1a1);
        tvIngresosTotales = view.findViewById(R.id.tvIngresosTotales);
        tvCursosActivos = view.findViewById(R.id.tvCursosActivos);

        tvIngresos30d = view.findViewById(R.id.tvIngresos30d);
        tvUltimaActividad = view.findViewById(R.id.tvUltimaActividad);
        btnVolverEstadisticas = view.findViewById(R.id.btnVolverEstadisticas);

        // Placeholders / valores iniciales
        if (tvCursosCompletados != null) tvCursosCompletados.setText("0");
        if (tvClases1a1 != null) tvClases1a1.setText("0");
        if (tvIngresosTotales != null) tvIngresosTotales.setText("$0.00");
        if (tvCursosActivos != null) tvCursosActivos.setText("0");
        if (tvIngresos30d != null) tvIngresos30d.setText("$0.00");
        if (tvUltimaActividad != null) tvUltimaActividad.setText("—");

        // Botón volver: vuelve al fragment anterior
        if (btnVolverEstadisticas != null) {
            btnVolverEstadisticas.setOnClickListener(v ->
                    requireActivity().getSupportFragmentManager().popBackStack()
            );
        }
    }
}