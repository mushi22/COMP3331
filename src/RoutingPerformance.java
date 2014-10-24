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
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;


public class RoutingPerformance {
	
	//these are inputs from command line
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
	
	public static int totalPackets = 0;
	public static int currentPacketValue = 0;


	public static void main(String[] args) throws FileNotFoundException {

		BufferedReader br;	
		String  line;
		
		//read in which network scheme its going to be
		networkScheme = args[0];
		//read in which routing scheme its going to be
		routingScheme = args[1];
		//read in toplogy file
		FileInputStream tFile = new FileInputStream(args[2]);
		//read in workload file
		FileInputStream wFile = new FileInputStream(args[3]);
		
		//get the packet rate and convert to integer
	    packetRate = Integer.parseInt(args[4]);
	    
		//system out of netowkring scheme and routing scheme
		//System.out.println("network scheme:"+ " " +networkScheme);
		//System.out.println("routing scheme:"+ " " +routingScheme);
		
		//generate all the nodes from A-Z manually
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
		
		
		//use buffer reader to read in topology file and
		br = new BufferedReader(new InputStreamReader(tFile, Charset.forName("UTF-8")));
		try {
			while ((line = br.readLine()) != null) {
				String [] s = line.split(" ");
				// read in source node
				String a = s[0];
				//read in destination node
				String b = s[1];
				//read in delay from topology file
				int delay = Integer.parseInt(s[2]);
				//read in capacity from the toplogy file
				int capacity = Integer.parseInt(s[3]);
				
				Node src = null;
				Node dest = null;
				
				
				//for loop to find out what nodes on graph Src and destion are
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
				//create edge using information provided 
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
			   // System.out.println(line);
			}
			

			
			// calls the function which processes the workload file and does all the calulcaiton
			processWorkLoad(wFile);
			
			//all the system outs at the end of the program
			requestSuccessRate = ((float)successfulRequests/totalPackets * 100);
			busyRequestRate = ((float)busyRequests/totalPackets * 100);

			System.out.println("total number of virtual circuit requests: " + virtualCircuitRequests);

			System.out.println("total number of packets: "+ totalPackets);

			System.out.println("number of successfully routed packets: " + successfulRequests);
			 
			System.out.printf("percentage of successfully routed packets: %.2f\n", requestSuccessRate);

			
			System.out.println("number of blocked packets: " + busyRequests);
		
			System.out.printf("percentage of blocked packets: %.2f\n", busyRequestRate);
			    
			    

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

			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	//the function gets the workload.txt file and processes
	public static void processWorkLoad(FileInputStream f){
	   //shortestPath.clear();
	   int currentTime = 0;
	   try {
			BufferedReader br = new BufferedReader(new InputStreamReader(f, Charset.forName("UTF-8")));
		
		      String line;
		 
		    //  System.out.println("Printing workload");
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
				
				
		
				//we use big decimal as it can hold more data
				//we get rid of the decimal point to make the manpulation of numbers easer
				BigDecimal a = new BigDecimal(s[0].replace(".", ""));
		        BigDecimal b = new BigDecimal(s[3].replace(".", ""));

		        
		        //this is the packet rate in big decimal
		        BigDecimal pr = new BigDecimal(s[3]);
		        String one = "1";
		       
		     
		        // cover the a into a long (int cant hold larger values)
		        Long end = a.longValue();
		  
		        //round the packet rate to the ceiling and who number
		        BigDecimal scaled = pr.setScale(0, RoundingMode.CEILING);
		        
		 
		        long packetsPerRequest = scaled.longValue() * packetRate;
		        currentPacketValue = (int) packetsPerRequest;
		        totalPackets = (int) (totalPackets + packetsPerRequest);
		        
		        float inter = (1/packetRate);
		       // System.out.print("interval as a float " + inter);
		      //  System.out.println("rounded upto "+scaled);
		       // System.out.println("b times packet rate of "+packetRate+" = "+ packetsPerRequest);
		        
		        //total duration of packets
		        long duration = a.longValue() + b.longValue();
	

		        //long duration = scaled.longValue();
		        //calculate the interval based on duration and packets per request
		        //long test = 2 * b.longValue();
		       // packetDuration = 1/ packetRate;
		        long interval =  duration/packetsPerRequest;
		        
		           //check what the network scheme is, for circuit do the following
			       if(networkScheme.equals("CIRCUIT")) {
		               for(Node n: graph){
		            	   for(Edge e: n.Neighbours){
		            		   e.update(end);
		            	   }
		               }
		            
		               //System.out.println("src: "+src.toString()+" dest; "+dest.toString());
		           	
		       		
		    		   //call dijstra based on the src node
		               DijkstraComputePath(src);
		              // System.out.println("Printing shortest path"); 
		               //call the shortestpath function, that uses destination and src
		               shortestPath = shortestPath(dest,src);
		           	
		           	
		 			for(Node n:shortestPath){
						//System.out.print("["+n.toString()+"] " +n.minDistance);
					}
		               setWorkLoad(duration);
		               
				      	        
	     		      //System.out.println("Finished request for " + src.toString() + " " + dest.toString() + " " + a.longValue() + " " + b.longValue() + "\n");
	     		     //reset all values for djikstra algorithm values
		    		  for(Node n:graph){
		    			   n.minDistance = Double.POSITIVE_INFINITY;
		    			   n.previous = null;
		    			   for(Edge e: n.Neighbours){
		    				   e.to.minDistance = Double.POSITIVE_INFINITY;
		    				   e.to.previous = null;
		    			   }
		    		   }
			     }//end if statement    
			     //for PACKET network scheme do the following under the if statement
				 if(networkScheme.equals("PACKET")){
					 //changed long inc = 0 to long inc = interval
					 //this made success rate go up .20%. (think the first interval was being set to 0 at first
					 //when it wasnt 0
					 //still going down, so we are not unfreeing update properly
					 long inc = interval;
					 long ending = end;
					 long d = 0;
					 for(int i = 0;i<packetsPerRequest;i++){
						  // System.out.println(inc);
					       ending = ending + inc;
					       //System.out.println(ending);
					       d = end + interval;
					       //System.out.println("output of end from packet "+end);
					       
					       for(Node n: graph){
			            	   for(Edge e: n.Neighbours){
			            		   e.update(ending);
			            	   }
			               }
				
						  // System.out.println("src: "+src.toString()+" dest; "+dest.toString());
						
				         
				         
						   DijkstraComputePath(src);
			              
			               
			               shortestPath = shortestPath(dest,src);
			           	
			           	
				 			for(Node n:shortestPath){
								//System.out.print("["+n.toString()+"] " +n.minDistance);
							}
				 			
				 			 
							
			               setWorkLoad(duration);
			               
					      
			               inc = interval;
			               //reset all values for djikstra algorithm 
				    		  for(Node n:graph){
				    			   n.minDistance = Double.POSITIVE_INFINITY;
				    			   n.previous = null;
				    			   for(Edge e: n.Neighbours){
				    				   e.to.minDistance = Double.POSITIVE_INFINITY;
				    				   e.to.previous = null;
				    			   }
				    		   }
			             
						 				 
					 }
					 //System.out.println("Finished request for " + src.toString() + " " + dest.toString() + " " + a.longValue() + " " + b.longValue()+ "\n");
									 
				 }//end of packet if
		      
		      
		      
		      
		      
		      
		      
		      }
		      br.close();
		    }
		    catch (Exception e) {
		      System.err.println(e.getMessage()); // handle exception
		    }
	}
	
	
	//the function calculates the workload using the duration
	public static void setWorkLoad(long duration){
		
		boolean busy = false;
		
		long delay = 0;
		int first=0;
		int second=1;
		int sizeOfShortestPath = shortestPath.size();
		
		while(second < sizeOfShortestPath){
			Node n1 = shortestPath.get(first);
			Node n2 = shortestPath.get(second);
			//System.out.println("to match second val: "+n2.toString());
			
			//tries to find the edge e.g. D->F
			for(Edge e: n1.Neighbours){
				if(e.to.equals(n2)){
					
					//System.out.println("Matched: "+n1.toString()+" "+e.to.toString());
					if(e.busy()){
						busy = true;
						//System.out.println("busy at " + e.from.toString() + " " + e.to.toString());
				        break; 
					}
				}				
			}
			first++;
			second++;
		}
		//do the same as top but with n1 and n2 flipped (so its both ways)
		first = 0;
		second =1;
		while(second < sizeOfShortestPath){
			while(second < sizeOfShortestPath){
				Node n1 = shortestPath.get(first);
				Node n2 = shortestPath.get(second);
				//System.out.println("to match second val: "+n2.toString());
				
				//tryes to find the edge D->F
				for(Edge e: n2.Neighbours){
					if(e.to.equals(n1)){
						
						//System.out.println("Matched: "+n1.toString()+" "+e.to.toString());
						if(e.busy()){
							busy = true;
							//System.out.println("busy at " + e.from.toString() + " " + e.to.toString());
					        break; 
						}
					}				
				}
				first++;
				second++;
			}
		}	
		

		// calculates hops
		first = 0;
		second = 1;
		
		if(busy == false){
			//subtract 1 for the hops
		   hops.add(shortestPath.size()-1);	
		   // do for n1 -> n2
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
		   //do it reverse, for n2 -> n1
		   first = 0;
			second = 1;
			while(second < sizeOfShortestPath){
				Node n1 = shortestPath.get(first);
				Node n2 = shortestPath.get(second);
				for(Edge e: n2.Neighbours){
					//mathcing the ed
					if(e.to.equals(n1)){
						e.use(duration);

					}				
				}
				first++;
				second++;
				
				
			}
		//based on network scheme find total amount of successful requests	
	    if(networkScheme.equals("CIRCUIT")){
		   successfulRequests=successfulRequests + currentPacketValue;
	    }
	    if(networkScheme.equals("PACKET")){
	    		
			   //successfulRequests=successfulRequests + packetRate;
			  
				   successfulRequests=successfulRequests + 1;
			   
		    }
	    //add the delay to the cost
		cost.add((int) delay);
		}else{
			if(networkScheme.equals("CIRCUIT")){
				busyRequests=busyRequests+currentPacketValue;
			    }
			 if(networkScheme.equals("PACKET")){
			     busyRequests=busyRequests+ 1;
		     }
			
		}
		virtualCircuitRequests++;
	}
	
	//this function implements the Dijkstra based on the source node.
	//this handles based on routing scheme what occurs
	public static void DijkstraComputePath(Node src){
	
		  
		    src.minDistance = 0.;
		    // have a priority queue which has all the nodes, and each src node is added to it
	        PriorityQueue<Node> NodeQueue = new PriorityQueue<Node>();
	      	NodeQueue.add(src);

		while (!NodeQueue.isEmpty()) {
			double weight=1;
			double distanceThroughU=0;
		    Node n = NodeQueue.poll();

	            // Visit each edge exiting u
	            for (Edge e : n.Neighbours){
	            	
	            
	                Node v = e.to;
	                
	                if(routingScheme.equals("LLP")){
	                	int w = (int) Math.max(n.minDistance,e.load);
	                	distanceThroughU = n.minDistance + w;
	                }else{
		            
		                if(routingScheme.equals("SHP")){
		                	weight = e.SHPDistance;
		                }
		                if(routingScheme.equals("SDP")){
		                	weight = e.delay;
		                }
		                
		                
		                distanceThroughU = n.minDistance + weight;
	                }
					if (distanceThroughU < v.minDistance) {
					    NodeQueue.remove(v);
					    v.minDistance = distanceThroughU;
					    v.previous = n;
					   
					    NodeQueue.add(v);
					    
					}
	            }
	        }
	}
	
	//the funtion calculates the shortest path using ArrayList
	public static ArrayList<Node> shortestPath(Node target,Node source){
    
        ArrayList<Node> path = new ArrayList<Node>();
       // System.out.println(target.toString());
        
        
        for (Node node = target; node != null; node = node.previous){
       
        	 path.add(node);
           
        	 if(node.name.equals(source.name)){
         		//System.out.println("matched source and dest from shortest path");
         		 break;
         	}
        	
        	
        }
           
        Collections.reverse(path);
        return path;
    }
	


}


//The node class handles all nodes in out graph
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


//This class handles and creates the edges in the graph.
//E.g. Form A->B, it will create the edge and that ediges properites
class Edge{

