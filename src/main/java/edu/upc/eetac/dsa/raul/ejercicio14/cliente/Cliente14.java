package edu.upc.eetac.dsa.raul.ejercicio14.cliente;

public class Cliente14 {
	public static void start() {
		System.out.println("Cliente iniciado");
		QueHoraEsProtocolo p = new QueHoraEsProtocolo("localhost", 50894);

		while (p.nextStep() == 0) {
			// nothing
		}

		System.out.println("Fin");

	}
}
