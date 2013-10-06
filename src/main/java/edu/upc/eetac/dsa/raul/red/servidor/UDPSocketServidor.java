package edu.upc.eetac.dsa.raul.red.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPSocketServidor {

	DatagramSocket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;
	InetAddress IPAddress = null;
	int port = 0;

	byte[] receiveData = new byte[1500];
	byte[] sendData = new byte[1500];

	DatagramPacket sendPacket = null;
	DatagramPacket receivePacket = null;

	public UDPSocketServidor(int port) {
		try {
			socket = new DatagramSocket(port);
		} catch (Exception e) {

		}
	}

	public void esperarCliente() {
		try {
			receivePacket = new DatagramPacket(receiveData, receiveData.length);
			socket.receive(receivePacket);
			IPAddress = receivePacket.getAddress();
			port = receivePacket.getPort();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enviarFecha() {
		Date dt = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		sendData = format.format(dt).getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		try {
			socket.send(sendPacket);
		} catch (IOException e) {
			System.out.println("Error al enviar el paquete");
		}
	}

	public void close() {
		try {
			socket.close();
		} catch (Exception e) {
			System.out.println("Problemas al cerrar el socket servidor");
		}
		return;
	}

}
