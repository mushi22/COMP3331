import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class GraphImple implements Graph<Graphnode> {

	public ArrayList<Graphnode> nodes;
	public ArrayList<HashMap<Graphnode,Integer>> directedTo;

	private int nodeCount = 0;
	public int edgeCount = 0;

	public GraphImple() {
		nodes = new ArrayList<Graphnode>();
		directedTo = new ArrayList<HashMap<Graphnode,Integer>>();
	}

	@Override
	public void addNode(Graphnode node) {
		// TODO Auto-generated method stub
		//if nodes array doesnt have it, then we can add it
		if (!nodes.contains(node)) {
			nodes.add(node);
			directedTo.add(new HashMap<Graphnode,Integer>());
			setNodeCount(getNodeCount() + 1);
		}
	}

	public void addEdge(Graphnode from, Graphnode to, int weight) {
		// TODO Auto-generated method stub
		//if both nodes passed in, are in the all nodes array
		if (nodes.contains(from) && nodes.contains(to)) {
			//get index of the from node
			int j = nodes.indexOf(from);
            //checking that the edge hasnt already been added
			if (!directedTo.get(j).containsKey(from)) {
				
				directedTo.get(j).put(to, weight);
				setEdgeCount(getEdgeCount() + 1);
			}
		}
	}
	
	

	@Override
	public void deleteNode(Graphnode node) {
		// TODO Auto-generated method stub
		if (nodes.contains(node)) {
			for (int i = 0; i < directedTo.size(); i++) {
				directedTo.get(i).remove(node);
				if (directedTo.get(i).containsKey(node)) {
					directedTo.get(i).remove(node);
					setEdgeCount(getEdgeCount() - 1);
				}

			}
			nodes.remove(node);
			setNodeCount(getNodeCount() - 1);
		}
	}

	@Override
	public void deleteEdge(Graphnode from, Graphnode to) {
		// TODO Auto-generated method stub
		if (nodes.contains(from) && nodes.contains(to)) {
			int j = nodes.indexOf(from);

			if (directedTo.get(j).containsKey(to)) {
				directedTo.get(j).remove(to);
				setEdgeCount(getEdgeCount() - 1);
			}
		}
	}

	@Override
	public boolean isConnected(Graphnode from, Graphnode to) {
		// TODO Auto-generated method stub
		boolean exists = false;
		if (nodes.contains(from) && nodes.contains(to)) {
			int j = nodes.indexOf(from);
			if (directedTo.get(j).containsKey(to)) {
				exists = true;

			}

		}
		return exists;
	}

	@Override
	public List<Graphnode> getNodes() {
		// TODO Auto-generated method stub
		return nodes;
	}

	public void setEdgeCount(int newEdgeCount) {
		// TODO Auto-generated method stub
		edgeCount = newEdgeCount;
	}

	public int getEdgeCount() {
		// TODO Auto-generated method stub
		return edgeCount;
	}

	@Override
	public int numNodes() {
		// TODO Auto-generated method stub
		return getNodeCount();
	}

	@Override
	public int numEdges() {
		// TODO Auto-generated method stub
		return edgeCount;
	}

	public int getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(int newNodeCount) {
		nodeCount = newNodeCount;
	}

	@Override
	public ArrayList<Graphnode> bfs(Graphnode initial, Graphnode goal) {
				
		LinkedList<Graphnode> toVisit = new LinkedList<Graphnode>();
		ArrayList<Graphnode> visited = new ArrayList<Graphnode>();
		ArrayList<Graphnode> connected = new ArrayList<Graphnode>();
		
		Graphnode current = initial;
		initial.setParentNode(null);
		
		toVisit.add(current);
		
		while (!toVisit.isEmpty()) {

			current = toVisit.poll();
			visited.add(current);

			if (current.equals(goal)) {
				ArrayList<Graphnode> path = new ArrayList<Graphnode>();
				path = constructPath(current);
				path.add(0, initial);
				return path;
			} else {
				connected = getNeighbors(current);
				for (Graphnode c : connected) {
					if (!visited.contains(c)) {
						toVisit.add(c);
						c.setParentNode(current);
					}
				}
			}

		}
		return null;
	}

	 public ArrayList<Graphnode> constructPath(Graphnode node) {
		  ArrayList<Graphnode> path = new ArrayList<Graphnode>();
		  while (node.getParentNode() != null) {
		    path.add(0, node);
		    node = node.getParentNode();
		  }
		  return path;
		}
	
	@Override
	public ArrayList<Graphnode> getNeighbors(Graphnode node) {
		//System.out.println("in here");
		ArrayList<Graphnode> neighbours = new ArrayList<Graphnode>();	
		
		//get the hashmap thats located at the index in the directedTo array, of the node we are checking neigbours for
		HashMap<Graphnode,Integer> h = directedTo.get(nodes.indexOf(node));
		
		//get a set of all the neighours (which are the keys to the hashmap)
		Set<Graphnode> keyset = h.keySet();
		
		//adding every element of keyset to an array
		for(Graphnode n: keyset){
			//System.out.println(n.getName());
			neighbours.add(n);
		}
//		for(Graphnode n: neighbours){
//			//System.out.println(n.getName());
//		}
		//System.out.println(neighbours.size());
		return neighbours;
		
		
	}

	public void addNonDEdge(Graphnode a, Graphnode b, int weight) {
		addEdge(a, b,weight);
		addEdge(b, a,weight);
	}

	@Override
	public void addEdge(Graphnode from, Graphnode to) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNonDEdge(Graphnode a, Graphnode b) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
