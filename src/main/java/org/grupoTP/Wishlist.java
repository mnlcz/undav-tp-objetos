package org.grupoTP;

import java.util.List;

public class Wishlist {
    private String title;
    public List<Contenido> contenidos;

    public void crear(String titulo) {
    }

    public boolean existeWishlist(String titulo) {
        return false;
    }

    public void agregarContenidoWishlist(Contenido contenido) {
        contenidos.add(contenido);
    }

    public void eliminarContenidoWishlist(Contenido contenido) {
        contenidos.remove(contenido);
    }
}
