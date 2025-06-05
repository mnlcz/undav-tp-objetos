package org.grupoTP;

import java.time.LocalDateTime;

public class Libro extends Contenido {
    private int paginas;

    public Libro(String nombre, String tipo, String plataforma, LocalDateTime fechaCreacion, int paginas) {
        super(nombre, tipo, plataforma, fechaCreacion);
        this.paginas = paginas;
    }

    public int getPaginas() {
    return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }
}

