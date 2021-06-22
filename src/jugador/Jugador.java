package src.jugador;

public class Jugador{

	public Jugador(int id, String nombre, int juegosGanados, int juegosPerdidos){
		this.id = id;
		this.nombre = nombre;
		this.juegosGanados = juegosGanados;
		this.juegosPerdidos = juegosPerdidos;
		
	}
	
	public String getNombre(){
		return nombre;
	}

	public void setJuegosGanados(int juegosGanados){
		this.juegosGanados = juegosGanados;
	}

	public int getJuegosGanados(){
		return juegosGanados;
	}

	public void setJuegosPerdidos(int juegosPerdidos){
		this.juegosPerdidos = juegosPerdidos;
	}

	public int getJuegosPerdidos(){
		return juegosPerdidos;
	}

	public int getId(){
		return this.id;
	}

	public String getInformacion(){
		return "Id: " + id+ " nombre: "+nombre + " juegos ganados: "+juegosGanados + " juegos perdidos: " +juegosPerdidos;
	}
	
	//Variables con encapsulamiento
	private String nombre;
	private int juegosGanados;
	private int juegosPerdidos;
	private int id;
}
