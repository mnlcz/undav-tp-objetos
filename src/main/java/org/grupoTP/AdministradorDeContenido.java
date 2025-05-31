package org.grupoTP;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class AdministradorDeContenido {
    private PlataformaCentral plataformaCentral;
    public AdministradorDeContenido(PlataformaCentral plataformaCentral){
        this.plataformaCentral = plataformaCentral;
    }

    public void agregarContenido(Contenido contenido) {
        //Agrega contenido a PlataformaCentral
        plataformaCentral.getListaContenidos().add(contenido);
    }

    public void eliminarContenido(Contenido contenido) {
        //Elimina contenido de PlataformaCentral
        plataformaCentral.getListaContenidos().remove(contenido);

    }

    public boolean fueVisto(Contenido contenido) {
        return contenido.getFechaVisto() != null;
    }

    public void calificarContenido(Contenido contenido, float calificacion, String resena) {
        contenido.setCalificacion(calificacion);
        contenido.setResena(resena);
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
