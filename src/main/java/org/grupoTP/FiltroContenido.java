package org.grupoTP;

import java.time.LocalDateTime;

public class FiltroContenido {
    //Filtra contenido segun parametros enviados
    private String tipo;
    private String plataforma;
    private Float puntajeMin;
    private LocalDateTime fechaMin;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Float getPuntajeMin() {
        return puntajeMin;
    }

    public void setPuntajeMin(Float puntajeMin) {
        this.puntajeMin = puntajeMin;
    }

    public LocalDateTime getFechaMin() {
        return fechaMin;
    }

    public void setFechaMin(LocalDateTime fechaMin) {
        this.fechaMin = fechaMin;
    }

    // evalua si un contenido cumple con el filtro
    public boolean cumple(Contenido contenido) {
        if (tipo != null && !tipo.equals(contenido.getTipo())) return false;
        if (plataforma != null && !plataforma.equals(contenido.getPlataforma())) return false;
        if (puntajeMin != null && contenido.getCalificacion() < puntajeMin) return false;
        if (fechaMin != null && contenido.getFechaCreacion().isBefore(fechaMin)) return false;
        return true;
    }
}
