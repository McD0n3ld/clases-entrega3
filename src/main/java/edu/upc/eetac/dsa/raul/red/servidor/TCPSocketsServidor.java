package edu.upc.eetac.dsa.raul.red.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPSocketsServidor {

	ServerSocket socket = null;
	Socket cliente = null;
	PrintWriter out = null;
	BufferedReader in = null;

	public TCPSocketsServidor(int port) {
		try {
			socket = new ServerSocket(port);
		} catch (Exception e) {
			System.out.println("No se ha podido abrir el server");
		}
	}

	public void esperarCliente() {
		try {
			cliente = socket.accept();
			out = new PrintWriter(cliente.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String readLine() {
		String line = "null";
		try {
			line = in.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
	}

	public void writeLine(String line) {
		out.println(line);
		return;
	}
	
	public void enviarFecha() {
		Date dt = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		writeLine(format.format(dt));
	}

	public void enviarFecha(int opcion) {
		if (opcion == 0)
			enviarFecha();
		else {
			Date dt = new Date();
			SimpleDateFormat format = new SimpleDateFormat("EEEE, dd MMMM yyyy, HH:mm:ss");
			writeLine(format.format(dt));
		}
	}

	public void cerrarCliente() {
		try {
			cliente.close();
			out.close();
		} catch (Exception e) {
			System.out.println("Problemas al cerrar el socket cliente");
		}
		return;
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
