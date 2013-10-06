package edu.upc.eetac.dsa.raul.red.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JuegoChinosClientProtocol {

	private int estado = 1;
	TCPSocketsCliente s = null;
	UDPSocketCliente udp = null;

	String ip = "";
	int port = 0;
	
	String nombre = "";
	String apuesta = "";

	public JuegoChinosClientProtocol(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public int nextStep() {
		switch (estado) {
		case 1:
			System.out.println("Abriendo socket con el cliente");
			s = new TCPSocketsCliente(ip, port);
			estado = 2;
			break;

		case 2:
			System.out.println("Enviar PLAY [nombre]. Indica tu nombre");
			nombre = leerLinea();
			s.writeLine("PLAY "+nombre);
			estado = 3;
			break;

		case 3:
			System.out.println("Recibiendo respuesta");
			String line = s.readLine();
			if (line == "WAIT")
				estado = 4;
			else {
				System.out.println(line);
				estado = 5;
			}
			break;

		case 4:
			System.out.println("Hemos recibido WAIT. Esperando...");
			if (s.readLine() == "YOUR BET")
				estado = 5;
			else
				estado = 4;
			break;
			
		case 5:
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

		case 99:
			System.out.println("Cerrando socket");
			s.close();
			estado = 1;
			return -1;
			// break;

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
