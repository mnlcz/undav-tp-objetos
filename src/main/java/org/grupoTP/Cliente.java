package org.grupoTP;

import java.util.List;

// Habría que ver si se inicializa las listas en el constructor o inline (como estaba antes)
public class Cliente {
    private int id;
    private String user;
    private String password;
    private List<Cliente> friends;
    private List<Contenido> seen;
    private List<Wishlist> wishlists;

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

    public void agregarAmigo(Cliente amigo) {
        friends.add(amigo);
    }

    public void eliminarAmigo(Cliente amigo) {
        // Forma alternativa por si no funciona la otra
//        if (friends.stream().anyMatch(cliente -> cliente.id == amigo.id)) {
//            friends.remove(amigo);
//        }
        if (friends.contains(amigo)) {
            friends.remove(amigo);
        } else {
            System.out.println("No se pudo eliminar. No compartes amistad con ese usuario.");
        }
    }
}
