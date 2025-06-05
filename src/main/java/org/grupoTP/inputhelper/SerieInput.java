package org.grupoTP.inputhelper;

import org.grupoTP.Contenido;
import org.grupoTP.Serie;

import java.util.Scanner;

public class SerieInput extends ContenidoInput {
    @Override
    public Contenido input(Scanner scanner) {
        super.genericInput(scanner);
        System.out.print("Temporadas: ");
        var temporadas = scanner.nextInt();
        System.out.print("Episodios: ");
        var episodios = scanner.nextInt();
        return new Serie(nombre, tipo, plataforma, fecha, temporadas, episodios);
    }
}
