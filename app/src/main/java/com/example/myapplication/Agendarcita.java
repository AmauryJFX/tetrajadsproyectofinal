package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;
import java.util.Locale;

public class Agendarcita extends Fragment {

    private CalendarView calendarView;
    private TextInputLayout tilHora;
    private TextInputEditText etHora;
    private MaterialButton btnConfirmar;

    private long selectedDateMillis = System.currentTimeMillis();

    public Agendarcita() { }

    public static Agendarcita newInstance(String param1, String param2) {
        Agendarcita fragment = new Agendarcita();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // OJO: usa el nombre real de tu layout
        return inflater.inflate(R.layout.fragment_agendarcita, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Referencias
        calendarView = view.findViewById(R.id.calendarView);
        //tilHora      = view.findViewById(R.id.text_input_layout);
        etHora       = view.findViewById(R.id.etHora);
        btnConfirmar = view.findViewById(R.id.btnConfirmarAgenda);

        // Coloca hoy en el calendario (evita que aparezca 1969)
        if (calendarView != null) {
            calendarView.setDate(System.currentTimeMillis(), false, true);
            calendarView.setOnDateChangeListener((cv, year, month, dayOfMonth) -> {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // ancla a medianoche
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                c.set(Calendar.MILLISECOND, 0);
                selectedDateMillis = c.getTimeInMillis();
            });
        }

        // Abrir time picker al tocar el campo
        if (etHora != null) {
            etHora.setOnClickListener(v -> abrirTimePicker());
        }

        // Abrir time picker al tocar el ícono del TextInputLayout
        if (tilHora != null) {
            tilHora.setEndIconOnClickListener(v -> abrirTimePicker());
        }

        // Botón Agendar: muestra fecha + hora
        if (btnConfirmar != null) {
            btnConfirmar.setOnClickListener(v -> {
                String hora = etHora != null ? String.valueOf(etHora.getText()) : "";
                if (hora == null || hora.trim().isEmpty()) {
                    Toast.makeText(requireContext(), "Selecciona una hora", Toast.LENGTH_SHORT).show();
                    return;
                }

                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(selectedDateMillis);
                String fecha = String.format(Locale.getDefault(),
                        "%02d/%02d/%04d",
                        c.get(Calendar.DAY_OF_MONTH),
                        (c.get(Calendar.MONTH) + 1),
                        c.get(Calendar.YEAR));

                String msg = "Cita para el " + fecha + " a las " + hora;
                Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show();
            });
        }
    }

    private void abrirTimePicker() {
        Calendar now = Calendar.getInstance();
        int h = now.get(Calendar.HOUR_OF_DAY);
        int m = now.get(Calendar.MINUTE);

        MaterialTimePicker picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(h)
                .setMinute(m)
                .setTitleText("Elige la hora")
                .build();

        picker.addOnPositiveButtonClickListener(v -> {
            int hh = picker.getHour();
            int mm = picker.getMinute();
            String hora = String.format(Locale.getDefault(), "%02d:%02d", hh, mm);
            if (etHora != null) etHora.setText(hora);
        });

        // Fragment → usa el parentFragmentManager
        picker.show(getParentFragmentManager(), "time_picker_agendar");
    }
}
