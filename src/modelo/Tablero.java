
package src.modelo;

public class Tablero {

    public static final String SIMBOLO_VACIO = " ";

    private String[][] tablero;

    public Tablero() {
        this.tablero = new String[12][12];
        dibujarTablero();
    }

    public String[][] getTablero() {
        return tablero;
    }

    public void setTablero(String[][] tablero) {
        this.tablero = tablero;
    }
    
    public void dibujarTablero() {
        for (int i = 0; i < tablero.length; i++) { //Imprimir las filas
        
        	for(int j=0; j<tablero.length; j++){
	        	System.out.print("--------");
        	}
        	
        	for(int k=0; k<3; k++){
	        	System.out.println("");
        		for(int l=0; l<=tablero.length; l++){
        			System.out.print("|");
        			System.out.print("\t");
        		}
        	}
        	
        	System.out.println("");
        }
        
        for(int j=0; j<tablero.length; j++){
	        	System.out.print("--------");
        }
        
        System.out.println("");
    }



}
