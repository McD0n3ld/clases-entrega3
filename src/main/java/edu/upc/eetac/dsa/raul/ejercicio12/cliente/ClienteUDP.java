package edu.upc.eetac.dsa.raul.ejercicio12.cliente;

public class ClienteUDP {
	public static void main(String[] args) {
		System.out.println("Cliente iniciado");
		QueHoraEsProtocolCliente p = new QueHoraEsProtocolCliente("localhost", 50894);

		while (p.nextStepUDP() == 0) {
			// nothing
		}

		System.out.println("Fin");
	}
}
