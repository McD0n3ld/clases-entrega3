package edu.upc.eetac.dsa.raul.ejercicio15.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import edu.upc.eetac.dsa.raul.ejercicio12.cliente.TCPSocketsCliente;

public class Protocolo {

	private int estado = 1;
	TCPSocketsCliente s = null;

	String ip = "";
	int port = 0;

	String nombre = "";
	String apuesta = "";

	String line = "";
	int intentos = 0;

	public Protocolo(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public int nextStep() {
		switch (estado) {
		case 1:
			System.out.println("Abriendo conexion con el server");
			s = new TCPSocketsCliente(ip, port);
			if (s.ready)
				estado = 2;
			else
				intentos++;
			if (intentos > 3)
				estado = 98;
			break;

		case 2:
			System.out.println("Enviar PLAY [nombre]. Indica tu nombre");
			nombre = leerLinea();
			s.writeLine("PLAY " + nombre);
			estado = 3;
			break;

		case 3:
			System.out.println("Recibiendo respuesta");
			line = s.readLine();
			System.out.println(line);
			if (line.equals("WAIT"))
				estado = 4;
			else {
				estado = 5;
			}
			break;

		case 4:
			System.out.println("Hemos recibido WAIT. Esperando...");
			line = s.readLine();
			System.out.println(line);
			if (line.equals("YOUR BET"))
				estado = 5;
			else
				estado = 4;
			break;

		case 5:
			System.out.println("Hemos recibido YOUR BET. Esperando...");
			System.out.println("Cual es tu apuesta. [JUEGAS] [TOTALES]");
			apuesta = leerLinea();
			s.writeLine(apuesta);
			estado = 6;
			break;

		case 6:
			System.out.println("Recibiendo respuesta....");
			System.out.println(s.readLine());
			estado = 99;
			break;
			
		case 98:
			System.out.println("Cerrando cliente");
			return -1;

		case 99:
			System.out.println("Cerrando socket");
			s.close();
			estado = 1;
			return -1;

		default:
			System.out.println("Wrong state\nReiniciando protocolo");
			estado = 1;
			break;
		}
		return 0;
	}

	private String leerLinea() {
		String t = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			t = br.readLine();
		} catch (Exception e) {
		}
		return t;
	}

}
