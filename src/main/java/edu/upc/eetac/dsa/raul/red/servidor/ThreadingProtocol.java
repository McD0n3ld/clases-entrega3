package edu.upc.eetac.dsa.raul.red.servidor;

public class ThreadingProtocol extends Thread {

	TCPSocketsServidor s = null;
	int estado = 1;
	int opcion = -1;

	public ThreadingProtocol(TCPSocketsServidor s) {
		this.s = s;
	}

	public void run() {
		System.out.println("Thread iniciado.");
		while (estado != -1) {
			switch (estado) {
			case 1:
				System.out.println("Lectura del numero enviado");
				String line = s.readLine();
				opcion = Integer.parseInt(line);
				System.out.println("Opcion: " + opcion);
				estado = 2;
				break;

			case 2:
				System.out.println("Enviar fecha");
				// s.enviarFecha();
				s.enviarFecha(opcion);
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
