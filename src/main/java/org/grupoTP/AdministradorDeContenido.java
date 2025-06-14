package org.grupoTP;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class AdministradorDeContenido {
    public final PlataformaCentral plataformaCentral;

    public AdministradorDeContenido(PlataformaCentral plataformaCentral) {
        this.plataformaCentral = plataformaCentral;
    }

    public void agregarContenido(Contenido contenido) {
        plataformaCentral.getListaContenidos().add(contenido);
    }

    public void eliminarContenido(Contenido contenido) {
        plataformaCentral.getListaContenidos().remove(contenido);
    }

    public boolean fueVisto(Contenido contenido) {
        return contenido.getFechaVisto() != null;
    }

    public void calificarContenido(Cliente usuario, Contenido contenido, float calificacion, String resena) {
        boolean visto = false;
        boolean calificacionValida = false;

        if (fueVisto(contenido) && plataformaCentral.calificacionValida(calificacion)) {
            contenido.setCalificacion(calificacion);
            visto = true;
            calificacionValida = true;
        }
        if (!visto) {
            System.out.println("No se puede calificar algo no visto");
        }
        if (!calificacionValida) {
            System.out.println("Error en calificacion");
        }
        contenido.setResena(usuario, resena);
    }

    public List<Contenido> ultimosVistos(LocalDateTime inicio, LocalDateTime fin, String plataforma) {
        List<Contenido> ultimosVistos = new ArrayList<>();
        for (Contenido contenido : plataformaCentral.getListaContenidos()) {
            if (contenido.getFechaVisto() != null && contenido.getPlataforma().equals(plataforma) &&
                    contenido.getFechaVisto().isAfter(inicio) && contenido.getFechaVisto().isBefore(fin)) {
                ultimosVistos.add(contenido);
            }
        }
        return ultimosVistos;
    }
}
