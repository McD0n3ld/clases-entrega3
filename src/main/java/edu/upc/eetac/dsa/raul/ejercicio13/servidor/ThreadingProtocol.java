package edu.upc.eetac.dsa.raul.ejercicio13.servidor;

import edu.upc.eetac.dsa.raul.ejercicio12.servidor.TCPSocketsServidor;

class ThreadingProtocol extends Thread {

	TCPSocketsServidor s = null;
	int estado = 1;
	int opcion = -1;

	public ThreadingProtocol(TCPSocketsServidor s) {
		this.s = s;
	}

	public void run() {
		System.out.println("Thread iniciado. <EX13>");
		while (estado != -1) {
			switch (estado) {
			
			case 1:
				System.out.println("Enviar fecha");
				s.enviarFecha();
				estado = 99;
				break;

			case 99:
				System.out.println("Cerrar socket cliente");
				s.cerrarCliente();
				estado = -1;
				break;

			default:
				System.out.println("Warning, wrong state. Reiniciando protocolo");
				estado = 1;
				break;
			}
		}
		return;
	}

}
