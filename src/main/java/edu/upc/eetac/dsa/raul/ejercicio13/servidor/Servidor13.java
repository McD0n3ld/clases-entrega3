package edu.upc.eetac.dsa.raul.ejercicio13.servidor;

public class Servidor13 
{
	public static void start()
    {
    	System.out.println("Servidor iniciado");
    	QueHoraEsProtocolServidorConcurrente pt = new QueHoraEsProtocolServidorConcurrente(50894);
    	   	
    	while(pt.nextStep() == 0) {
    		//nothing
    	}  	
    	
    	System.out.println("Ejercicio finalizado");
    	
    }
}
