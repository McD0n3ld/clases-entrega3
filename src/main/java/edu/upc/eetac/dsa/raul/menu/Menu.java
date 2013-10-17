package edu.upc.eetac.dsa.raul.menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import edu.upc.eetac.dsa.raul.ejercicio12.cliente.ClienteTCP;
import edu.upc.eetac.dsa.raul.ejercicio12.cliente.ClienteUDP;
import edu.upc.eetac.dsa.raul.ejercicio12.servidor.ServidorTCP;
import edu.upc.eetac.dsa.raul.ejercicio12.servidor.ServidorUDP;
import edu.upc.eetac.dsa.raul.ejercicio13.cliente.Cliente13;
import edu.upc.eetac.dsa.raul.ejercicio13.servidor.Servidor13;
import edu.upc.eetac.dsa.raul.ejercicio14.cliente.Cliente14;
import edu.upc.eetac.dsa.raul.ejercicio14.servidor.Servidor14;
import edu.upc.eetac.dsa.raul.ejercicio15.cliente.ClienteChinos;
import edu.upc.eetac.dsa.raul.ejercicio15.servidor.ServidorChinos;

public class Menu {

	public static void main(String[] args) {
		String t = "";
		int ejercicio = 0;
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.println("Introduce un número.");
			System.out.println("     1. Ejercicio 12 - Cliente TCP");
			System.out.println("     2. Ejercicio 12 - Servidor TCP");
			System.out.println();
			System.out.println("     3. Ejercicio 12 - Cliente UDP");
			System.out.println("     4. Ejercicio 12 - Servidor UDP");
			System.out.println();
			System.out.println("     5. Ejercicio 13 - Cliente TCP");
			System.out.println("     6. Ejercicio 13 - Servidor Concurrente TCP");
			System.out.println();
			System.out.println("     7. Ejercicio 14 - Cliente TCP");
			System.out.println("     8. Ejercicio 14 - Servidor TCP Concurrente");
			System.out.println();
			System.out.println("     9. Ejercicio 15 - Cliente TCP");
			System.out.println("	10. Ejercicio 15 - Servidor TCP Chinos");
			t = s.readLine();
			ejercicio = Integer.parseInt(t);
		} catch (Exception e) {
			System.out.println("No has introducido un numero del 1 al 2");
			return;
		}

		switch (ejercicio) {
		case 1:
			ClienteTCP.start();
			break;

		case 2:
			ServidorTCP.start();
			break;

		case 3:
			ClienteUDP.start();
			break;

		case 4:
			ServidorUDP.start();
			break;

		case 5:
			Cliente13.start();
			break;

		case 6:
			Servidor13.start();
			break;

		case 7:
			Cliente14.start();
			break;

		case 8:
			Servidor14.start();
			break;

		case 9:
			ClienteChinos.start();
			break;

		case 10:
			ServidorChinos.start();
			break;

		case 0:
			System.out.println("Fin del test");
			return;

		default:
			break;
		}

	}

}
