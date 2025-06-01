package org.grupoTP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class WishlistTest {

    private Wishlist wishlist;
    private Cliente cliente;
    private Contenido pelicula1;

    private ByteArrayInputStream inputPrueba;

    @BeforeEach
    void setUp() {
        wishlist = new Wishlist("Favoritos");
        cliente = new Cliente(1, "POO", "Fernando");
        pelicula1 = new Pelicula("La materia: POO", "Pelicula", "Netflix", LocalDateTime.now(), 148);
    }

    private void simulacionInput(String data) {
        inputPrueba = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputPrueba);
    }

    @Test
    void agregarContenidoWishlist_AgregaContenido() {
        wishlist.agregarContenidoWishlist(pelicula1);
        assertTrue(wishlist.eliminarContenidoWishlist(pelicula1),
                   "Eliminar debería ser exitoso si el contenido fue agregado previamente.");
    }

    @Test
    void eliminarContenidoWishlist_ContenidoExiste() {
        wishlist.agregarContenidoWishlist(pelicula1);
        assertTrue(wishlist.eliminarContenidoWishlist(pelicula1),
                   "Debería retornar true si el contenido existía y fue eliminado.");
    }

    @Test
    void eliminarContenidoWishlist_ContenidoNoExiste() {
        assertFalse(wishlist.eliminarContenidoWishlist(pelicula1),
                    "Debería retornar false si el contenido no está en la wishlist.");
    }

    @Test
    void existeWishlist_ExisteEnCliente() {
        Wishlist clienteWishlist = new Wishlist("Lista del Cliente");
        cliente.getWishlists().add(clienteWishlist); 

        assertTrue(wishlist.existeWishlist(cliente, "Lista del Cliente"),
                   "Debería retornar true si la wishlist con ese título existe en las listas del cliente.");
    }

    @Test
    void existeWishlist_NoExisteEnCliente() {
        simulacionInput("S\n");
        boolean existe = wishlist.existeWishlist(cliente, "Otra Lista Nueva");

        assertFalse(existe, "Debería retornar false ya que la wishlist no existe inicialmente.");
        assertEquals(1, cliente.getWishlists().size(), "Se debería haber agregado una wishlist al cliente.");
        assertEquals("Otra Lista Nueva", cliente.getWishlists().get(0).getTitle(), "La wishlist agregada debería tener el título correcto.");
    }

}
