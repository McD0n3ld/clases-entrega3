package edu.upc.eetac.dsa.raul.ejercicio15.servidor;

import java.net.Socket;

public class Jugador {

	private TCPSocketsServidor s = null;
	private int apuesta = 0;
	private String nombre = "";
	private int total = 0;
	public boolean ready = false;
	
	public Jugador(Socket c, String nombre) {
		this.s = new TCPSocketsServidor(c);
		this.nombre = nombre;
	}
	
	public Jugador(Socket c) {
		this.s = new TCPSocketsServidor(c);
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

	public synchronized void setNombre(String nombre) {
		this.nombre = nombre;
		this.ready = true;
	}
	
	

}
