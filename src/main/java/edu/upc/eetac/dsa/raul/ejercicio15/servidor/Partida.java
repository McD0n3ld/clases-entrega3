package edu.upc.eetac.dsa.raul.ejercicio15.servidor;

public class Partida extends Thread {

	Jugador j1;
	Jugador j2;
	int estado = 1;

	public Partida(Jugador j1, Jugador j2) {
		this.j1 = j1;
		this.j2 = j2;
	}

	public void run() {
		while (estado != -1) {
			switch (estado) {
			case 1:
				System.out.println("Leyendo apuestas...");
				j1.info();
				j2.info();
				j1.setBet(j1.escuchar());
				j2.setBet(j2.escuchar());
				estado = 2;
				break;

			case 2:
				System.out.println("Comprobando apuestas...");
				int ganador = comprobarApuesta(j1.getApuesta(), j2.getApuesta(), j1.getTotal(), j2.getTotal());
				String line = "";
				if (ganador == 1)
					line = "WINNER " + j1.getNombre();
				else if (ganador == 2)
					line = "WINNER " + j2.getNombre();
				else
					line = "NONE";
				j1.comunicar(line);
				j2.comunicar(line);
				estado = 98;
				break;

			case 98:
				System.out.println("Cerrar socket cliente");
				j1.cerrar();
				j2.cerrar();
				estado = 99;
				break;

			case 99:
				System.out.println("Borrando usuario");
				synchronized(ChinosConcurrente.list) {
					ChinosConcurrente.list.remove(j1);
					ChinosConcurrente.list.remove(j2);
				}
				estado = -1;
				break;

			default:
				System.out.println("Warning, wrong state. Reiniciando protocolo");
				estado = 1;
				break;
			}
		}

	}

	private int comprobarApuesta(int juega1, int juega2, int totales1, int totales2) {
		int ganador;
		ganador = juega1 + juega2;
		if (totales1 == ganador)
			return 1;
		else if (totales2 == ganador)
			return 2;
		return 0;
	}
}
