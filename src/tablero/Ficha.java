package src.tablero;

public class Ficha {
    private boolean esNegra;

    private boolean debeAscender;

    // private int x,y;
    private Coordenada posicion;

    private char celda = '░';

    private Tablero tablero; 

    //private char celdaColor = '█';
    // █▓
    public static final String ANSI_RED = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[34m";

    private String id;

    public Coordenada getCoordenada(){
        return this.posicion;
    }

    public boolean getEsNegra() {
        return esNegra;
    }

    public String getCaracter() {
        String res = (esNegra) ? "" + ANSI_RED + celda : "" + ANSI_YELLOW + celda;
        return res;
    }

    public Ficha(boolean esNegra, String id, Coordenada posicion, boolean debeAscender, Tablero tablero) {
        this.debeAscender = debeAscender;
        this.tablero = tablero;
        this.posicion = posicion;
        this.esNegra = esNegra;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Coordenada[] getMovimientosPosibles() {
        
        Coordenada[] res = new Coordenada[4];
        int movY = (debeAscender) ? 1 : -1;

        int cont = this.evaluarMovimiento(-1, movY, this.posicion, res, 0);
        cont = this.evaluarMovimiento(+1, movY, this.posicion, res, cont);

        if (cont == 0){
            res = null;
        }
        return res;
    }

    public void setCoordenada(int x, int y){
        this.posicion.setX(x);
        this.posicion.setY(y);
    }
  


    private int evaluarMovimiento(int movX, int movY, Coordenada pos, Coordenada[] res, int indice){
        
        Coordenada evaluando = new Coordenada(pos.getX() + movX, pos.getY() + movY);
        Celda tmp = tablero.getCelda(evaluando);

        if (tmp != null) {
            if (!tmp.ocupadaPorFicha()) { //No está ocupada
                res[indice] = evaluando;
                fichaAComer[indice] = null;
                indice++;
            } else { //Esta ocupada
                if (tmp.getFicha().getEsNegra() != this.esNegra) {
                    fichaAComer[indice] = tmp.getFicha().getCoordenada();
                    Coordenada evaluando2 = new Coordenada(evaluando.getX() + movX, evaluando.getY() +movY);
                    tmp = tablero.getCelda(evaluando2);
                    if (!tmp.ocupadaPorFicha()) {
                        res[indice] = evaluando2;
                        indice++;
                    }
                }
            }
        }

        return indice;
    }

    public Coordenada[] getFichasAComer(){
        return fichaAComer;
    }

    Coordenada[] fichaAComer = new Coordenada[4];
}
