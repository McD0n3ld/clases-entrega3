package edu.upc.eetac.dsa.raul.red.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class QueHoraEsProtocolCliente {

	private int estado = 1;
	TCPSocketsCliente s = null;
	UDPSocketCliente udp = null;

	String ip = "";
	int port = 0;

	public QueHoraEsProtocolCliente(String ip, int port) {
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
			System.out.println("Leer numero");
			int temp = leerNumero();
			if (temp != -1) { // si no es correcto, volvera a pedir el numero
				s.writeLine(temp);
				System.out.println("Se ha enviado un " + temp);
				estado = 3;
			}
			break;

		case 3:
			System.out.println("Esperando lectura");
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

	public int nextStepUDP() {
		switch (estado) {
		case 1:
			System.out.println("Abriendo socket con el cliente");
			try {
				udp = new UDPSocketCliente(InetAddress.getByName("localhost"), port);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				break;
			}
			estado = 2;
			break;

		case 2:
			System.out.println("Conectar al server");
			udp.writeLine("default message");
			estado = 3;
			break;

		case 3:
			System.out.println("Recibir fecha");
			System.out.println(udp.readLine());
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

	private int leerNumero() {
		int ejercicio = -1;
		String t = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("0 -> dd/MM/yyyy hh:mm:ss\n1 -> WWW, DDD de MMM, hh:mm:ss");
			t = br.readLine();
			ejercicio = Integer.parseInt(t);
		} catch (Exception e) {
			System.out.println("No has introducido un numero");
		}
		if (ejercicio < 0 || ejercicio > 2)
			return -1;
		return ejercicio;
	}

}
