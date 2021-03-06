import java.util.*;
import java.util.concurrent.locks.*;
public class Node {
	public ArrayList<Character> mData= new ArrayList<Character>();
	public Lock mLock = new ReentrantLock();
	Node(){}						// default constructor
	Node(int size){initNode(size);}	// trigger constructor with specific size
	public void initNode(int size){
		Random rand = new Random();
		String letterCAPs = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i=0;i<size;i++){
			mData.add(letterCAPs.charAt(rand.nextInt(letterCAPs.length())));
		}
	}
	public void ShuffleNode(){
		mLock.lock();
		try{Collections.shuffle(mData);}
		finally{mLock.unlock();}
	}
}
