package org.grupoTP;

import java.util.ArrayList;
import java.util.List;

public class GestorClientes {
    private List<Cliente> listaClientes = new ArrayList<>();

    public void registrarCliente(String user, String password){
        int nuevoId = listaClientes.size() + 1;
        Cliente nuevoCliente = new Cliente(nuevoId, user, password);
        listaClientes.add(nuevoCliente);
        System.out.println("Cliente " + user + " registrado.");
    }
    //Elimina la cuenta que llama al metodo de GestorClientes
    public boolean eliminarCuenta(String user){
        return listaClientes.removeIf(cliente -> cliente.getUser().equals(user));
    }
}
