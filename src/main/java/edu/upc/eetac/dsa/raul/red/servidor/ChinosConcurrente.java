private package edu.upc.eetac.dsa.raul.red.servidor;

public class ChinosConcurrente {

	private int estado = 0;
	TCPSocketsServidor s = null;
	Jugador j1;
	Jugador j2;
	int port = 0;

	public ChinosConcurrente(int port) {
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
			System.out.println("Esperando cliente 1");
			s.esperarCliente();
			estado = 2;
			break;
			
		case 2:
			System.out.println("Recibiendo nombre");
			j1 = new Jugador(s,s.readLine().split(" ")[1]);
			s.writeLine("WAIT");
			estado = 3;
			break;
			
		case 3:
			System.out.println("Esperando cliente 2");
			s.esperarCliente();
			estado = 4;
			break;
			
		case 4:
			System.out.println("Recibiendo nombre");
			j2 = new Jugador(s,s.readLine().split(" ")[1]);
			System.out.println("Iniciando thread...");
			(new ThreadingChinos(j1,j2)).start();
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
