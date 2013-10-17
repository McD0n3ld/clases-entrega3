package edu.upc.eetac.dsa.raul.ejercicio15.servidor;

public class ServidorChinos {
	public static void start() {
		System.out.println("Servidor iniciado");
		ChinosConcurrente cc = new ChinosConcurrente(50894);

		while (cc.nextStep() == 0) {
			// nothing
		}

		System.out.println("Ejercicio finalizado");

	}
}
