import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class RoutingPerformance {
    
	private static String networkScheme = null;
	private static String routingScheme = null;
	
	
	private static int packetRate;
	public static void main(String[] args) throws FileNotFoundException {
		
		networkScheme = args[0];
		routingScheme = args[1];
		FileInputStream tFile = new FileInputStream(args[2]);
		FileInputStream wFile = new FileInputStream(args[3]);
		
		packetRate = Integer.parseInt(args[4]);

	}
	//adding comments

}