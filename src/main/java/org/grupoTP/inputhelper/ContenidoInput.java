package org.grupoTP.inputhelper;

import org.grupoTP.Contenido;

import java.time.LocalDateTime;
import java.util.Scanner;

public abstract class ContenidoInput {
    protected String nombre;
    protected String tipo;
    protected String plataforma;
    protected LocalDateTime fecha;

    public void genericInput(Scanner scanner) {
        System.out.print("Nombre: ");
        this.nombre = scanner.nextLine();
        System.out.print("Tipo: ");
        this.tipo = scanner.nextLine();
        System.out.print("Plataforma: ");
        this.plataforma = scanner.nextLine();
        System.out.print("Fecha de creación (yyyy-mm-dd): ");
        this.fecha = parseFecha(scanner.nextLine());
    }

    public abstract Contenido input(Scanner scanner);

    private static LocalDateTime parseFecha(String input) {
        var split = input.split("-");
        return LocalDateTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), 0, 0);
    }
}
