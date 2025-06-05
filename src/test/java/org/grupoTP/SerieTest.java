package org.grupoTP;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;


public class SerieTest {

    @Test
    public void testConstructorYGetters() {
        LocalDateTime fecha = LocalDateTime.of(2018, 8, 20, 0, 0);
        Serie s = new Serie("Breaking Bad", "Serie", "Netflix", fecha, 5, 62);

        // Verificar los campos heredados
        assertEquals("Breaking Bad", s.getNombre());
        assertEquals("Serie", s.getTipo());
        assertEquals("Netflix", s.getPlataforma());
        assertEquals(fecha, s.getFechaCreacion());
        // Verificar los campos especificos de Serie
        assertEquals(5, s.getTemporadas());
        assertEquals(62, s.getCapitulos());
        // Promedio de calificaciones inicial debe ser 0
        assertEquals(Float.valueOf(0f), s.getCalificacion());
        // No hay resenias al inicio
        assertTrue(s.getResenas().isEmpty());
    }

    @Test
    public void testSettersYCalificacionesYResenas() {
        LocalDateTime ahora = LocalDateTime.now();
        Serie s = new Serie("TestSerie", "Serie", "HBO", ahora, 1, 10);

        // Modificar temporadas y capitulos, y verificar
        s.setTemporadas(2);
        assertEquals(2, s.getTemporadas());

        s.setCapitulos(20);
        assertEquals(20, s.getCapitulos());

        // Modificar campos heredados
        s.setNombre("NuevaSerie");
        assertEquals("NuevaSerie", s.getNombre());

        s.setTipo("Miniserie");
        assertEquals("Miniserie", s.getTipo());

        s.setPlataforma("Disney");
        assertEquals("Disney", s.getPlataforma());

        // Probar calificaciones: agregar dos y verificar promedio
        s.setCalificacion(2.0f);
        s.setCalificacion(3.0f);
        float esperado = (2.0f + 3.0f) / 2;
        assertEquals(esperado, s.getCalificacion());
    }
}