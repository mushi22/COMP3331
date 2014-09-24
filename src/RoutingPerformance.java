
public class RoutingPerformance {
    
	private static String networkScheme = null;
	private static String routingScheme = null;
	
	
	private static int packetRate;
	public static void main(String[] args) {
		
		networkScheme = args[0];
		routingScheme = args[1];
		
		packetRate = Integer.parseInt(args[4]);

	}

}
