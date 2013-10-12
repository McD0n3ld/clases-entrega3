package edu.upc.eetac.dsa.raul.ejercicio12.servidor;

public class QueHoraEsProtocolServidor {

	private int estado = 0;
	TCPSocketsServidor s = null;
	UDPSocketServidor udp = null;
	int port = 0;

	public QueHoraEsProtocolServidor(int port) {
		this.port = port;
	}

	public int nextStep() {
		switch (estado) {
		case 0:
			System.out.println("Abriendo servidor");
			s = new TCPSocketsServidor(port);
			estado = 1;
			break;

		case 1:
			System.out.println("Esperando cliente");
			s.esperarCliente();
			estado = 2;
			break;

		case 2:
			System.out.println("Enviar fecha");
			s.enviarFecha();
			estado = 99;
			break;

		case 99:
			System.out.println("Cerrar socket cliente");
			s.cerrarCliente();
			estado = 1;
			return -1;
			// break;

		default:
			System.out.println("Warning, wrong state. Reiniciando protocolo");
			estado = 1;
			break;
		}
		return 0;
	}

	public int nextStepUDP() {
		switch (estado) {
		case 0:
			System.out.println("Abriendo servidor");
			udp = new UDPSocketServidor(port);
			estado = 1;
			break;

		case 1:
			System.out.println("Esperando cliente");
			udp.esperarCliente();
			estado = 2;
			break;

		case 2:
			System.out.println("Enviar fecha");
			udp.enviarFecha();
			estado = 1;
			return -1;
			// break;

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
