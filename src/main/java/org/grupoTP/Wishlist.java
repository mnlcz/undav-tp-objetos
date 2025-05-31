package org.grupoTP;

import java.util.Scanner;
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
        boolean existe = false;
        for (Wishlist w : cliente.getWishlists()){
            if (w.getTitle().equals(titulo)){
                existe = true;
            }
        }
        if (!existe){
            Scanner scanner  = new Scanner(System.in);
            System.out.println("Deseas agregarlo? S/N");
            String decision = scanner.nextLine();
            if(decision == "s" || decision == "S"){
                cliente.getWishlists().add(new Wishlist(titulo));
            }
            scanner.close(); //una vez que no usamos mas el scanner, lo cerramos
        }
        return existe;
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
