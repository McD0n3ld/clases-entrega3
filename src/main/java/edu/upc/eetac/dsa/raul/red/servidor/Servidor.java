package edu.upc.eetac.dsa.raul.red.servidor;

public class Servidor 
{
    public static void main( String[] args )
    {
    	System.out.println("Servidor iniciado");
    	QueHoraEsProtocolServidor p = new QueHoraEsProtocolServidor(50894);
    	QueHoraEsProtocolServidorConcurrente pt = new QueHoraEsProtocolServidorConcurrente(50894);
    	
//    	while(p.nextStep() == 0) {
//    		//nothing
//    	}
    	
//    	while(p.nextStepUDP() == 0) {
//    		//nothing
//    	}
    	
    	while(pt.nextStep() == 0) {
    		//nothing
    	}
    	
    	
    	System.out.println("Ejercicio finalizado");
    	
    }
}
