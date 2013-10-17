package edu.upc.eetac.dsa.raul.ejercicio15.servidor;

public class LinkerClientes extends Thread {

	public LinkerClientes() {

	}

	public void run() {
		int i = 0;
		int j = 0;
		boolean encontrado = false;
		Jugador j1;
		Jugador j2;
		while (true) {
			System.out.println("Tamaño: " + ChinosConcurrente.list.size());
			i = 0;
			j = 0;
			encontrado = false;
			while (i < ChinosConcurrente.list.size()) {
				j1 = ChinosConcurrente.list.get(i);
				if (j1.estado == 1) { // si esta esperando, buscamos a alguien
										// que este esperando jugar
					while (j < ChinosConcurrente.list.size()) {
						if (j == i) {
							j++;
							continue;
						}
						j2 = ChinosConcurrente.list.get(j);
						if (j2.estado == 2) { // si esta esperando, los
												// linkamos.
							j2.comunicar("YOUR BET");
							j1.comunicar("YOUR BET");
							j2.estado = 3;
							j1.estado = 3;
							(new Partida(j1, j2)).start();
							encontrado = true;
							break;
						} else if (j2.estado == 1) { // los dos esperan un WAIT
														// (improbable)
							j2.comunicar("WAIT");
							j1.comunicar("YOUR BET");
							j2.comunicar("YOUR BET");
							j2.estado = 3;
							j1.estado = 3;
							(new Partida(j1, j2)).start();
							encontrado = true;
							break;
						} else
							encontrado = false;
						j++;
					}
					if (!encontrado) {
						j1.comunicar("WAIT");
						j1.estado = 2;
					}
				} // else if (j1.estado == 0/2/3) <-- no haremos nada
				i++;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
