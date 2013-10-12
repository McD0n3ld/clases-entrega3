package edu.upc.eetac.dsa.raul.ejercicio14.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.upc.eetac.dsa.raul.ejercicio12.servidor.TCPSocketsServidor;

public class TCPSockets extends TCPSocketsServidor {

	BufferedReader in = null;

	public TCPSockets(TCPSockets s) {
		super(s);
		this.in = s.getIn();
	}

	public TCPSockets(int port) {
		super(port);
	}

	@Override
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

	public void enviarFecha(int opcion) {
		if (opcion == 0)
			enviarFecha();
		else {
			Date dt = new Date();
			SimpleDateFormat format = new SimpleDateFormat("EEEE, dd MMMM yyyy, HH:mm:ss");
			writeLine(format.format(dt));
		}
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

}
