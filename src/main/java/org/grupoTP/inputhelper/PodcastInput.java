package org.grupoTP.inputhelper;

import org.grupoTP.Contenido;
import org.grupoTP.Podcast;

import java.util.Scanner;

public class PodcastInput extends ContenidoInput {
    @Override
    public Contenido input(Scanner scanner) {
        super.genericInput(scanner);
        System.out.print("Duración: ");
        var duracion = scanner.nextFloat();
        System.out.print("Episodios: ");
        var episodios = scanner.nextInt();
        return new Podcast(nombre, tipo, plataforma, fecha, duracion, episodios);
    }
}
