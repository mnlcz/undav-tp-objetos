package org.grupoTP;

import java.util.List;
import java.util.ArrayList;
// Habría que ver si se inicializa las listas en el constructor o inline (como estaba antes)
public class Cliente {
    private int id;
    private String user;
    private String password;
    private List<Cliente> friends;
    private List<Contenido> seen;
    private List<Wishlist> wishlists;

    public Cliente(String user, String password){
        super();
        this.user = user;
        this.password = password;
        this.friends = new ArrayList<>();
        this.seen = new ArrayList<>();
        this.wishlists = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Cliente> getFriends() {
        return friends;
    }
    
    public List<Wishlist> getWishlists() {
        return this.wishlists;
    }

    public List<Contenido> getSeen() {
        return this.seen;
    }

    //Agrega el amigo por parametro a la lista de amigos del cliente
    public void agregarAmigo(Cliente amigo) {
        friends.add(amigo);
    }

    public boolean eliminarAmigo(Cliente amigo) {
        boolean amigoEliminado = false;
        if (friends.contains(amigo)) {
            amigoEliminado = true;
            friends.remove(amigo);
        }
        else{
            System.out.println("No se pudo eliminar. No compartes amistad con ese usuario.");
        }
        return amigoEliminado;
    }
}
