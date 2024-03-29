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
		System.out.println("testhing this");
		GraphImple graph = new GraphImple();

		
		//WE need to hard code this for A-Z
	
		//We also need to hardcode this

		Graphnode A = new Graphnode("A");
		Graphnode B = new Graphnode("B");
		Graphnode C = new Graphnode("C");
		Graphnode D = new Graphnode("D");
		Graphnode E = new Graphnode("E");
		Graphnode F = new Graphnode("F");
		Graphnode G = new Graphnode("G");
		Graphnode H = new Graphnode("H");
		Graphnode I = new Graphnode("I");
		Graphnode J = new Graphnode("J");
		Graphnode K = new Graphnode("K");
		Graphnode L = new Graphnode("L");
		Graphnode M = new Graphnode("M");
		Graphnode N = new Graphnode("N");
		Graphnode O = new Graphnode("O");
		Graphnode P = new Graphnode("P");
		Graphnode Q = new Graphnode("Q");
		Graphnode R = new Graphnode("R");
		Graphnode S = new Graphnode("S");
		Graphnode T = new Graphnode("T");
		Graphnode U = new Graphnode("U");
		Graphnode V = new Graphnode("V");
		Graphnode W = new Graphnode("W");
		Graphnode X = new Graphnode("X");
		Graphnode Y = new Graphnode("Y");
		Graphnode Z = new Graphnode("Z");
		

		graph.addNode(A);
		graph.addNode(B);
		graph.addNode(C);
		graph.addNode(D);
		graph.addNode(E);
		graph.addNode(F);
		graph.addNode(G);
		graph.addNode(H);
		graph.addNode(I);
		graph.addNode(J);
		graph.addNode(K);

		//this loop for topology
		br = new BufferedReader(new InputStreamReader(tFile, Charset.forName("UTF-8")));
		while ((line = br.readLine()) != null) {
		    System.out.println(line);
		    // in here need to split nodes plus weight
		}
		//this loop for workload
		br = new BufferedReader(new InputStreamReader(wFile, Charset.forName("UTF-8")));
		while ((line = br.readLine()) != null) {
		    System.out.println(line);
		}
		
		//this comes from the file, we can put this on the loop.
		graph.addNonDEdge(A, B,5);
		graph.addNonDEdge(A, D,6);
		graph.addNonDEdge(B, C,7);
		graph.addNonDEdge(C, D,3);
		graph.addNonDEdge(D, G,5);
		graph.addNonDEdge(D, E,2);
		graph.addNonDEdge(E, F,5);
		graph.addNonDEdge(F, G,7);
		graph.addNonDEdge(F, J,8);
		graph.addNonDEdge(G, J,2);
		graph.addNonDEdge(G, H,4);
		graph.addNonDEdge(J, I,6);
		graph.addNonDEdge(I, K,8);
		
		//graph.getNeighbors(a);
		ArrayList<Graphnode> path = graph.bfs(A, K);
	    printList(path);

		graph.addNode(L);
		graph.addNode(M);
		graph.addNode(N);
		graph.addNode(O);
		graph.addNode(P);
		graph.addNode(Q);
		graph.addNode(R);
		graph.addNode(S);
		graph.addNode(T);
		graph.addNode(U);
		graph.addNode(V);
		graph.addNode(W);
		graph.addNode(X);
		graph.addNode(Y);
		graph.addNode(Z);
		
		/**
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
		**/
		
		//ArrayList<Graphnode> path = graph.bfs(a, k);
	//	printList(path);


	}
	public static void printList(ArrayList<Graphnode> list){
		for(Graphnode n : list){
			System.out.print("[" + n.getName() + "]");
		}
	}
	//adding comments

}