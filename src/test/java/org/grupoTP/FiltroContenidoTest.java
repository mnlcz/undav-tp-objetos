package org.grupoTP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

public class FiltroContenidoTest {

    /*1. Si no se configuran filtros, cualquier Contenido debe cumplir y devolver true*/
    @Test
    public void testCumpleSinFiltros() {
        FiltroContenido filtro = new FiltroContenido();
        // Creo un contenido cualquiera: un libro con fecha 2020-01-01
        Libro libro = new Libro("Las historias de Fabra","Libro","Amazon",
            LocalDateTime.of(2020, 1, 1, 0, 0),200);
        // No se agregan calificaciones ni filtros: cumple() debe ser true
        assertTrue(filtro.cumple(libro));
    }

    /*2. Prueba de filtrado solo por tipo:
        Si tipo = Libro, un Libro debería pasar; una Pelicula no.*/
    @Test
    public void testFiltroPorTipo() {
        FiltroContenido filtro = new FiltroContenido();
        filtro.setTipo("Libro");

        Libro libro = new Libro("Estructuras de Datos","Libro","CampusUNDAV",
            LocalDateTime.of(2019, 9, 1, 0, 0),350);
        Pelicula pelicula = new Pelicula("Interestelar","Pelicula","Netflix",
            LocalDateTime.of(2014, 11, 7, 0, 0),169f);

        assertTrue(filtro.cumple(libro),"Un libro debe pasar cuando tipo = Libro");
        assertFalse(filtro.cumple(pelicula), "Una pelicula NO debe pasar si tipo = Libro");
    }

    /*3. Filtrado solo por plataforma:
        Si plataforma = Netflix, la pelicula que tenga plataforma = Netflix pasa.
        Si es distinta plataforma, no pasa.*/
    @Test
    public void testFiltroPorPlataforma() {
        FiltroContenido filtro = new FiltroContenido();
        filtro.setPlataforma("Netflix");

        Pelicula p = new Pelicula( "El Origen","Pelicula","Netflix",
            LocalDateTime.of(2010, 7, 16, 0, 0),148f);
        Libro l = new Libro("Las aventuras de Mickey Mouse","Libro","Disney",
            LocalDateTime.of(2008, 8, 1, 0, 0),464);

        assertTrue(filtro.cumple(p),"Debería pasar la película de Netflix");
        assertFalse(filtro.cumple(l), "No debería pasar el libro de Prentice Hall");
    }

    /*4. Prueba de filtrado por puntaje minimo:
        Se asignan calificaciones al contenido.
        El promedio se compara con puntajeMin.*/
    @Test
    public void testFiltroPorPuntajeMinimo() {
        FiltroContenido filtro = new FiltroContenido();
        filtro.setPuntajeMin(4.5f);

        // Creo un libro y le agrego calificaciones: 4.0, 5.0 → promedio = 4.5
        Libro libro = new Libro("Patrones de Diseño","Libro","Pingorcho",
            LocalDateTime.of(1994, 10, 21, 0, 0),395);
        libro.setCalificacion(4.0f);
        libro.setCalificacion(5.0f);
        //promedio = (4.0 + 5.0) / 2 = 4.5 → cumple >= 4.5
        assertTrue(filtro.cumple(libro), "Promedio 4.5 >= puntajeMin 4.5");

        filtro.setPuntajeMin(4.6f);
        assertFalse(filtro.cumple(libro), "Promedio 4.5 < puntajeMin 4.6");
    }

    /* 5. Prueba de filtrado por fecha minima:
        Si fechaCreacion < fechaMin, no cumple.*/
    @Test
    public void testFiltroPorFechaMinima() {
        FiltroContenido filtro = new FiltroContenido();
        // Establezco fechaMin en 2021-01-01
        LocalDateTime fechaMin = LocalDateTime.of(2021, 1, 1, 0, 0);
        filtro.setFechaMin(fechaMin);
        // Contenido creado el 2020-12-31 → antes de fechaMin → no cumple
        Serie serieVieja = new Serie("Los Picapiedras","Serie","HBO",
            LocalDateTime.of(1900, 12, 31, 23, 59),3,20);
        // Contenido creado el 2021-05-01 → despues de fechaMin → cumple
        Serie serieNueva = new Serie("Casados Con Hijos","Serie","HBO",
            LocalDateTime.of(2021, 5, 1, 12, 0),1,10);

        assertFalse(filtro.cumple(serieVieja),"Contenido con fechaCreacion < fechaMin no deberia cumplir");
        assertTrue(filtro.cumple(serieNueva),"Contenido con fechaCreacion ≥ fechaMin deberia cumplir");
    }
}