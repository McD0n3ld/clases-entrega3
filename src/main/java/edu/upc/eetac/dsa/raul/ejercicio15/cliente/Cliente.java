package edu.upc.eetac.dsa.raul.ejercicio15.cliente;

public class Cliente {
	public static void main(String[] args) {
		System.out.println("Cliente iniciado");
		Protocolo jccp = new Protocolo("localhost", 50894);

		while (jccp.nextStep() == 0) {
			// nothing
		}

	}
}
