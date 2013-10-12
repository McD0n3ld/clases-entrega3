private package edu.upc.eetac.dsa.raul.ejercicio14.servidor;

public class QueHoraEsProtocolServidorConcurrente {

	private int estado = 0;
	TCPSockets s = null;
	int port = 0;

	public QueHoraEsProtocolServidorConcurrente(int port) {
		this.port = port;
	}

	public int nextStep() {
		switch (estado) {
		case 0:
			System.out.println("Abriendo servidor");
			s = new TCPSockets(port);
			estado = 1;
			break;

		case 1:
			System.out.println("Esperando cliente");
			s.esperarCliente();
			TCPSockets temp = new TCPSockets(s);
			System.out.println("Iniciando thread...");
			(new ThreadingProtocol(temp)).start();
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
