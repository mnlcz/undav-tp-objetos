package org.grupoTP;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class PlataformaCentral {
    private List<Contenido> listaContenidos = new ArrayList<>();

    public boolean existeContenido(Contenido contenido) {
        for (Contenido c : listaContenidos) {
            if (contenido.getNombre().equals(c.getNombre()) && contenido.getPlataforma().equals(c.getPlataforma())) {
                return true;
            }
        }
        return false;
    }

    public boolean calificacionValida(float puntaje) {
        return puntaje >= 0.0f && puntaje <= 5.0f;
    }


    public void marcarVisto(Cliente cliente, Contenido contenido, LocalDateTime fecha) {
        contenido.setFechaVisto(fecha);
        cliente.getSeen().add(contenido);
    }

    public List<Contenido> getListaContenidos() {
        return listaContenidos;
    }

    public Optional<Contenido> getContenido(String nombre) {
        return getListaContenidos()
                .stream()
                .filter(contenido -> contenido.getNombre().equals(nombre))
                .findFirst();
    }
}
