package org.grupoTP.inputhelper;

import org.grupoTP.Contenido;
import org.grupoTP.Pelicula;

import java.util.Scanner;

public class PeliculaInput extends ContenidoInput {
    @Override
    public Contenido input(Scanner scanner) {
        super.genericInput(scanner);
        System.out.print("Duración: ");
        var duracion = scanner.nextFloat();
        return new Pelicula(nombre, tipo, plataforma, fecha, duracion);
    }
}
