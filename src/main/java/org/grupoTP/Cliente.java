package org.grupoTP;

import java.util.List;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Cliente {
    private int id;
    private String user;
    private String password;
    private List<Cliente> friends;
    private List<Contenido> seen;
    private List<Wishlist> wishlists;
    private String salt;

    public Cliente(String user, String password) {
        this.user = user;
        handleSecurePassword(password);
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
        handleSecurePassword(password);
    }

    public List<Cliente> getFriends() {
        return friends;
    }

    public List<Wishlist> getWishlists() {
        return this.wishlists;
    }

    public void addWishlist(Wishlist wl) {
        this.wishlists.add(wl);
    }

    public List<Contenido> getSeen() {
        return this.seen;
    }

    public void agregarAmigo(Cliente amigo) {
        friends.add(amigo);
        amigo.friends.add(this); // TODO: test
    }

    public boolean eliminarAmigo(Cliente amigo) {
        boolean amigoEliminado = false;
        if (friends.contains(amigo)) {
            amigoEliminado = true;
            friends.remove(amigo);
            amigo.friends.remove(this); // TODO: test
        } else {
            System.out.println("No se pudo eliminar. No compartes amistad con ese usuario.");
        }
        return amigoEliminado;
    }

    /*
     * Extra: password hashing
     */
    private void handleSecurePassword(String password) {
        this.salt = generateSalt();
        this.password = hashPassword(password, salt);
    }

    private String generateSalt() {
        var random = new SecureRandom();
        var salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password, String salt) {
        try {
            var digest = MessageDigest.getInstance("SHA-256");
            digest.update(Base64.getDecoder().decode(salt));
            var hashedBytes = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al encriptar la clave", e);
        }
    }

    public boolean verificarPassword(String password) {
        var candidate = hashPassword(password, this.salt);
        return candidate.equals(this.password);
    }

    @Override
    public String toString() {
        return "Nombre: " + this.user + "\t\tAmigos: " + this.friends.size();
    }
}
