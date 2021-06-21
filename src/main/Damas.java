package src.main;

import java.util.Scanner;

import src.jugador.Jugador;
import src.jugador.VectorJugador;
import src.tablero.*;

public class Damas {

    public Damas() {
        tablero = new Tablero(8, 8, true);
    }

    public void menu() {
        boolean regresar = true;
        int menu = 0;
        System.out.println("\n ----- Bienvenido(a) al juego de Damas ----- ");
        do {
            System.out.println("\n -------- Menú -------- ");
            System.out.println("1. Jugar" + "\n2. Agregar Jugadores" + "\n3. Ordenar Jugadores ascendente"
                    + "\n4. Ordenar Jugadores descendente" + "\n5. Mostrar Jugadores" + "\n6. Estadisticas"
                    + "\n7. Salir.");
            menu = IngresoDatos.getEntero("Ingrese la opción: ", true);
            switch (menu) {
                case 1:
                    jugar();
                    break;
                case 2:
                    tablaJugador.agregarJugador();
                    break;
                case 3:
                    System.out.println("Desordenado:");
                    tablaJugador.mostrarJugadores();
                    System.out.println("Ordenado ascendente:");
                    tablaJugador.ordenarPorPartidaGanada(true);
                    tablaJugador.mostrarJugadores();
                    break;
                case 4:
                    System.out.println("Desordenado:");
                    tablaJugador.mostrarJugadores();
                    System.out.println("Ordenado descendente:");
                    tablaJugador.ordenarPorPartidaGanada(false);
                    tablaJugador.mostrarJugadores();
                    break;
                case 5:
                    tablaJugador.mostrarJugadores();
                    break;
                case 6:
                    break;
                case 7:
                    IngresoDatos.imprimirMensaje("¡Saliendo del programa!\n");
                    regresar = false;
                    break;
                default:
                    IngresoDatos.imprimirMensaje("¡Opción incorrecta!");
            }
        } while (regresar);
    }

    public void jugar() {
        if (tablaJugador.getSiguienteCodigo() > 2) {
            seleccionarJugador();
            while (!seTerminoJuego) {
                if (iteracion % 2 == 0) {
                    turno(true);
                    iteracion++;
                } else {
                    turno(false);
                    iteracion++;
                }
            }
        } else {
            IngresoDatos.imprimirMensaje("¡No hay 2 jugadores, por favor ingresalos!");
        }

    }

    private void seleccionarJugador() {
        boolean seguir = false;
        int idJ1;
        int idJ2;
        do {
            IngresoDatos.imprimirMensaje("Lista de jugadores disponibles: ");
            tablaJugador.mostrarJugadores();
            IngresoDatos.imprimirMensaje("\nElige a los jugadores conforme su id: ");
            idJ1 = Damas.readInt("\nId jugador 1: ", true);
            idJ2 = Damas.readInt("\nId jugador 2: ", true);

            if (idJ1 < tablaJugador.getSiguienteCodigo() && idJ2 < tablaJugador.getSiguienteCodigo()) {
                seguir = true;
            } else {
                System.out.println("Id erroneo de uno de los jugadores, digite de nuevo");
            }
        } while (!seguir);

        PiedraPapelTijera piedraPapelTijera = new PiedraPapelTijera();
        boolean turnoBlanco = piedraPapelTijera.generarAleatoriamente();

        if (turnoBlanco) {
            jugadorBlanco = tablaJugador.getJugadores()[idJ1-1];
            jugadorNegro = tablaJugador.getJugadores()[idJ2-1];
        } else {
            jugadorBlanco = tablaJugador.getJugadores()[idJ2-1];
            jugadorNegro = tablaJugador.getJugadores()[idJ1-1];
        }
    }

    private void turno(boolean esBlanca) {

        Ficha fichaAMover = null;
        Coordenada[] coordenadasPosibles = null;
        String advertencia = "";

        while (fichaAMover == null) {
            System.out.println("\n\nJugador blanco: " + jugadorBlanco.getNombre() + "\t\t\t" + "Jugador negro: "
                    + jugadorNegro.getNombre()+"\n");
            tablero.pintarTablero();
            System.out.println(advertencia);
            advertencia = "";
            System.out.println("TURNO DE LAS " + ((esBlanca) ? "BLANCAS" : "NEGRAS"));
            fichaAMover = tablero.getFicha(Damas.readString("Seleccione la ficha que desea mover: "), esBlanca);
            if (fichaAMover == null) {
                advertencia += "Debe seleccionar una ficha valida";
            } else {
                coordenadasPosibles = fichaAMover.getMovimientosPosibles();

                if (coordenadasPosibles != null) {
                    String mensaje = "\nCoordenadas posibles para la ficha seleccionada: \n";
                    int cantidadPosibilidades = 0;
                    int i = 0;
                    while (coordenadasPosibles[i] != null) {
                        if (coordenadasPosibles[i] != null) {
                            mensaje += i + ") " + coordenadasPosibles[i].toString() + "\n";
                            cantidadPosibilidades++;
                        }
                        i++;
                    }
                    mensaje += "\nSeleccione la coordenada a la que desea moverse: ";
                    int seleccion = -1;
                    while ((seleccion < 0) || (seleccion >= cantidadPosibilidades)) {
                        seleccion = Damas.readInt(mensaje, true);
                    }

                    tablero.moverFicha(fichaAMover, coordenadasPosibles[seleccion]);

                } else {

                    advertencia += "La ficha " + fichaAMover.getId() + " que selecciono no tiene movimientos posibles.";
                    fichaAMover = null;
                }
            }

        }

    }

    private static String readString(String mensaje) {
        String res = "";

        boolean lecturaCorrecta = false;

        while (!lecturaCorrecta) {
            System.out.print(mensaje + " ");

            res = scanner.nextLine();
            res = res.trim();
            if (res.length() > 0) {
                lecturaCorrecta = true;
            } else {
                System.out.println("Debe ingresar al menos un caracter para continuar.");
            }

        }

        return res;
    }

    private static int readInt(String mensaje, boolean soloPositivo) {
        int res = 0;
        String lectura = "";
        boolean lecturaCorrecta = false;
        while (!lecturaCorrecta) {
            System.out.print(mensaje + " ");
            lectura = scanner.nextLine();

            try {
                res = Integer.valueOf(lectura);
                lecturaCorrecta = true;
                if (soloPositivo && res < 0) {
                    lecturaCorrecta = false;
                    System.out.println("Debe ingresar un número positivo. ");
                }
            } catch (Exception e) {
                lecturaCorrecta = false;
            }
        }

        return res;
    }

    private static Scanner scanner = new Scanner(System.in);
    private VectorJugador tablaJugador = new VectorJugador();
    private Tablero tablero;
    private boolean seTerminoJuego = false;
    private int iteracion = 0;
    private Jugador jugadorBlanco;
    private Jugador jugadorNegro;
}
