package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        MaterialButton btnLogin = findViewById(R.id.btnLogin);
        MaterialButton btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(v ->
                startActivity(new Intent(WelcomeActivity.this, IniciarSesion.class)));

        btnRegister.setOnClickListener(v ->
                startActivity(new Intent(WelcomeActivity.this, registro.class)));
    }


    @Override
    public void onClick(View view) {
        String cadena = ((Button)view).getText().toString();
        if(cadena.equals("Cerrar sesion")){
            Intent intent = new Intent(this,WelcomeActivity.class);
            startActivity(intent);
        }
    }
}
