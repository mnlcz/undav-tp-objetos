package org.grupoTP;

import mef1.Api;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        execLoop();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private static void execLoop() {
        while (true) {
            if (App.user.isEmpty()) {
                System.out.println("""
                                        ADMINISTRADOR DE CONTENIDO (admin)
                        1) Agregar nuevo contenido a la plataforma.
                        2) Quitar contenido de la plataforma.
                        3) Listar contenido de la plataforma.
                        z) [DEBUG] agregar contenido de prueba.
                        x) [DEBUG] agregar usuarios de prueba.
                        
                                        USUARIO
                        a) Iniciar sesión.
                        b) Registrarse.
                        """);
                System.out.print("Opción: ");
                var opt = App.scanner.nextLine();
                switch (opt) {
                    case "1":
                    case "2":
                    case "3":
                        adminLoop(opt);
                        break;
                    case "z":
                        rellenarPlataformaCentral();
                        break;
                    case "x":
                        rellenarUsuarios();
                        break;
                    case "a":
                    case "b":
                        loginLoop(opt);
                        break;
                    default:
                        break;
                }
            } else {
                System.out.printf("""
                                        USUARIO: %s
                        a) Gestionar contenidos.
                        b) Gestionar amigos.
                        0) Cerrar sesión.
                        %n""", App.user);

                System.out.print("Opción: ");
                userLoop(App.scanner.nextLine());
            }
        }
    }

    private static void adminLoop(String option) {
        switch (option) {
            case "1" -> App.adminContenido.agregarContenido(App.crearContenido());
            case "2" -> App.adminContenido.eliminarContenido(App.eliminarContenido());
            case "3" -> {
                var cont = App.adminContenido.plataformaCentral.getListaContenidos();
                for (var c : cont) {
                    System.out.println(c);
                }
            }
        }
    }

    private static void loginLoop(String option) {
        while (true) {
            switch (option) {
                case "a":
                    if (App.iniciarSesion()) {
                        return;
                    } else {
                        App.iniciarSesion();
                    }
                    break;
                case "b":
                    if (App.registrarse()) {
                        return;
                    } else {
                        App.registrarse();
                    }
            }
        }
    }

    private static void userLoop(String option) {
        var api = new Api(App.gestorClientes.getListaClientes(), App.adminContenido.plataformaCentral.getListaContenidos());
        switch (option) {
            case "a" -> contenidosLoop(api);
            case "b" -> amigosLoop(api);
            case "0" -> App.user = "";
        }
    }

    private static void contenidosLoop(Api apiSimulada) {
        while (true) {
            System.out.printf("""
                                    CONTENIDO DE %s
                    a) Listar vistos.
                    b) Listar wishlist.
                    c) Agregar visto.
                    d) Agregar a wishlist.
                    e) Nueva wishlist.
                    f) Calificar contenido.
                    g) Contenido recomendado (MEF1).
                    0) Volver atrás.
                    %n""", App.user);
            System.out.println("Opción: ");
            switch (App.scanner.nextLine()) {
                case "a":
                    App.listarContenidos('s');
                    break;
                case "b":
                    App.listarContenidos('w');
                    break;
                case "c":
                    App.agregar('s');
                    break;
                case "d":
                    App.agregar('w');
                    break;
                case "e":
                    App.nuevaWishlist();
                    break;
                case "f":
                    App.calificar();
                    break;
                case "g":
                    var rec = apiSimulada.recomendarContenido();
                    System.out.println("MEF1 RECOMIENDA:");
                    for (var c : rec) {
                        System.out.println(c);
                    }
                    break;
                case "0":
                default:
                    return;
            }
        }
    }

    private static void amigosLoop(Api apiSimulada) {
        while (true) {
            System.out.printf("""
                                AMIGOS DE %s
                    a) Listar amigos.
                    b) Agregar amigo.
                    c) Eliminar amigo.
                    d) Amigos recomendados (MEF1).
                    %n""", App.user);
            System.out.print("Opción: ");
            switch (App.scanner.nextLine()) {
                case "a":
                    App.listarAmigos();
                    break;
                case "b":
                    App.agregarAmigo();
                    break;
                case "c":
                    App.eliminarAmigo();
                    break;
                case "d":
                    var amigos = apiSimulada.recomendarUsuarios();
                    System.out.println("MEF1 RECOMIENDA:");
                    for (var a : amigos) {
                        System.out.println(a);
                    }
                    break;
                default:
                    return;
            }
        }
    }

    private static void rellenarPlataformaCentral() {
        App.adminContenido.agregarContenido(new Pelicula("pelicula1", "pelicula", "Netflix", LocalDateTime.now(), 148));
        App.adminContenido.agregarContenido(new Pelicula("pelicula2", "pelicula", "Netflix", LocalDateTime.now(), 60));
        App.adminContenido.agregarContenido(new Pelicula("pelicula3", "pelicula", "Netflix", LocalDateTime.now(), 120));
        App.adminContenido.agregarContenido(new Pelicula("pelicula4", "pelicula", "Netflix", LocalDateTime.now(), 180));
        App.adminContenido.agregarContenido(new Pelicula("pelicula5", "pelicula", "Netflix", LocalDateTime.now(), 240));
        App.adminContenido.agregarContenido(new Libro("libro1", "libro", "Kindle", LocalDateTime.now(), 300));
        App.adminContenido.agregarContenido(new Libro("libro2", "libro", "Kindle", LocalDateTime.now(), 100));
        App.adminContenido.agregarContenido(new Libro("libro3", "libro", "Kindle", LocalDateTime.now(), 900));
        App.adminContenido.agregarContenido(new Libro("libro4", "libro", "Kindle", LocalDateTime.now(), 500));
        App.adminContenido.agregarContenido(new Libro("libro5", "libro", "Kindle", LocalDateTime.now(), 200));
        App.adminContenido.agregarContenido(new Serie("serie1", "serie", "Netflix", LocalDateTime.now(), 2, 12));
        App.adminContenido.agregarContenido(new Serie("serie2", "serie", "Netflix", LocalDateTime.now(), 5, 12));
        App.adminContenido.agregarContenido(new Serie("serie3", "serie", "Netflix", LocalDateTime.now(), 1, 50));
        App.adminContenido.agregarContenido(new Serie("serie4", "serie", "Netflix", LocalDateTime.now(), 3, 25));
        App.adminContenido.agregarContenido(new Podcast("podcast1", "podcast", "Spotify", LocalDateTime.now(), 1, 12));
        App.adminContenido.agregarContenido(new Podcast("podcast2", "podcast", "Spotify", LocalDateTime.now(), 2, 5));
        App.adminContenido.agregarContenido(new Podcast("podcast3", "podcast", "Spotify", LocalDateTime.now(), 1, 10));
        App.adminContenido.agregarContenido(new Podcast("podcast4", "podcast", "Spotify", LocalDateTime.now(), 3, 20));
        App.adminContenido.agregarContenido(new Podcast("podcast5", "podcast", "Spotify", LocalDateTime.now(), 1, 12));

        System.out.println("[DEBUG] AGREGADOS " + App.adminContenido.plataformaCentral.getListaContenidos().size() + " CONTENIDOS.");
    }

    private static void rellenarUsuarios() {
        App.gestorClientes.registrarCliente("abcd", "abcd");
        App.gestorClientes.registrarCliente("user1", "pass");
        App.gestorClientes.registrarCliente("user2", "pass");
        App.gestorClientes.registrarCliente("user3", "pass");
        App.gestorClientes.registrarCliente("user4", "pass");
        App.gestorClientes.registrarCliente("user5", "pass");
        App.gestorClientes.registrarCliente("user6", "pass");
        App.gestorClientes.registrarCliente("user7", "pass");

        System.out.println("[DEBUG] AGREGADO USUARIO: [abcd:abcd]");
    }
}