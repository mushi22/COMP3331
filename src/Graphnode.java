
public class Graphnode {

	private Graphnode parentNode;
	private String name;
	
	public Graphnode(String name){
		this.parentNode = null;
		this.name = name;

	}
	
	
	
	public Graphnode getParentNode(){
		return parentNode;
	}	
	
	public void setParentNode(Graphnode pN){
		this.parentNode = pN;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
}