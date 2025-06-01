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

    public Cliente(int id, String user, String password) {
        super();
        this.id = id;
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

    public List<Cliente> getFriends() {
        return friends;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void agregarAmigo(Cliente amigo) {
        //Agrega el amigo por parametro a la lista de amigos del cliente
        friends.add(amigo);
    }

    public boolean eliminarAmigo(Cliente amigo) {
        // Forma alternativa por si no funciona la otra
//        if (friends.stream().anyMatch(cliente -> cliente.id == amigo.id)) {
//            friends.remove(amigo);
//        }
        if (friends.contains(amigo)) {
            friends.remove(amigo);
            return true;
        } else {
            System.out.println("No se pudo eliminar. No compartes amistad con ese usuario.");
            return false;
        }
    }

    public List<Wishlist> getWishlists() {
        return this.wishlists;
    }

    public List<Contenido> getSeen() {
        return this.seen;
    }
}

