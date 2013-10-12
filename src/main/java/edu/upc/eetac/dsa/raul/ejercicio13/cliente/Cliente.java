package edu.upc.eetac.dsa.raul.ejercicio13.cliente;

import edu.upc.eetac.dsa.raul.ejercicio12.cliente.QueHoraEsProtocolCliente;

public class Cliente {
	public static void main(String[] args) {
		System.out.println("Cliente iniciado");
		QueHoraEsProtocolCliente p = new QueHoraEsProtocolCliente("localhost", 50894);

		while (p.nextStep() == 0) {
			// nothing
		}
		
		System.out.println("Fin");

	}
}
