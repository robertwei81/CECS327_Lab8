import java.util.*;
public class Node {
	ArrayList<Character> Data= new ArrayList<Character>();
	Node(){initNode();}
	private void initNode(){
		Random rand = new Random();
		String letterCAPs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i=0;i<500;i++){
			Data.add(letterCAPs.charAt(rand.nextInt(letterCAPs.length())));
		}
	}
	public void shuffleNode(){
		Collections.shuffle(Data);
	}	
}
/*
NODES
Each node stores an array of 500 characters (for simplicity). 
When a worker thread updates a node, it randomly shuffles the 500 characters. 
  (This is a simple task to give a worker thread something to do.)
*/