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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PublicarVenta extends Fragment {

    private boolean esCurso;
    private String titulo, descripcion, precio;
    private String archivoNombre, fileUri;
    private String fechaStr, horaStr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_publicarventa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        // 1) Leer datos que vienen desde SellFragment
        Bundle args = getArguments();
        if (args != null) {
            esCurso = args.getBoolean("esCurso");
            titulo = args.getString("titulo", "");
            descripcion = args.getString("descripcion", "");
            precio = args.getString("precio", "");
            archivoNombre = args.getString("archivoNombre", "");
            fileUri = args.getString("fileUri", "");
            fechaStr = args.getString("fechaStr", "");
            horaStr = args.getString("horaStr", "");
        }

        // 2) Referencias a vistas
        TextView tvTipo = v.findViewById(R.id.tvTipo);
        TextView tvTituloValor = v.findViewById(R.id.tvTituloValor);
        TextView tvDescripcionValor = v.findViewById(R.id.tvDescripcionValor);
        TextView tvPrecioValor = v.findViewById(R.id.tvPrecioValor);
        TextView tvExtraLabel = v.findViewById(R.id.tvExtraLabel);
        TextView tvExtraValor = v.findViewById(R.id.tvExtraValor);

        // 3) Mostrar los datos del resumen
        tvTipo.setText(esCurso ? "Tipo: Curso (pregrabado)" : "Tipo: Clase 1 a 1");
        tvTituloValor.setText(titulo);
        tvDescripcionValor.setText(descripcion);
        tvPrecioValor.setText(TextUtils.isEmpty(precio) ? "" : precio + " MXN");

        if (esCurso) {
            tvExtraLabel.setText("Archivo:");
            tvExtraValor.setText(
                    !TextUtils.isEmpty(archivoNombre) ? archivoNombre :
                            !TextUtils.isEmpty(fileUri) ? fileUri : "(sin archivo)"
            );
        } else {
            tvExtraLabel.setText("Fecha y hora:");
            String fh = (TextUtils.isEmpty(fechaStr) && TextUtils.isEmpty(horaStr))
                    ? "(sin fecha/hora)"
                    : (fechaStr + (TextUtils.isEmpty(horaStr) ? "" : "  " + horaStr));
            tvExtraValor.setText(fh);
        }

        // 4) Botón regresar
        v.findViewById(R.id.btnRegresar).setOnClickListener(view -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        // 5) Botón confirmar → guardar publicación en memoria
        v.findViewById(R.id.btnConfirmar).setOnClickListener(view -> {
            guardarEnHistorialLocal();
        });
    }

    // ✅ Guardar publicación en lista temporal y mostrar alerta
    private void guardarEnHistorialLocal() {
        String extra = esCurso
                ? (!TextUtils.isEmpty(archivoNombre) ? archivoNombre : fileUri)
                : (!TextUtils.isEmpty(fechaStr) || !TextUtils.isEmpty(horaStr)
                ? (fechaStr + " " + horaStr)
                : "");

        // Crear objeto de publicación
        PublicacionAdapter.PublicacionVM p =
                new PublicacionAdapter.PublicacionVM(esCurso, titulo, precio + " MXN", extra);

        // Guardar en el “historial” en memoria
        HistorialManager.agregar(p);

        // Mostrar alerta de éxito
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("¡Publicación exitosa!")
                .setMessage("Tu publicación ha sido subida correctamente.")
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    dialog.dismiss();
                    // Volvemos al fragmento anterior
                    requireActivity().getSupportFragmentManager().popBackStack();
                })
                .show();
    }
}
