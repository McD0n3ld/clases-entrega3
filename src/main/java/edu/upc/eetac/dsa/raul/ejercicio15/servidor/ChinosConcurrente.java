private package edu.upc.eetac.dsa.raul.ejercicio15.servidor;

import java.util.ArrayList;

import edu.upc.eetac.dsa.raul.ejercicio14.servidor.TCPSockets;

public class ChinosConcurrente {

	private int estado = 0;

	TCPSockets s = null;

	// Lista de jugadores.
	public static ArrayList<Jugador> list = new ArrayList<Jugador>();

	int port = 0;

	public ChinosConcurrente(int port) {
		this.port = port;
	}

	public int nextStep() {
		switch (estado) {
		case 0:
			System.out.println("Abriendo servidor");
			s = new TCPSockets(port);
			(new LinkerClientes()).start(); // abrimos el thread que linka
											// clientes
			estado = 1;
			break;

		case 1:
			System.out.println("Esperando cliente....");
			s.esperarCliente();
			if (list.size() == 2)
				estado = 3;
			estado = 2;
			break;

		case 2:
			System.out.println("Ha entrado un cliente. Colocandolo en la lista.");
			TCPSockets temp = new TCPSockets(s);
			(new ProcesarCliente(temp)).start();
			estado = 1;
			break;

		case 3:
			if (list.size() < 2)
				estado = 1;
			break;

		default:
			System.out.println("Warning, wrong state. Reiniciando protocolo");
			estado = 1;
			break;
		}
		return 0;
	}

	public void terminar() {
		s.close();
		estado = 0;
	}

}
