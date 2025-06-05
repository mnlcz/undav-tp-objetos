package org.grupoTP;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GestorClientes {
    private List<Cliente> listaClientes = new ArrayList<>();

    public void registrarCliente(String user, String password) {
        int nuevoId = listaClientes.size() + 1;
        Cliente nuevoCliente = new Cliente(user, password);
        nuevoCliente.setId(nuevoId);
        listaClientes.add(nuevoCliente);
        System.out.println("Cliente " + user + " registrado.");
    }

    //Elimina la cuenta que llama al metodo de GestorClientes
    public boolean eliminarCuenta(String user) {
        //esto lo va a utilizar un cliente. Si el usuario del cliente
        //coincide con el usuario enviado en el parametro, se elimina
        return listaClientes.removeIf(cliente -> cliente.getUser().equals(user));
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public Optional<Cliente> existe(String nombre) {
        return getListaClientes().stream().filter(cliente -> cliente.getUser().equals(nombre)).findFirst();
    }

    public Cliente getCliente(String nombre) {
        var existe = existe(nombre);
        return existe.orElse(null);
    }

    public Optional<Wishlist> getWishlist(Cliente usuario, String titulo) {
        return usuario.getWishlists()
                .stream()
                .filter(w -> w.getTitle().equals(titulo))
                .findFirst();
    }

    // La validación se hace en App
    public void agregarWishlist(Cliente usuario, String titulo) {
        usuario.addWishlist(new Wishlist(titulo));
    }
}
