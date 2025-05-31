package org.grupoTP;

import java.util.List;
import java.util.ArrayList;

public class Wishlist {
    private String title;
    private List<Contenido> contenidos = new ArrayList<>() ;

    public Wishlist(String title) {
        super();
        this.title = title;
    }

    public String getTitle() {
        return title;
        }
    public void setTitle(String title) {
        this.title = title;
        }
    
    public boolean existeWishlist(Cliente cliente, String titulo) {
        for (Wishlist w : cliente.getWishlists()){
            if (w.getTitle().equals(titulo)){
                return true;
            }
        }
        return false;
    }
    
    //Agrega un contenido a la wishlist
    public void agregarContenidoWishlist(Contenido contenido) {
        contenidos.add(contenido);
    }
    //Elimina un contenido a la wishlist 
    public void eliminarContenidoWishlist(Contenido contenido) {
        contenidos.remove(contenido);
    }
}
