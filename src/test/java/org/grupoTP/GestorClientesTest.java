package org.grupoTP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

public class GestorClientesTest {

    @Test
    public void testRegistrarCliente() {
        GestorClientes gestor = new GestorClientes();

        // Al inicio no hay clientes
        assertEquals(0, gestor.getListaClientes().size());

        // Registro un cliente "juan" con pwd "1234"
        gestor.registrarCliente("juan", "1234");
        List<Cliente> lista = gestor.getListaClientes();

        assertEquals(1, lista.size());
        Cliente c = lista.get(0);
        assertEquals(1, c.getId());
        assertEquals("juan", c.getUser());
        assertTrue(c.verificarPassword("1234"));

        // Registro otro cliente
        gestor.registrarCliente("maria", "abcd");
        assertEquals(2, gestor.getListaClientes().size());
        Cliente c2 = gestor.getListaClientes().get(1);
        assertEquals(2, c2.getId());
        assertEquals("maria", c2.getUser());
    }

    @Test
    public void testEliminarCuentaExistente() {
        GestorClientes gestor = new GestorClientes();
        gestor.registrarCliente("ana", "pass");
        gestor.registrarCliente("luis", "pass2");

        // Ahora hay dos clientes
        assertEquals(2, gestor.getListaClientes().size());

        // Eliminamos "ana"
        boolean eliminadoAna = gestor.eliminarCuenta("ana");
        assertTrue(eliminadoAna, "Eliminar usuario existente debe retornar true");
        assertEquals(1, gestor.getListaClientes().size());
        // El unico que queda ahora debe ser "luis"
        assertEquals("luis", gestor.getListaClientes().get(0).getUser());
    }

    @Test
    public void testEliminarCuentaInexistente() {
        GestorClientes gestor = new GestorClientes();
        gestor.registrarCliente("carlos", "xyz");

        // Intento eliminar un usuario que no existe
        boolean resultado = gestor.eliminarCuenta("ines");
        assertFalse(resultado, "Eliminar usuario que no existe debe retornar false");
        // La lista de clientes debe seguir igual
        assertEquals(1, gestor.getListaClientes().size());
        assertEquals("carlos", gestor.getListaClientes().get(0).getUser());
    }
}
