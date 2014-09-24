import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;


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
		
		GraphImple graph = new GraphImple();
		Graphnode a = new Graphnode("a");
		Graphnode b = new Graphnode("b");
		Graphnode c = new Graphnode("c");
		Graphnode d = new Graphnode("d");
		Graphnode e = new Graphnode("e");
		Graphnode f = new Graphnode("f");
		Graphnode g = new Graphnode("g");
		Graphnode h = new Graphnode("h");
		Graphnode i = new Graphnode("i");
		Graphnode j = new Graphnode("j");
		Graphnode k = new Graphnode("k");
		
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		graph.addNode(e);
		graph.addNode(f);
		graph.addNode(g);
		graph.addNode(h);
		graph.addNode(i);
		graph.addNode(j);
		graph.addNode(k);
		
		graph.addNonDEdge(a, b);
		graph.addNonDEdge(a, d);
		graph.addNonDEdge(b, c);
		graph.addNonDEdge(c, d);
		graph.addNonDEdge(d, g);
		graph.addNonDEdge(d, e);
		graph.addNonDEdge(e, f);
		graph.addNonDEdge(f, g);
		graph.addNonDEdge(f, j);
		graph.addNonDEdge(g, j);
		graph.addNonDEdge(g, h);
		graph.addNonDEdge(j, i);
		graph.addNonDEdge(i, k);
		
		ArrayList<Graphnode> path = graph.bfs(a, k);
		printList(path);

	}
	public static void printList(ArrayList<Graphnode> list){
		for(Graphnode n : list){
			System.out.print("[" + n.getName() + "]");
		}
	}
	//adding comments

}