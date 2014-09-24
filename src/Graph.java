import java.util.ArrayList;
import java.util.List;


public interface Graph <E> {
	public void addNode(E node);
	public void addEdge(E from, E to);
	public void deleteNode(E node);
	public void deleteEdge(E from, E to);
	public boolean isConnected(E from, E to);
	public int numNodes();
	public List<E> getNodes();
	public int numEdges();
	public List<E> getNeighbors(E node);
	public ArrayList<E> bfs(E initial, E goal);
	public void addNonDEdge(E a, E b);
	
	
	
	
}
