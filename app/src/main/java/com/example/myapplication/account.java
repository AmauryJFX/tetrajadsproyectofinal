package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class account extends Fragment implements View.OnClickListener {

    private Button btnCerrarSesion;
    private Button btnVerHistorial;
    private Button btnEstadisticas;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnCerrarSesion  = view.findViewById(R.id.btnCerrarSesion);
        btnVerHistorial  = view.findViewById(R.id.btnHistorial);
        btnEstadisticas = view.findViewById(R.id.btnEstadisticas);

        btnCerrarSesion.setOnClickListener(this);
        btnVerHistorial.setOnClickListener(this);
        btnEstadisticas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        FragmentManager fm = requireActivity().getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();

        if (id == R.id.btnCerrarSesion) {
            // Ir a la pantalla de bienvenida (Fragment)
            // Limpia el back stack para que no se pueda volver con "atrás"
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            tx.replace(R.id.mainContainer, new WelcomeActivity2())  // <-- tu fragmento de bienvenida
                    .commit();

            // Si "WelcomeActivity" fuera una **Activity**, en vez de esto usa:
            // startActivity(new Intent(requireContext(), WelcomeActivity.class));
            // requireActivity().finish();

        } else if (id == R.id.btnHistorial) {
            // Ir al Historial (y permitir volver con "atrás")
            tx.replace(R.id.mainContainer, new Historial())
                    .addToBackStack(null)
                    .commit();
        }else if (id == R.id.btnEstadisticas) {
            // Ir a estadisticas (y permitir volver con "atrás")
            tx.replace(R.id.mainContainer, new Estadisticas())
                    .addToBackStack(null)
                    .commit();
        }
    }
}

