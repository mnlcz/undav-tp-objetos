package org.grupoTP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

public class AdministradorDeContenidoTest {

    private PlataformaCentral plataformaCentral;
    private AdministradorDeContenido adminDeContenido;
    private Pelicula pelicula1;
    private Libro libro1;

    @BeforeEach
    void setUp(){
        plataformaCentral = new PlataformaCentral();
        adminDeContenido = new AdministradorDeContenido(plataformaCentral);
        pelicula1 = new Pelicula("La materia: POO", "Pelicula", "Netflix", LocalDateTime.now(), 148);
        libro1 = new Libro("1984", "Libro", "Kindle", LocalDateTime.now(), 328);
    }

    @Test
    void testAgregarContenido(){
        adminDeContenido.agregarContenido(pelicula1);
        assertTrue(plataformaCentral.getListaContenidos().contains(pelicula1), "El contenido debería haberse agregado a la plataforma");
        assertEquals(1, plataformaCentral.getListaContenidos().size(), "La lista de contenidos debería tener un elemento");
    }

    @Test
    void testEliminarContenido(){
        adminDeContenido.agregarContenido(pelicula1);
        adminDeContenido.agregarContenido(libro1);
        adminDeContenido.eliminarContenido(pelicula1);
        assertFalse(plataformaCentral.getListaContenidos().contains(pelicula1), "El contenido debería haber sido eliminado");
        assertTrue(plataformaCentral.getListaContenidos().contains(libro1), "Deberia quedar este elemento");
        assertEquals(1, plataformaCentral.getListaContenidos().size(), "La lista de contenidos debería tener un elemento después de eliminar uno");
    }

    void testCalificarContenido(){
        adminDeContenido.calificarContenido(libro1, 4.0f, "Excelente libro. No le doy 5 estrellas porque es muy largo");
        assertEquals(4.5f, libro1.getCalificacion(), "La calificacion fue colocada correctamente");
        assertTrue(libro1.getResenas().contains("Excelente libro. No le doy 5 estrellas porque es muy largo"), "La reseña fue agregada correctamente");
    }

    @Test
    void testFueVisto(){
        assertFalse(adminDeContenido.fueVisto(pelicula1), "Contenido nuevo no debería estar marcado como visto");
        pelicula1.setFechaVisto(LocalDateTime.now());
        assertTrue(adminDeContenido.fueVisto(pelicula1), "Contenido con fechaVisto debería estar marcado como visto");
    }

    @Test
    void testUltimosVistos(){
        //Puede que sea confuso. La pelicula se crea ayer 
        Contenido peliculaVistaHoy = new Pelicula("Tenet", "Pelicula", "HBO", LocalDateTime.now().minusDays(1), 150);
        //Aca es cuando yo la veo
        peliculaVistaHoy.setFechaVisto(LocalDateTime.now().minusHours(1));
        adminDeContenido.agregarContenido(peliculaVistaHoy);

        LocalDateTime inicioRango = LocalDateTime.now().minusDays(1);
        LocalDateTime finRango = LocalDateTime.now();

        List<Contenido> vistosHBO = adminDeContenido.ultimosVistos(inicioRango, finRango, "HBO");
        assertEquals(1, vistosHBO.size(), "Debería haber una película vista de HBO");
        assertTrue(vistosHBO.contains(peliculaVistaHoy), "La lista de últimos vistos de HBO debería contener 'Tenet'");

        List<Contenido> vistosNetflix = adminDeContenido.ultimosVistos(inicioRango, finRango, "Netflix");
        assertTrue(vistosNetflix.isEmpty(), "No debería haber contenidos vistos de Netflix en el rango");
    }
}
