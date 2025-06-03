package org.grupoTP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

public class LibroTest {
    @Test
    public void testConstructorYGetters() {
        LocalDateTime fecha = LocalDateTime.of(2020, 1, 1, 0, 0);
        Libro libro = new Libro("Microservicios", "Libro", "Amazon", fecha, 300);

        // Verificamos los campos heredados
        assertEquals("Microservicios", libro.getNombre());
        assertEquals("Libro", libro.getTipo());
        assertEquals("Amazon", libro.getPlataforma());
        assertEquals(fecha, libro.getFechaCreacion());
        // Verificar campo específico de Libro
        assertEquals(300, libro.getPaginas());
        // Promedio de calificaciones inicial debe ser 0
        assertEquals(Float.valueOf(0f), libro.getCalificacion());
        // Verificamos que no hayan reseñas al inicio
        assertTrue(libro.getResenas().isEmpty());
    }

    @Test
    public void testSettersYCalificacionesYResenas() {
        LocalDateTime ahora = LocalDateTime.now();
        Libro libro = new Libro("Test", "Libro", "Plataforma", ahora, 100);

        // Modificamos paginas y verificamos
        libro.setPaginas(150);
        assertEquals(150, libro.getPaginas());

        // Modificamos los campos heredados
        libro.setNombre("NuevoNombre");
        assertEquals("NuevoNombre", libro.getNombre());

        libro.setTipo("OtroTipo");
        assertEquals("OtroTipo", libro.getTipo());

        libro.setPlataforma("Netflix");
        assertEquals("Netflix", libro.getPlataforma());

        // Probamos las calificaciones: agregar dos y verificar promedio
        libro.setCalificacion(4.0f);
        libro.setCalificacion(5.0f);
        float esperado = (4.0f + 5.0f) / 2;
        assertEquals(esperado, libro.getCalificacion());

        // Probar resenias
        libro.setResena("Excelente libro");
        libro.setResena("Muy didactico");
        List<String> resenas = libro.getResenas();
        assertEquals(2, resenas.size());
        assertTrue(resenas.contains("Excelente libro"));
        assertTrue(resenas.contains("Muy didactico"));
    }
}