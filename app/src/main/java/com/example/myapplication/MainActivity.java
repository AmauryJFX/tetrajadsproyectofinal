package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // debe tener mainContainer + bottomBar + fabCentral

        bottomBar = findViewById(R.id.bottomBar);

        // Fragment inicial
        if (savedInstanceState == null) {
            replaceFragment(new HomeFragment());
            bottomBar.setSelectedItemId(R.id.nav_home);
        }

        // Listener sin lambda (evita errores de tipo)
        bottomBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment f = null;

                if (id == R.id.nav_home) {
                    f = new HomeFragment();
                } else if (id == R.id.nav_buy) {
                    f = new buy();              // <- tu clase 'buy' existe según el árbol
                } else if (id == R.id.nav_sell) {
                    f = new SellFragment();     // <- usa el nombre correcto de la clase
                } else if (id == R.id.nav_account) {
                    f = new account();          // <- tu clase 'account' existe según el árbol
                }

                if (f != null) {
                    replaceFragment(f);
                    return true;
                }
                return false;
            }
        });

        // FAB -> ir a Vender y dejar seleccionado el tab
        View fab = findViewById(R.id.fabCentral);
        if (fab != null) {
            fab.setOnClickListener(v -> {
                replaceFragment(new SellFragment());     // usa la clase correcta
                bottomBar.setSelectedItemId(R.id.nav_sell);
            });
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContainer, fragment)
                .commit();
    }
}
