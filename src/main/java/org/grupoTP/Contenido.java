package org.grupoTP;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

// Habría que ver como manejar la construcción de c/subclase. Creo q con constructores individuales
public abstract class Contenido {
    private String nombre;
    private String tipo;
    private List<Float> puntajes= new ArrayList<>();
    private List<String> resenas = new ArrayList<>();
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

    public Float getPuntaje() {
        if (puntajes.isEmpty()) {
        return 0.0f; 
    }
        Float puntajeTotal = 0f;
        for (Float puntaje : puntajes) {
            puntajeTotal += puntaje;
        }
        return puntajeTotal / puntajes.size();
    }
    
    public void setPuntaje(Float puntaje) {
        puntajes.add(puntaje);
    }

    public List<String> getResenas() { 
        return new ArrayList<>(resenas); 
    }
    
    public void setResena(String resena) {
        resenas.add(resena);
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
}
