package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IniciarSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciarsesion);

        Button btnIniciar = findViewById(R.id.btnIniciarSesion);
        Button btnRegistrar = findViewById(R.id.btnRegistrarse);

        // Ir a la pantalla principal cuando presione "Iniciar sesión"
        // (cámbialo a MainActivity si prefieres esa)
        btnIniciar.setOnClickListener(v -> {
            startActivity(new Intent(IniciarSesion.this, MainActivity.class));
            finish(); // para que no vuelva a login con Back
        });

        // Ir al Activity de registro existente (tu clase se llama 'registro')
        btnRegistrar.setOnClickListener(v -> {
            startActivity(new Intent(IniciarSesion.this, registro.class));
        });
    }
}
