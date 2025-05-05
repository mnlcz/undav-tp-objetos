package org.grupoTP;

import java.time.LocalDateTime;

public class AdministradorDeContenido {
    public void agregarContenido(String nombre, String tipo, LocalDateTime fechaCreacion) {
    }

    public boolean fueVisto(String nombre, String plataforma) {
        throw new UnsupportedOperationException("Falta implementar");
    }

    public void verContenido(String nombre, LocalDateTime fecha, String plataforma) {
    }

    public void calificarContenido(String nombre, float puntaje, String resena) {
    }

    public void ultimosVistos(LocalDateTime desde, LocalDateTime hasta, String plataforma) {
    } // print ultimos 5 vistos
}
