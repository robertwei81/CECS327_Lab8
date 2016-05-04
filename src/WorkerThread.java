import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.locks.*;
public class WorkerThread extends Thread{
	private Random mRand;
	private int mNumOfNodes = 150;
	private int mRepetition = 200;
	Node[]mDataPoint;
	//ArrayList<Token>mTokens;
	private Lock mLock;
	Requestor mRequestAgent = new Requestor();
	WorkerThread(){}
	WorkerThread(Node[]orgData,ArrayList<Token> tokens){	// must have a way to point back to data, and tokens
		mRand = new Random();
		mDataPoint = orgData;
		//mTokens = tokens;
		mLock = new ReentrantLock();
	}
	public void run(){
		for (int run = 0; run < mRepetition; run++){
			int index = mRand.nextInt(mNumOfNodes);   //randomly choose a node to update
			GrabToken(index);
			Update(index);
			try {Thread.sleep(10);}//10 milliseconds sleep 
			catch(InterruptedException ex){Thread.currentThread().interrupt();}
		}	
	}
	private void GrabToken(int index){
	}
	private void Update(int index){
		mLock.lock();
		try{
			mDataPoint[index].ShuffleNode();
			Synch(index);
		}	
		//catch(){}
		finally{mLock.unlock();}
	}
	private void Synch(int index){
		//TODO attempt to tell other systems they need to update...
	}
}
