package edu.upc.eetac.dsa.raul.red.servidor;

public class ThreadingChinos extends Thread {

	static int THREADS_ACTIVOS = 0;
	
	Jugador j1 = null;
	Jugador j2 = null;
	
	int estado = 1;
	int opcion = -1;

	int usuarios = 0;

	String apuesta1 = "";
	String apuesta2 = "";
	
	String nombre1 = "";
	String nombre2 = "";
	
	public ThreadingChinos(Jugador j1, Jugador j2) {
		this.j1 = j1;
		this.j2 = j2;
		THREADS_ACTIVOS++;
		if (THREADS_ACTIVOS > 10) {
			THREADS_ACTIVOS--;
			estado = 99;
		}
	}

	public void run() {
		System.out.println("Thread iniciado.");
		while (estado != -1) {
			switch (estado) {
			case 1:
				System.out.println("Preguntando apuestas...");
				j1.getSocket().writeLine("YOUR BET");
				j2.getSocket().writeLine("YOUR BET");
				estado = 2;
				break;

			case 2:
				System.out.println("Leyendo apuestas...");
				j1.setBet(j1.getSocket().readLine());
				j2.setBet(j2.getSocket().readLine());
				estado = 3;
				break;

			case 3:
				System.out.println("Comprobando apuestas...");
				int ganador = comprobarApuesta(j1.getApuesta(), j2.getApuesta(), j1.getTotal(), j2.getTotal());
				String line = "";
				if (ganador == 1)
					line = "WINNER "+j1.getNombre();
				else if (ganador == 2)
					line = "WINNER "+j2.getNombre();
				else
					line = "NONE";
				j1.getSocket().writeLine(line);
				j2.getSocket().writeLine(line);
				estado = 99;

			case 99:
				System.out.println("Cerrar socket cliente");
				j1.getSocket().cerrarCliente();
				j2.getSocket().cerrarCliente();
				return;

			default:
				System.out.println("Warning, wrong state. Reiniciando protocolo");
				estado = 1;
				break;
			}
		}
		return;
	}

	private int comprobarApuesta(int juega1, int juega2, int totales1, int totales2) {
		int ganador;
		ganador = juega1+juega2;
		if (totales1 == ganador)
			return 1;
		else if (totales2 == ganador)
			return 2;
		return 0;
	}

}
