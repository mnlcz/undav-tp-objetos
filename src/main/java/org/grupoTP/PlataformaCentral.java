package org.grupoTP;

import java.time.LocalDateTime;
import java.util.List;

// Falta el constructor
public class PlataformaCentral {
    public List<Contenido> listaContenidos;

    public boolean existeContenido(String nombre, String plataforma) {
        for (Contenido c : listaContenidos) {
            if (nombre.equals(c.getNombre()) && (plataforma.equals(c.getPlataforma()))) return true;
        }
        return false;
    }

    public boolean calificacionValida(float puntaje) {
        if (puntaje > 0 && puntaje < 5) return true;
        return false;
    }

    public void marcarVisto(String nombre, LocalDateTime fecha, String plataforma) {
    }
}
