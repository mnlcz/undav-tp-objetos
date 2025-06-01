package org.grupoTP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

public class AdministradorDeContenidoTest {

    private PlataformaCentral plataformaCentral;
    private AdministradorDeContenido adminContenido;
    private Pelicula pelicula1;
    private Libro libro1;

    @BeforeEach
    void setUp() {
        plataformaCentral = new PlataformaCentral();
        adminContenido = new AdministradorDeContenido(plataformaCentral);
        pelicula1 = new Pelicula("Inception", "Pelicula", "Netflix", LocalDateTime.now(), 148);
        libro1 = new Libro("1984", "Libro", "Kindle", LocalDateTime.now().minusDays(10), 328);
    }

    @Test
    void testAgregarContenido() {
        adminContenido.agregarContenido(pelicula1);
        assertTrue(plataformaCentral.getListaContenidos().contains(pelicula1), "El contenido debería haber sido agregado a la plataforma.");
        assertEquals(1, plataformaCentral.getListaContenidos().size(), "La lista de contenidos debería tener un elemento.");
    }

    @Test
    void testEliminarContenido() {
        adminContenido.agregarContenido(pelicula1);
        adminContenido.agregarContenido(libro1);
        adminContenido.eliminarContenido(pelicula1);
        assertFalse(plataformaCentral.getListaContenidos().contains(pelicula1), "El contenido debería haber sido eliminado.");
        assertTrue(plataformaCentral.getListaContenidos().contains(libro1), "El otro contenido no debería haber sido eliminado.");
        assertEquals(1, plataformaCentral.getListaContenidos().size(), "La lista de contenidos debería tener un elemento después de eliminar uno.");
    }

    void testCalificarContenido() {
        adminContenido.calificarContenido(libro1, 4.5f, "Excelente libro distópico.");
        assertEquals(4.5f, libro1.getCalificacion(), "La calificacion no fue colocada correctamente.");
        assertTrue(libro1.getResenas().contains("Excelente libro distópico."), "La reseña no fue seteada correctamente.");
    }

    @Test
    void testFueVisto() {
        assertFalse(adminContenido.fueVisto(pelicula1), "Contenido nuevo no debería estar marcado como visto.");
        pelicula1.setFechaVisto(LocalDateTime.now());
        assertTrue(adminContenido.fueVisto(pelicula1), "Contenido con fechaVisto debería estar marcado como visto.");
    }

    @Test
    void testUltimosVistos() {
        Contenido peliculaVistaHoy = new Pelicula("Tenet", "Pelicula", "HBO", LocalDateTime.now().minusDays(1), 150);
        peliculaVistaHoy.setFechaVisto(LocalDateTime.now().minusHours(1));
        adminContenido.agregarContenido(peliculaVistaHoy);

        LocalDateTime inicioRango = LocalDateTime.now().minusDays(1);
        LocalDateTime finRango = LocalDateTime.now();

        List<Contenido> vistosHBO = adminContenido.ultimosVistos(inicioRango, finRango, "HBO");
        assertEquals(1, vistosHBO.size(), "Debería haber una película vista de HBO en el rango.");
        assertTrue(vistosHBO.contains(peliculaVistaHoy), "La lista de últimos vistos de HBO debería contener 'Tenet'.");

        List<Contenido> vistosNetflix = adminContenido.ultimosVistos(inicioRango, finRango, "Netflix");
        assertTrue(vistosNetflix.isEmpty(), "No debería haber contenidos vistos de Netflix en el rango.");
    }
}
