import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.PriorityQueue;


public class RoutingPerformance {
	
	//these are inputs frmo command line
	public static String networkScheme = null;
	public static String routingScheme = null;
	public static int packetRate;
	//this is the graph
	public static ArrayList<Node> graph = new ArrayList<Node>();
	public static ArrayList<Node> allnodes = new ArrayList<Node>();
	//the arraylist for the shortest path
	public static ArrayList<Node> shortestPath;
	//for all the statistics
	public static ArrayList<Integer> hops = new ArrayList<Integer>();
	public static ArrayList<Integer> cost = new ArrayList<Integer>();
	public static int successfulRequests=0;
	public static int busyRequests = 0;
	public static int virtualCircuitRequests=0;
	public static float requestSuccessRate=0;
	public static float busyRequestRate=0;

	public static void main(String[] args) throws FileNotFoundException {

		BufferedReader br;
		String  line;
		
		networkScheme = args[0];
		routingScheme = args[1];
		FileInputStream tFile = new FileInputStream(args[2]);
		FileInputStream wFile = new FileInputStream(args[3]);
		
		
		packetRate = Integer.parseInt(args[4]);
	    
		
		System.out.println("network scheme:"+ " " +networkScheme);
		System.out.println("routing scheme:"+ " " +routingScheme);
		
		
		Node A = new Node("A");
		allnodes.add(A);
		Node B = new Node("B");
		allnodes.add(B);
		Node C = new Node("C");
		allnodes.add(C);
		Node D = new Node("D");
		allnodes.add(D);
		Node E = new Node("E");
		allnodes.add(E);
		Node F = new Node("F");
		allnodes.add(F);
		Node G = new Node("G");
		allnodes.add(G);
		Node H = new Node("H");
		allnodes.add(H);
		Node I = new Node("I");
		allnodes.add(I);
		Node J = new Node("J");
		allnodes.add(J);
		Node K = new Node("K");
		allnodes.add(K);
		Node L = new Node("L");
		allnodes.add(L);
		Node M = new Node("M");
		allnodes.add(M);
		Node N = new Node("N");
		allnodes.add(N);
		Node O = new Node("O");
		allnodes.add(O);
		Node P = new Node("P");
		allnodes.add(P);
		Node Q = new Node("Q");
		allnodes.add(Q);
		Node R = new Node("R");
		allnodes.add(R);
		Node S = new Node("S");
		allnodes.add(S);
		Node T = new Node("T");
		allnodes.add(T);
		Node U = new Node("U");
		allnodes.add(U);
		Node V = new Node("V");
		allnodes.add(V);
		Node W = new Node("W");
		allnodes.add(W);
		Node X = new Node("X");
		allnodes.add(X);
		Node Y = new Node("Y");
		allnodes.add(Y);
		Node Z = new Node("Z");
		allnodes.add(Z);
		
		br = new BufferedReader(new InputStreamReader(tFile, Charset.forName("UTF-8")));
		try {
			while ((line = br.readLine()) != null) {
				String [] s = line.split(" ");
				String a = s[0];
				String b = s[1];
				int delay = Integer.parseInt(s[2]);
				int capacity = Integer.parseInt(s[3]);
				
				Node src = null;
				Node dest = null;
				
				for(Node n: allnodes){
					
					if(n.toString().equals(a)){
						src=n;
						//System.out.println("src "+src.getName());
					}
					if(n.toString().equals(b)){
						dest=n;
						//System.out.println("dest "+dest.getName());
					}
				}
				Edge e = null;
				e = new Edge(src,dest,delay,capacity);
				
				//if the node is not in the graph, we add it
				if(!graph.contains(src)){
					src.Neighbours.add(e);
				   graph.add(src);
				   //System.out.println("first if "+ src.toString());
				}
				//if the node is in the graph, we add neighours to that object
				else{
					for(Node n:graph){
						if(n.toString().equals(a)){
							src.Neighbours.add(e);
							//System.out.println("in else "+src.toString());
						}
					}
				}
				e = new Edge(dest,src,delay,capacity);
				if(!graph.contains(dest)){
					dest.Neighbours.add(e);
				   graph.add(dest);
				   //System.out.println("first if "+ src.toString());
				}
				//if the node is in the graph, we add neighours to that object
				else{
					for(Node n:graph){
						if(n.toString().equals(a)){
							dest.Neighbours.add(e);
							//System.out.println("in else "+src.toString());
						}
					}
				}
			    System.out.println(line);
			}
			
			//all the graph is created from topology file.
			//graph testing function
//			System.out.println("graph size: "+graph.size());
//			for(Node n: graph){
//				System.out.println("Src Node: "+n.toString());
//				System.out.println(n.Neighbours.size());
//				for(Edge e : n.Neighbours){
//					System.out.println("the srs and their neigbours and info: "+e.from+" "+e.to+" "+e.delay+" "+e.capacity);
//				}
//			}
//			Node source = null;
//			Node target= null;
//			for(Node n: graph){
//				if(n.toString().equals("B")){
//					source = n;
//				}
//				if(n.toString().equals("D")){
//					target = n;
//				}
//			}
			
			// where all the shit happens
			processWorkLoad(wFile);
			
			//all the stats
			requestSuccessRate = ((float)successfulRequests/virtualCircuitRequests * 100);
			busyRequestRate = ((float)busyRequests/virtualCircuitRequests * 100);

			    System.out.println("total number of virtual circuit requests: " + virtualCircuitRequests);

			    System.out.println("number of successfully routed requests: " + successfulRequests);
			 
			    System.out.printf("percentage of successfully routed request: %.2f\n", requestSuccessRate);
			    System.out.println("number of busy requests: " + busyRequests);
		
			    System.out.printf("percentage of busy requests: %.2f\n", busyRequestRate);

			    float average = 0;
			    if (!hops.isEmpty()) {
			      for (int i : hops)
			        average = average + i; 
			    }
		
			    System.out.printf("average number of hops per circuit: %.2f\n", (average/hops.size()));
			    average = 0;
			    if (!cost.isEmpty()) {
			      for (int i : cost)
			        average = average + i;
			    }
		
			    System.out.printf("average cumulative propagation delay per circuit: %.2f\n", (average/cost.size()));
//			System.out.println(target.toString());
//			
//			
//			DijkstraComputePath(source);
//			//ArrayList<Node> path = new ArrayList<Node>();
//			shortestPath = shortestPath(target);
//			
//			System.out.println("Printing shortest path"); 
//			for(Node n:shortestPath){
//				System.out.print("["+n.toString()+"] " +n.minDistance);
//			}
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void processWorkLoad(FileInputStream f){
	   //shortestPath.clear();
	   int currentTime = 0;
	   try {
			BufferedReader br = new BufferedReader(new InputStreamReader(f, Charset.forName("UTF-8")));
		
		      String line;
		 
		      System.out.println("Printing workload");
		      //the loop for reading every line
		      while ((line = br.readLine()) != null) {

		        String[] s = line.split(" ");
		        //srouce node
		        String aS = s[1];
		        //destination node
				String bD = s[2];
				Node src = null;
				Node dest = null;
				
				for(Node n: allnodes){
					
					if(n.toString().equals(aS)){
						src=n;
						//System.out.println("src "+src.getName());
					}
					if(n.toString().equals(bD)){
						dest=n;
						//System.out.println("dest "+dest.getName());
					}
				}
                // at this point src is holding the source node and dest is holding the destination node
		        //update every node's workload
				//need to fix for large inputs
		        BigDecimal a = new BigDecimal(s[0]).multiply(new BigDecimal("1000000."));
		        BigDecimal b = new BigDecimal(s[3]).multiply(new BigDecimal("1000000."));
		        
		        BigDecimal pr = new BigDecimal(s[3]);
		        int end = a.intValue();
		  
		        BigDecimal scaled = pr.setScale(0, RoundingMode.CEILING);
		        int packetsPerRequest = scaled.intValue() * packetRate;
		       // int pe = pr.ROUND_HALF_UP;
		        
		        System.out.println("rounded upto "+scaled);
		        System.out.println("b times packet rate of "+packetRate+" = "+ packetsPerRequest);
		        
		        int duration = a.intValue() + b.intValue();
		        //this is for packet
		        int interval = duration/packetsPerRequest;
		        System.out.println("interval "+interval);
		       // maybe keep an array of all the edges separately, and use that if this doesnt work
		    
		        
			       if(networkScheme.equals("CIRCUIT")) {
		               for(Node n: graph){
		            	   for(Edge e: n.Neighbours){
		            		   e.update(end);
		            	   }
		               }
		              // System.out.println("in here");
		            
		               System.out.println("src: "+src.toString()+" dest; "+dest.toString());
		           	
		       		   //reset all values for djikstra algorithm use
		    		   for(Node n:graph){
		    			   n.minDistance = Double.POSITIVE_INFINITY;
		    			   n.previous = null;
		    			   for(Edge e: n.Neighbours){
		    				   e.to.minDistance = Double.POSITIVE_INFINITY;
		    				   e.to.previous = null;
		    			   }
		    		   }
		    		   
		               DijkstraComputePath(src);
		               System.out.println("Printing shortest path"); 
		               shortestPath = shortestPath(dest,src);
		           	
		           	
		 			for(Node n:shortestPath){
						System.out.print("["+n.toString()+"] " +n.minDistance);
					}
		 			  System.out.println();
		               setWorkLoad(duration);
		               
				      	        
				      System.out.println("Finished request for " + src.toString() + " " + dest.toString() + " " + a.intValue() + " " + b.intValue() + "\n");
				          
			     }//end if statement    
				 if(networkScheme.equals("PACKET")){
					 int inc = 0;
						   
					 for(int i = 0;i<packetsPerRequest;i++){
					       end = end + inc;
					       duration = end + interval;
						   
						   for(Node n: graph){
			            	   for(Edge e: n.Neighbours){
			            		   e.update(end);
			            	   }
			               }
						   System.out.println("src: "+src.toString()+" dest; "+dest.toString());
						   DijkstraComputePath(src);
			               System.out.println("Printing shortest path"); 
			               shortestPath = shortestPath(dest,src);
			           	
			           	
				 			for(Node n:shortestPath){
								System.out.print("["+n.toString()+"] " +n.minDistance);
							}
			 			  System.out.println();
			               setWorkLoad(duration);
			               
			               inc = inc +interval;
						 				 
					 }
					 System.out.println("Finished request for " + src.toString() + " " + dest.toString() + " " + a.intValue() + " " + b.intValue() + "\n");
			          
									 
				 }//end of packet if
		      
		      
		      
		      
		      
		      
		      
		      }
		      br.close();
		      //System.out.println("total number of virtual circuit requests: " + numVCRequests);
		    }
		    catch (Exception e) {
		      System.err.println(e.getMessage()); // handle exception
		    }
	}
	public static void setWorkLoad(int duration){
		
		boolean busy = false;
		
		int delay = 0;
		int first=0;
		int second=1;
		int sizeOfShortestPath = shortestPath.size();
		
		while(second < sizeOfShortestPath){
			Node n1 = shortestPath.get(first);
			Node n2 = shortestPath.get(second);
			System.out.println("to match second val: "+n2.toString());
			
			//tryes to find the edge D->F
			for(Edge e: n1.Neighbours){
				if(e.to.equals(n2)){
					
					System.out.println("Matched: "+n1.toString()+" "+e.to.toString());
					if(e.busy()){
						busy = true;
						System.out.println("busy at " + e.from.toString() + " " + e.to.toString());
				        break; 
					}
				}				
			}
			first++;
			second++;
		}
		
		System.out.println("made it here");
		
		
//		
//		for(Node n:shortestPath){
//			for(Edge e: n.Neighbours){
//				if(e.busy()){
//					busy = true;
//					System.out.println("busy at " + e.from.toString() + " " + e.to.toString());
//			        break;      
//				}
//			}
//		}
		
		first = 0;
		second = 1;
		
		if(busy == false){
		   hops.add(shortestPath.size());	
		   while(second < sizeOfShortestPath){
				Node n1 = shortestPath.get(first);
				Node n2 = shortestPath.get(second);
				for(Edge e: n1.Neighbours){
					//mathcing the ed
					if(e.to.equals(n2)){
						e.use(duration);
						delay = delay+e.delay;

					}				
				}
				first++;
				second++;
			}
//		   for(Node n: shortestPath){
//			   for(Edge e: n.Neighbours){
//				   e.use(duration);
//				   delay = delay+e.delay;
//			   }
//		   }

		successfulRequests++;
		cost.add(delay);
		}
		else{
			busyRequests++;
		}
		virtualCircuitRequests++;
	}
	
	public static void DijkstraComputePath(Node src){
	
		  
		    src.minDistance = 0.;
	        PriorityQueue<Node> NodeQueue = new PriorityQueue<Node>();
	      	NodeQueue.add(src);

		while (!NodeQueue.isEmpty()) {
			double weight=1;
			double distanceThroughU=0;
		    Node n = NodeQueue.poll();
		    //System.out.println("starting node for search: "+n.toString());
           // System.out.println("Main node: "+n.toString());
	            // Visit each edge exiting u
	            for (Edge e : n.Neighbours){
	            	
	            
	                Node v = e.to;
	                //System.out.println("visiting: "+v.toString());
	                
	                if(routingScheme.equals("LLP")){
	                	weight = e.load;
	                	distanceThroughU = n.minDistance + weight;
	                	//System.out.println("nmindistance = "+n.minDistance);
	                }
	                else{
		            
		                if(routingScheme.equals("SHP")){
		                	weight = e.SHPDistance;
		                }
		                if(routingScheme.equals("SDP")){
		                	weight = e.delay;
		                }
		                
		                
		                distanceThroughU = n.minDistance + weight;
		                //System.out.println("nmindistance = "+n.minDistance);
	                }
					if (distanceThroughU < v.minDistance) {
					    NodeQueue.remove(v);
					    v.minDistance = distanceThroughU ;
					    v.previous = n;
					   
					    NodeQueue.add(v);
					    
					    //System.out.println("Added "+v.toString() +" to pq");
					}
	            }
	        }
	}
	public static ArrayList<Node> shortestPath(Node target,Node source){
    
        ArrayList<Node> path = new ArrayList<Node>();
        //System.out.println(target.toString());
        
        for (Node node = target; node != null; node = node.previous){
        	
        	// System.out.println("pathh Node "+node.toString());  
        	//sometimes goes into infinte loop for packet switching,
        
        	 path.add(node);
        	 if(node.name.equals(source.name)){
         		System.out.println("matched source and dest from shortest path");
         		 break;
         	}
        	
        	
        }
           
        Collections.reverse(path);
        return path;
    }

}



class Node implements Comparable<Node>{
   public final String name;
   public ArrayList<Edge> Neighbours = new ArrayList<Edge>();	
   public double minDistance = Double.POSITIVE_INFINITY;
   public Node previous = null;
   
   public Node(String name) {
	super();
	this.name = name;
	
   }
   public String toString() { return name; }
	@Override
	public int compareTo(Node o) {
		return Double.compare(minDistance, o.minDistance);
	}
	
	
}



class Edge{
	//from is not nessecary
	public final Node from;
	// to, delay and capacity
	public final Node to;
	public final int delay;
	public final int capacity;
	
	public int load = 0;
	public ArrayList<Integer> workLoad;
	//is when you use shp
	public final int SHPDistance = 1;
	
	
	public Edge(Node from, Node to, int delay, int capacity){
			
		super();
		this.from = from;
		this.to = to;
		this.delay = delay;
		this.capacity = capacity;
		this.workLoad = new ArrayList<Integer>();
	}
	public void print(){
		System.out.println(from.toString()+" "+to.toString()+" "+delay+" "+capacity);
	}
	public boolean busy(){
		return load >= capacity;
	}
	public void use(int duration){
		load++;
		workLoad.add(duration);
		System.out.print("load++ for edge " + from.toString() + "->" + to.toString() + " now " + load + "/" + capacity+ "\n" );
	}
	//this method updates the loads on circuits, to see if they are still alive, if not it will reduce the load
	public void update(int end){
		 ListIterator it = workLoad.listIterator();
	    while (it.hasNext()){
	        int curr = (Integer)it.next();
	        //-->>System.out.print("Updating curr for " + srcNode + " " + destNode + " " + curr + " - " + expired + " = " + (curr-expired) + "\n");
	        if (end >= curr){
	          it.remove();
	          load--;
	          System.out.println("Released finished load " + from.toString() + "->" + to.toString() + " now " + load + "/" + capacity);
	        }
	      }
		
		
	}

	
	
	
	
	
	
}