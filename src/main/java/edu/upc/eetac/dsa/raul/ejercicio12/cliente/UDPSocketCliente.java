package edu.upc.eetac.dsa.raul.ejercicio12.cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSocketCliente {

	DatagramSocket socket = null;
	InetAddress IPAddress = null;
	int port = 0;

	byte[] receiveData = new byte[1500];
	byte[] sendData = new byte[1500];

	DatagramPacket sendPacket = null;
	DatagramPacket receivePacket = null;

	public UDPSocketCliente(InetAddress ip, int port) {
		try {
			socket = new DatagramSocket();
			this.IPAddress = ip;
			this.port = port;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeLine(String line) {
		sendData = line.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		try {
			socket.send(sendPacket);
		} catch (IOException e) {
			System.out.println("Error al enviar");
		}
	}

	public String readLine() {
		String line = "null";
		try {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			socket.receive(receivePacket);
			line = new String(receivePacket.getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
	}

	public void close() {
		try {
			socket.close();
		} catch (Exception e) {
			System.out.println("Problemas al cerrar el socket");
		}
		return;
	}

}
