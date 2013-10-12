package edu.upc.eetac.dsa.raul.ejercicio12.servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPSocketsServidor {

	protected ServerSocket socket = null;
	protected Socket cliente = null;
	protected PrintWriter out = null;

	public TCPSocketsServidor(int port) {
		try {
			socket = new ServerSocket(port);
		} catch (Exception e) {
			System.out.println("No se ha podido abrir el server");
		}
	}
	
	public TCPSocketsServidor(TCPSocketsServidor s) {
		this.socket = s.getSocket();
		this.cliente = s.getCliente();
		this.out = s.getOut();
		return;
	}

	public void esperarCliente() {
		try {
			cliente = socket.accept();
			out = new PrintWriter(cliente.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public ServerSocket getSocket() {
		return socket;
	}

	public void setSocket(ServerSocket socket) {
		this.socket = socket;
	}

	public Socket getCliente() {
		return cliente;
	}

	public void setCliente(Socket cliente) {
		this.cliente = cliente;
	}

	public PrintWriter getOut() {
		return out;
	}

	public void setOut(PrintWriter out) {
		this.out = out;
	}

	
	
}
