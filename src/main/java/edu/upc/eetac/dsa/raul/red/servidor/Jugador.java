package edu.upc.eetac.dsa.raul.red.servidor;

public class Jugador {

	private TCPSocketsServidor s = null;
	private int apuesta = 0;
	private String nombre;
	private int total = 0;
	
	public Jugador(TCPSocketsServidor s, String nombre) {
		this.s = s;
		this.nombre = nombre;
	}

	public TCPSocketsServidor getSocket() {
		return s;
	}

	public int getApuesta() {
		return apuesta;
	}

	public void setApuesta(int apuesta) {
		this.apuesta = apuesta;
	}

	public String getNombre() {
		return nombre;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public void setBet(String line) {
		apuesta = Integer.parseInt(line.split(" ")[0]);
		total = Integer.parseInt(line.split(" ")[1]);
	}
	
	

}
