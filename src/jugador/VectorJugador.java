package src.jugador;

import src.main.IngresoDatos;
import src.jugador.Jugador;

public class VectorJugador {

    public VectorJugador() {
        siguienteCodigo = 1;
    }

    public void agregarJugador(String nombre) {
        if (siguienteCodigo > 10) {
            System.out.println("Límite de jugadores alcanzado");
        } else {
            jugadores[(siguienteCodigo - 1)] = new Jugador(siguienteCodigo, nombre, 0, 0);
        }
        siguienteCodigo++;
    }

    public void agregarJugador() {
        String nombre = IngresoDatos.getTexto("Ingrese el nombre del jugador: ");
        agregarJugador(nombre);
    }

    public void mostrarJugadores() {
        System.out.println("\n\n Jugadores del juego de Damas");
        for (int i = 0; i < (siguienteCodigo - 1); i++) {
            System.out.println(i + ". " + jugadores[i].getInformacion());
        }
        System.out.println("\n\n");
    }

    // Ordenamiento
    public void ordenarPorPartidaGanada(boolean ascendente) {
        boolean cambio = true;

        for (int i = 1; i < (siguienteCodigo - 1); i++) {
            for (int j = 0; j < (siguienteCodigo - 1 - i); j++) {
                // String.compareTo(String)
                // [-oo , + oo]
                if (ascendente) {
                    cambio = (jugadores[j].getJuegosGanados() > jugadores[j + 1].getJuegosGanados());
                } else {
                    cambio = (jugadores[j].getJuegosGanados() < jugadores[j + 1].getJuegosGanados());
                }

                if (cambio) {
                    // jugadores i va antes que jugadores j<0 descendente
                    Jugador aux = jugadores[j];
                    jugadores[j] = jugadores[j + 1];
                    jugadores[j + 1] = aux;
                }
            }
        }

    }

    public int getSiguienteCodigo(){
        return siguienteCodigo;
    }
    
    public Jugador[] getJugadores(){
        return jugadores;
    }
    private int siguienteCodigo;
    private Jugador[] jugadores = new Jugador[10];
}