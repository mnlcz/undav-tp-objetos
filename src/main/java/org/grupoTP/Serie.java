package org.grupoTP;

import java.time.LocalDateTime;

public class Serie extends Contenido {
    private int temporadas;
    private int capitulos;

    public Serie(String nombre, String tipo, String plataforma, LocalDateTime fechaCreacion, int temporadas, int capitulos) {
        super(nombre, tipo, plataforma, fechaCreacion);
        this.temporadas = temporadas;
        this.capitulos = capitulos;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(int capitulos) {
        this.capitulos = capitulos;
    }
    
}
