package org.grupoTP;

import org.grupoTP.inputhelper.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;

public final class App {
    public static String user = "";
    public static Scanner scanner = new Scanner(System.in);
    public final static AdministradorDeContenido adminContenido = new AdministradorDeContenido(new PlataformaCentral());
    public final static GestorClientes gestorClientes = new GestorClientes();

    public static Contenido crearContenido() {
        while (true) {
            System.out.println("""
                                    CREADOR DE CONTENIDOS
                    PELÍCULA (1) - PODCAST (2) - SERIE (3) - LIBRO (4)
                    """);
            var opt = scanner.nextLine();

            switch (opt) {
                case "1":
                    return optionHandler(new PeliculaInput(), scanner);
                case "2":
                    return optionHandler(new PodcastInput(), scanner);
                case "3":
                    return optionHandler(new SerieInput(), scanner);
                case "4":
                    return optionHandler(new LibroInput(), scanner);
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    public static Contenido eliminarContenido() {
        System.out.print("Título a eliminar: ");
        var nombre = scanner.nextLine();
        var res = adminContenido.plataformaCentral
                .getListaContenidos()
                .stream()
                .filter(contenido -> Objects.equals(contenido.getNombre(), nombre))
                .findFirst();
        return res.orElse(null);
    }

    public static boolean iniciarSesion() {
        System.out.print("Usuario: ");
        var user = scanner.nextLine();
        System.out.print("Password: ");
        var rawPassword = scanner.nextLine();
        return login(user, rawPassword);
    }
    
    private static boolean login(String username, String rawPassword) {
        var existe = gestorClientes.existe(username);
        if (existe.isPresent()) {
            var cliente = existe.get();
            if (cliente.verificarPassword(rawPassword)) {
                user = cliente.getUser();
                return true;
            } else {
                System.out.println("Contraseña incorrecta.");
                return false;
            }
        } else {
            System.out.println("Usuario incorrecto.");
            return false;
        }
    }

    public static boolean registrarse() {
        System.out.print("Usuario: ");
        var username = scanner.nextLine();
        var existe = gestorClientes.existe(username);
        if (existe.isPresent()) {
            System.out.println("Usuario existente.");
            return false;
        }
        System.out.print("Password: ");
        var rawPassword = scanner.nextLine();
        gestorClientes.registrarCliente(username, rawPassword);
        return true;
    }

    public static void listarContenidos(char opt) {
        Consumer<List<Object>> list = (List<Object> l) -> {
            for (var c : l) {
                System.out.println(c);
            }
        };

        switch (opt) {
            case 's' -> list.accept(Collections.singletonList(App.gestorClientes.getCliente(user).getSeen()));
            case 'w' -> list.accept(Collections.singletonList(App.gestorClientes.getCliente(user).getWishlists()));
        }
    }

    public static void agregar(char opt) {
        System.out.print("Titulo que desea marcar como visto: ");
        var titulo = scanner.nextLine();
        System.out.print("Fecha en la que se vió el contenido (yyyy-mm-dd): ");
        var split = scanner.nextLine().split("-");
        var fecha = LocalDateTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), 0, 0);
        var contenido = adminContenido.plataformaCentral.getContenido(titulo);
        if (opt == 's') {
            contenido.ifPresent(value -> adminContenido.plataformaCentral.marcarVisto(currentUser(), value, fecha));
        } else if (opt == 'w') {
            System.out.print("Titulo de la wishlist a la que desea agregar el contenido: ");
            var wishlistTitulo = scanner.nextLine();
            var wishlist = gestorClientes.getWishlist(currentUser(), wishlistTitulo);
            if (wishlist.isEmpty()) {
                System.out.println("No existe una wishlist con ese titulo.");
            } else {
                contenido.ifPresent(value -> wishlist.get().agregarContenidoWishlist(value));
            }
        }
    }
    
    public static void nuevaWishlist() {
        System.out.print("Título de la nueva wishlist: ");
        var titulo = scanner.nextLine();
        var existe = gestorClientes.getWishlist(currentUser(), titulo);
        if (existe.isPresent()) {
            System.out.println("Ya existe una wishlist con ese título.");
        } else {
            gestorClientes.agregarWishlist(currentUser(), titulo);
        }
    }
    
    public static void calificar() {
        System.out.print("Título del contenido que desea puntuar: ");
        var titulo = scanner.nextLine();
        var existe = adminContenido.plataformaCentral.getContenido(titulo);
        if (existe.isEmpty()) {
            System.out.println("No existe contenido con ese título.");
        } else {
            var cont = existe.get();
            while (true) {
                System.out.print("Puntuación 0-5: ");
                var puntaje = scanner.nextFloat();
                if (adminContenido.plataformaCentral.calificacionValida(puntaje)) {
                    cont.setCalificacion(puntaje);
                    System.out.println("Ingrese su reseña: ");
                    var res = scanner.nextLine();
                    adminContenido.calificarContenido(currentUser(), cont, puntaje, res);
                    break;
                } else {
                    System.out.println("Calificación inválida, ingrésela nuevamente.");
                }
            }
        }
    }
    
    public static void listarAmigos() {
        var amigos = currentUser().getFriends();
        if (amigos.isEmpty()) {
            System.out.println("No tenés amigos agregados.");
        } else {
            for (var a : amigos) {
                System.out.println(a);
            }
        }
    }
    
    public static void agregarAmigo() {
        var amigos = currentUser().getFriends();
        System.out.print("Nombre del amigo a agregar: ");
        var nombre = scanner.nextLine();
        var existe = gestorClientes.existe(nombre);
        if (existe.isEmpty()) {
            System.out.println("No existe un usuario con ese nombre.");
        } else if (amigos.stream().anyMatch(c -> c.getUser().equals(nombre))) {
            System.out.println("Ya tenés a esta persona en tu lista de amigos.");
        } else {
            currentUser().agregarAmigo(existe.get());
        }
    }
    
    public static void eliminarAmigo() {
        var amigos = currentUser().getFriends();
        System.out.print("Nombre del amigo a eliminar: ");
        var nombre = scanner.nextLine();
        var existe = gestorClientes.existe(nombre);
        if (existe.isEmpty()) {
            System.out.println("No existe un usuario con ese nombre.");
        } else if (amigos.stream().anyMatch(c -> c.getUser().equals(nombre))) {
            currentUser().eliminarAmigo(existe.get());
        } else {
            System.out.println("No tenés a esta persona agregada a tus amigos.");
        }
    }
    
    private static Contenido optionHandler(ContenidoInput inputHandler, Scanner scanner) {
        return inputHandler.input(scanner);
    }

    private static Cliente currentUser() {
        return App.gestorClientes.getCliente(user);
    }
}
