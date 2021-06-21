package src.tablero;

import src.main.IngresoDatos;

public class PiedraPapelTijera {

    public PiedraPapelTijera(){
    }


    public boolean generarAleatoriamente(){
        boolean empate = false;
        int aleat1;
        int aleat2;
        System.out.println("Su turno se generará aleatoriamente, con el juego Piedra Papel o Tijera");
        System.out.println("1. Tijera"
                          +"\n2. Papel"
                          +"\n2. Piedra");
        do{
            aleat1 = (int)(Math.random()*(3-1+1)+1);
            aleat2 = (int)(Math.random()*(3-1+1)+1);

            if(aleat1 == aleat2){
                empate = true;
            } else {
                empate = false;
            }
        }while(empate);

        boolean turnoBlanco = generarTurno(aleat1, aleat2);
        return turnoBlanco;
    }
    
    public boolean generarTurno(int aleat1, int aleat2){
        boolean turnoBlanco;
        if(aleat1 == 1){
            IngresoDatos.imprimirMensaje("Jugador 1 eligió: "+tijera);
            if(aleat2 == 2){
                IngresoDatos.imprimirMensaje("Jugador 2 eligió: "+papel);
                IngresoDatos.imprimirMensaje("Tijera vence a papel, por lo tanto empieza jugador 1.");
                return turnoBlanco = true;
            } else {
                IngresoDatos.imprimirMensaje("Jugador 2 eligió: "+piedra);
                IngresoDatos.imprimirMensaje("Piedra vence a tijera, por lo tanto empieza jugador 2.");
                return turnoBlanco = false;
            } 
        } else if(aleat1 == 2){
            IngresoDatos.imprimirMensaje("Jugador 1 eligió: "+papel);
            if(aleat2==1){
                IngresoDatos.imprimirMensaje("Jugador 2 eligió: "+tijera);
                IngresoDatos.imprimirMensaje("Tijera vence a papel, por lo tanto empieza jugador 2.");
                return turnoBlanco = false;
            } else {
                IngresoDatos.imprimirMensaje("Jugador 2 eligió: "+piedra);
                IngresoDatos.imprimirMensaje("Papel vence a piedra, por lo tanto empieza jugador 1.");
                return turnoBlanco = true;
            }
        } else {
            IngresoDatos.imprimirMensaje("Jugador 1 eligió: "+piedra);
            if(aleat2==2){
                IngresoDatos.imprimirMensaje("Jugador 2 eligió: "+papel);
                IngresoDatos.imprimirMensaje("Papel vence a piedra, por lo tanto empieza jugador 2.");
                return turnoBlanco = false;
            } else {
                IngresoDatos.imprimirMensaje("Jugador 2 eligió: "+tijera);
                IngresoDatos.imprimirMensaje("Piedra vence a tijera, por lo tanto empieza jugador 1.");
                return turnoBlanco = true;
            }
        }

    }
    private String tijera = "Tijera";
    private String papel = "Papel"; 
    private String piedra = "Piedra";
}
