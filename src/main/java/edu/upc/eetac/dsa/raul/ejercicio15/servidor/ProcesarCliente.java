package edu.upc.eetac.dsa.raul.ejercicio15.servidor;

import edu.upc.eetac.dsa.raul.ejercicio14.servidor.TCPSockets;

public class ProcesarCliente extends Thread { // añade un jugador que esté listo
												// a la lista
	TCPSockets s;

	public ProcesarCliente(TCPSockets s) {
		this.s = new TCPSockets(s);
	}

	public void run() {
		System.out.println("Recibiendo nombre cliente.");
		String nombre = s.readLine();
		Jugador j = new Jugador(nombre, s);
		synchronized (ChinosConcurrente.list) {
			ChinosConcurrente.list.add(j);
		}
	}
}