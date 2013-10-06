package edu.upc.eetac.dsa.raul.red.cliente;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPSocketsCliente {

	Socket socket = null;
	PrintWriter out = null;
	BufferedReader in = null;

	public TCPSocketsCliente(String ip, int port) {
		try {
			socket = new Socket(ip, port);
			System.out.println(socket);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (Exception e) {

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
	
	public void writeLine(int line) {
		try {
			out.write(line+"\n");
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public void close() {
		try {
			in.close();
			out.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("Problemas al cerrar el socket");
		}
		return;
	}

}
