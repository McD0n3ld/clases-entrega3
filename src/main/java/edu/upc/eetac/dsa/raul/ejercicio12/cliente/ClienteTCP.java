package edu.upc.eetac.dsa.raul.ejercicio12.cliente;

public class ClienteTCP {
	public static void main(String[] args) {
		System.out.println("Cliente iniciado");
		QueHoraEsProtocolCliente p = new QueHoraEsProtocolCliente("localhost", 50894);

		while (p.nextStep() == 0) {
			// nothing
		}
	}
}
