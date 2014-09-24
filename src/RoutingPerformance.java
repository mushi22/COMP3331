import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;


public class RoutingPerformance {
    
	private static String networkScheme = null;
	private static String routingScheme = null;
	
	
	private static int packetRate;
	public static void main(String[] args) throws IOException {
		BufferedReader br;
		String  line;
		
		networkScheme = args[0];
		routingScheme = args[1];
		FileInputStream tFile = new FileInputStream(args[2]);
		
		FileInputStream wFile = new FileInputStream(args[3]);
		
		packetRate = Integer.parseInt(args[4]);
        
		
		System.out.println("network scheme:"+ " " +networkScheme);
		System.out.println("routing scheme:"+ " " +routingScheme);
		
		br = new BufferedReader(new InputStreamReader(tFile, Charset.forName("UTF-8")));
		while ((line = br.readLine()) != null) {
		    System.out.println(line);
		}
		br = new BufferedReader(new InputStreamReader(wFile, Charset.forName("UTF-8")));
		while ((line = br.readLine()) != null) {
		    System.out.println(line);
		}
		System.out.println("packet rate:"+ " " +packetRate);
	}
	//adding comments

}