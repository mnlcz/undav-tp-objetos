package org.grupoTP;

import java.time.LocalDateTime;

public class Podcast extends Contenido {
    private float duracion;
    private int episodios;

    public Podcast(String nombre, String tipo, String plataforma, LocalDateTime fechaCreacion, float duracion, int episodios) {
        super(nombre, tipo, plataforma, fechaCreacion);
        this.duracion = duracion;
        this.episodios = episodios;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public int getEpisodios() {
        return episodios;
    }

    public void setEpisodios(int episodios) {
        this.episodios = episodios;
    }
}
