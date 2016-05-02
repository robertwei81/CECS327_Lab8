import java.util.*;
public class Node {
	ArrayList<Character> mData= new ArrayList<Character>();
	Node(){}
	private void initNode(){
		Random rand = new Random();
		String letterCAPs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i=0;i<500;i++){
			mData.add(letterCAPs.charAt(rand.nextInt(letterCAPs.length())));
		}
	}
	public void shuffleNode(){
		Collections.shuffle(mData);//comment to see if github is working
	}	
}
/*
NODES
Each node stores an array of 500 characters (for simplicity). 
When a worker thread updates a node, it randomly shuffles the 500 characters. 
  (This is a simple task to give a worker thread something to do.)
*/