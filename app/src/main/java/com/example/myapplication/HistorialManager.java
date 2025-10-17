package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class HistorialManager {

    // Lista estática que simula la base de datos
    private static final List<PublicacionAdapter.PublicacionVM> publicaciones = new ArrayList<>();

    public static void agregar(PublicacionAdapter.PublicacionVM p) {
        publicaciones.add(p);
    }

    public static List<PublicacionAdapter.PublicacionVM> obtenerTodas() {
        return new ArrayList<>(publicaciones);
    }

    public static void limpiar() {
        publicaciones.clear();
    }
}
