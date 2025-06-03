package org.grupoTP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;


public class PeliculaTest {

    @Test
    public void testConstructorYGetters() {
        LocalDateTime fecha = LocalDateTime.of(2015, 5, 10, 0, 0);
        Pelicula p = new Pelicula("Origen", "Pelicula", "HBO", fecha, 120f);

        // Verificamos los campos heredados
        assertEquals("Origen", p.getNombre());
        assertEquals("Pelicula", p.getTipo());
        assertEquals("HBO", p.getPlataforma());
        assertEquals(fecha, p.getFechaCreacion());
        // Verificamos el campo especifico de Pelicula
        assertEquals(120f, p.getDuracion(), 0.0001);
        // Promedio de calificaciones inicial debe ser 0
        assertEquals(Float.valueOf(0f), p.getCalificacion());
        // No hay resenias al inicio
        assertTrue(p.getResenas().isEmpty());
    }

    @Test
    public void testSettersYCalificacionesYResenas() {
        LocalDateTime ahora = LocalDateTime.now();
        Pelicula p = new Pelicula("TestPeli", "Pelicula", "Netflix", ahora, 90f);

        // Modificamos duracion y verificamos
        p.setDuracion(95f);
        assertEquals(95f, p.getDuracion(), 0.0001);

        // Modificamos los campos heredados
        p.setNombre("NuevaPeli");
        assertEquals("NuevaPeli", p.getNombre());

        p.setTipo("Documental");
        assertEquals("Documental", p.getTipo());

        p.setPlataforma("Amazon");
        assertEquals("Amazon", p.getPlataforma());

        // Probamos calificaciones: agregamos dos y verificar promedio
        p.setCalificacion(3.0f);
        p.setCalificacion(4.0f);
        float esperado = (3.0f + 4.0f) / 2;
        assertEquals(esperado, p.getCalificacion());

        // Probar resenias
        p.setResena("Peor que Riquelme en boca");
        List<String> resenas = p.getResenas();
        assertEquals(1, resenas.size());
        assertTrue(resenas.contains("Peor que Riquelme en boca"));
    }
}