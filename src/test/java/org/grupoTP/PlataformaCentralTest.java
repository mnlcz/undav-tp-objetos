package org.grupoTP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

public class PlataformaCentralTest {

    @Test
    public void testExisteContenidoVacio() {
        PlataformaCentral plataforma = new PlataformaCentral();
        Libro libro = new Libro(
            "Algoritmos", "Libro", "Amazon",
            LocalDateTime.of(2018, 3, 15, 0, 0), 500
);
        // Como aun no agrego nada a plataforma, existeContenido debe ser false
        assertFalse(plataforma.existeContenido(libro));
    }

    @Test
    public void testExisteContenidoDespuesDeAgregar() {
        PlataformaCentral plataforma = new PlataformaCentral();
        Pelicula pelicula = new Pelicula(
            "Buscando la Carroza", "Pelicula", "HBO",
            LocalDateTime.of(2006, 10, 20, 0, 0),130f);

        // Agrego la pelicula directamente a la lista interna
        List<Contenido> listaInterna = plataforma.getListaContenidos();
        listaInterna.add(pelicula);

        // Ahora que existe en listaInterna, debe devolver true
        assertTrue(plataforma.existeContenido(pelicula));

        // Pero si creo un objeto distinto con mismo nombre pero otra plataforma, no debe pasar
        Pelicula falso = new Pelicula(
            "Buscando la Carroza", "Pelicula", "Netflix",
            LocalDateTime.of(2006, 10, 20, 0, 0),130f);
        assertFalse(plataforma.existeContenido(falso));
    }

    @Test
    public void testCalificacionValida() {
        PlataformaCentral plataforma = new PlataformaCentral();
        assertFalse(plataforma.calificacionValida(-1.0f),"Puntaje -1 no es valido");
        assertTrue(plataforma.calificacionValida(0.0f), "Puntaje 0 es valido");
        assertTrue(plataforma.calificacionValida(3.5f), "Puntaje 3.5 es valido");
        assertTrue(plataforma.calificacionValida(5.0f), "Puntaje 5.0 es valido");
        assertFalse(plataforma.calificacionValida(5.1f), "Puntaje 5.1 no es valido");
    }

    @Test
    public void testMarcarVisto() {
        PlataformaCentral plataforma = new PlataformaCentral();
        Cliente cliente = new Cliente("sofia", "sofia123");
        Libro libro = new Libro("Estructura de Datos","Libro", "Mastantuono",
            LocalDateTime.of(2022, 2, 2, 0, 0),450);

        // Antes de marcar visto, fechaVisto deberia ser null y lista seen vacia
        assertNull(libro.getFechaVisto(), "FechaVisto inicial debe ser null");
        assertTrue(cliente.getSeen().isEmpty(),"El cliente no deberia haber visto nada al inicio");

        // Llamo a marcarVisto
        LocalDateTime now = LocalDateTime.now();
        plataforma.marcarVisto(cliente, libro, now);

        // Despues de marcarlo como visto:
        assertEquals(libro.getFechaVisto(), now, "La fechaVisto del contenido debe coincidir con la fecha pasada");
        assertTrue(cliente.getSeen().contains(libro),"El cliente debe contener ese contenido en su lista seen");
    }
}