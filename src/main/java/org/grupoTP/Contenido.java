package org.grupoTP;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Map;

public abstract class Contenido {
    private String nombre;
    private String tipo;
    private List<Float> calificaciones = new ArrayList<>();
    private Map<Cliente, String> resenas = new Hashtable<>();
    private String plataforma;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaVisto;

    protected Contenido(String nombre, String tipo, String plataforma, LocalDateTime fechaCreacion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.plataforma = plataforma;
        this.fechaCreacion = fechaCreacion;
        this.fechaVisto = null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Float getCalificacion() {
        if (calificaciones.isEmpty()) {
            return 0f;
        } else {
            Float calificacionTotal = 0f;
            for (Float calificacion : calificaciones) {
                calificacionTotal += calificacion;
            }
            return calificacionTotal / calificaciones.size();
        }
    }

    public void setCalificacion(Float calificacion) {
        calificaciones.add(calificacion);
    }

    public Map<Cliente, String> getResenas() {
        return resenas;
    }

    public void setResena(Cliente user, String resena) {
        resenas.put(user, resena);
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaVisto() {
        return fechaVisto;
    }

    public void setFechaVisto(LocalDateTime fechaVisto) {
        this.fechaVisto = fechaVisto;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + "\t\tTipo: " + this.tipo + "\t\tPlataforma: " + this.plataforma;
    }
}