	public final Node from;
	// to, delay and capacity
	public final Node to;
	public final long delay;
	public final long capacity;
	
	public long load = 0;
	public ArrayList<Integer> workLoad;
	//is when you use shp
	public final long SHPDistance = 1;
	
	//function that creates edge from src to destination
	//also has delay and capcity values of this lingk
	public Edge(Node from, Node to, long delay, long capacity){
			
		super();
		this.from = from;
		this.to = to;
		this.delay = delay;
		this.capacity = capacity;
		this.workLoad = new ArrayList<Integer>();
	}
	//print out edge
	public void print(){
		//System.out.println(from.toString()+" "+to.toString()+" "+delay+" "+capacity);
	}
	//checks if edge is busy
	public boolean busy(){
		return load >= capacity;
	}
	public void use(long duration){
		load++;
		workLoad.add((int) duration);
		//System.out.print("load++ for edge " + from.toString() + "->" + to.toString() + " now " + load + "/" + capacity+ "\n" );
	}
	//this method updates the loads on circuits, to see if they are still alive, if not it will reduce the load
	public void update(Long end){
		 ListIterator it = workLoad.listIterator();
	    while (it.hasNext()){
	        int curr = (Integer)it.next();
	        //-->>System.out.print("Updating curr for " + srcNode + " " + destNode + " " + curr + " - " + expired + " = " + (curr-expired) + "\n");
	        if (end >= curr){
	          it.remove();
	          load--;
	         // System.out.println("Released finished load " + from.toString() + "->" + to.toString() + " now " + load + "/" + capacity);
	        }
	      }
		
		
	}

	
}