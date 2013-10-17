package edu.upc.eetac.dsa.raul.ejercicio12.servidor;

public class ServidorTCP {
	public static void start() {
		System.out.println("Servidor iniciado");
		QueHoraEsProtocolServidor p = new QueHoraEsProtocolServidor(50894);

		while (p.nextStep() == 0) {
			// nothing
		}

		System.out.println("Ejercicio finalizado");

	}
}
