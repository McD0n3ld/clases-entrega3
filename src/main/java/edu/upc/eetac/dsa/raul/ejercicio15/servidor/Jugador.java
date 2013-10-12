package edu.upc.eetac.dsa.raul.ejercicio15.servidor;

import edu.upc.eetac.dsa.raul.ejercicio14.servidor.TCPSockets;

public class Jugador {

	private int apuesta = 0;
	private String nombre;
	private int total = 0;
	public int estado = 0; // 0 --> not ready 1 -> esperando wait 2 --> esperando jugador 3 -> jugando 
	private TCPSockets s = null;

	public Jugador(String nombre, TCPSockets s) {
		this.nombre = nombre;
		this.s = s;
		estado = 1; // ready
	}
	
	public Jugador(Jugador j) {
		this.nombre = j.getNombre();
		this.apuesta = j.getApuesta();
		this.total = j.getTotal();
		this.estado = j.estado;
		this.s = new TCPSockets(j.getSocket());
		estado = 1; // ready
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
	
	public TCPSockets getSocket() {
		return s;
	}


	public void setBet(String line) {
		apuesta = Integer.parseInt(line.split(" ")[0]);
		total = Integer.parseInt(line.split(" ")[1]);
	}

	public synchronized void setNombre(String nombre) {
		this.nombre = nombre;
		this.estado = 1;
		return;
	}

	public void comunicar(String line) {
		System.out.println(line+"   "+s.getCliente());
		s.writeLine(line);
	}
	
	public String escuchar() {
		return s.readLine();
	}
	
	public void cerrar() {
		s.cerrarCliente();
	}
	
	public void info() {
		System.out.println(s.getCliente());
	}
}
