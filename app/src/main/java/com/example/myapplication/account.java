package com.example.myapplication;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class account extends Fragment implements View.OnClickListener {

    private Button btnCerrarSesion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnCerrarSesion = view.findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(this); // <- PON EL LISTENER EN EL BOTON
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCerrarSesion) { // <- MEJOR POR ID QUE POR TEXTO
            NavController nav = Navigation.findNavController(requireView());
            nav.navigate(R.id.action_account_to_welcomeActivity22); // <- USA EL ACTION
        }
    }
}
