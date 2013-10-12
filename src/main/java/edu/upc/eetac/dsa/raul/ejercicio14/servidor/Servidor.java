package edu.upc.eetac.dsa.raul.ejercicio14.servidor;

public class Servidor {
	public static void main(String[] args) {
		System.out.println("Servidor iniciado");
		QueHoraEsProtocolServidorConcurrente pt = new QueHoraEsProtocolServidorConcurrente(50894);

		while (pt.nextStep() == 0) {
			// nothing
		}

		System.out.println("Ejercicio finalizado");

	}
}
