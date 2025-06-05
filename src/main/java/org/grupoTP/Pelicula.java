package org.grupoTP;

import java.time.LocalDateTime;

public class Pelicula extends Contenido {
    private float duracion;

    public Pelicula(String nombre, String tipo, String plataforma, LocalDateTime fechaCreacion, float duracion) {
        super(nombre, tipo, plataforma, fechaCreacion);
        this.duracion = duracion;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }
}

