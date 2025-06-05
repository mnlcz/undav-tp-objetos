package org.grupoTP.inputhelper;

import org.grupoTP.Contenido;
import org.grupoTP.Libro;

import java.util.Scanner;

public class LibroInput extends ContenidoInput {
    @Override
    public Contenido input(Scanner scanner) {
        super.genericInput(scanner);
        System.out.print("Páginas: ");
        var paginas = scanner.nextInt();
        return new Libro(nombre, tipo, plataforma, fecha, paginas);
    }
}
