package edu.upc.eetac.dsa.raul.red.cliente;

public class Cliente 
{
    @SuppressWarnings("unused")
	public static void main( String[] args )
    {
    	System.out.println("Cliente iniciado");
    	QueHoraEsProtocolCliente p = new QueHoraEsProtocolCliente("localhost",50894);
    	JuegoChinosClientProtocol jccp = new JuegoChinosClientProtocol("localhost",50894);
    	
//    	while(p.nextStep() == 0) {
//    		// nothing
//    	}
    	
//    	while(p.nextStepUDP() == 0) {
//    		// nothing
//    	}
    	
    	while(jccp.nextStep() == 0) {
    		// nothing
    	}
    	
    }
}
