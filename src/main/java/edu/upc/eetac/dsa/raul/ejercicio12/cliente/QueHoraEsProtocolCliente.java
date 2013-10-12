package edu.upc.eetac.dsa.raul.ejercicio12.cliente;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class QueHoraEsProtocolCliente {

	protected int estado = 1;
	protected TCPSocketsCliente s = null;
	UDPSocketCliente udp = null;

	protected String ip = "";
	protected int port = 0;
	protected int intentos = 0;

	public QueHoraEsProtocolCliente(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public int nextStep() {
		switch (estado) {
		case 1:
			System.out.println("Abriendo socket con el cliente");
			s = new TCPSocketsCliente(ip, port);
			if (s.ready)
				estado = 3;
			else
				intentos++;
			if (intentos > 3)
				estado = 98;
			break;

		case 3:
			intentos = 0;
			System.out.println("Esperando lectura");
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

}
