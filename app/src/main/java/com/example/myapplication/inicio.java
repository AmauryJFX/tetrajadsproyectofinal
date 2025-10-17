package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class inicio extends AppCompatActivity {

    private androidx.recyclerview.widget.RecyclerView rv;
    private PublicacionAdapter adapter;
    private TextInputEditText etBuscar;
    private ChipGroup chipsFiltro;
    private Chip chipTodos, chipCursos, chipClases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        // Vincular elementos del layout
        rv = findViewById(R.id.rvPublicaciones);
        etBuscar = findViewById(R.id.etBuscar);
        chipsFiltro = findViewById(R.id.chipsFiltro);
        chipTodos = findViewById(R.id.chipTodos);
        chipCursos = findViewById(R.id.chipCursos);
        chipClases = findViewById(R.id.chipClases);

        // Configurar RecyclerView
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PublicacionAdapter();
        rv.setAdapter(adapter);

        // Cargar las publicaciones guardadas en memoria
        cargarPublicaciones();

        // Buscador
        etBuscar.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                aplicarFiltros();
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Filtros por tipo
        chipsFiltro.setOnCheckedStateChangeListener((group, checkedIds) -> aplicarFiltros());
    }

    // Cargar publicaciones simuladas desde HistorialManager
    private void cargarPublicaciones() {
        List<PublicacionAdapter.PublicacionVM> lista = HistorialManager.obtenerTodas();

        if (lista.isEmpty()) {
            Toast.makeText(this, "Aun no hay publicaciones", Toast.LENGTH_SHORT).show();
        }

        adapter.setItems(lista);
        aplicarFiltros();
    }

    // Filtra por tipo o texto buscado
    private void aplicarFiltros() {
        Boolean soloCursos = null;
        if (chipCursos.isChecked()) soloCursos = true;
        if (chipClases.isChecked()) soloCursos = false;

        String q = etBuscar.getText() == null ? "" : etBuscar.getText().toString();
        adapter.filter(soloCursos, q);
    }

    // Cuando vuelves a la pantalla, recarga la lista
    @Override
    protected void onResume() {
        super.onResume();
        cargarPublicaciones();
    }
}
