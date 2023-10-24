package com.example.diseno_interfaces;

import com.example.diseno_interfaces.models.Receta;

public class Session {
    private static Receta recetaActual = null;

    public static Receta getRecetaActual() {
        return recetaActual;
    }

    public static void setRecetaActual(Receta recetaActual) {
        Session.recetaActual = recetaActual;
    }
}
