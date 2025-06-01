package org.grupoTP;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    void testCrearClienteConListasVacias() {
        Cliente cliente = new Cliente(1, "testUser", "password");
        assertNotNull(cliente.getFriends(), "La lista de amigos no debería ser nula.");
        assertTrue(cliente.getFriends().isEmpty(), "La lista de amigos debería estar vacía al crear el cliente.");
        assertNotNull(cliente.getSeen(), "La lista de vistos no debería ser nula.");
        assertTrue(cliente.getSeen().isEmpty(), "La lista de vistos debería estar vacía al crear el cliente.");
        assertNotNull(cliente.getWishlists(), "La lista de wishlists no debería ser nula.");
        assertTrue(cliente.getWishlists().isEmpty(), "La lista de wishlists debería estar vacía al crear el cliente.");
    }
}