package mef1;

import org.grupoTP.Cliente;
import org.grupoTP.Contenido;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Api {
    private final List<Cliente> clientes;
    private final List<Contenido> contenidos;
    private final Random rand = new Random();
    
    public Api(List<Cliente> clientes, List<Contenido> contenidos)  {
        this.clientes = clientes;
        this.contenidos = contenidos;
    }
    public List<Cliente> recomendarUsuarios() {
        Collections.shuffle(clientes);
        var num = rand.nextInt(clientes.size()/2) + 1;
        return this.clientes.stream().limit(num).collect(Collectors.toList());
    }
    
    public List<Contenido> recomendarContenido() {
        Collections.shuffle(contenidos);
        var num = rand.nextInt(contenidos.size()/2) + 1;
        return this.contenidos.stream().limit(num).collect(Collectors.toList());
    }
}
