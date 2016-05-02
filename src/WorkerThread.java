import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class WorkerThread extends Thread{
	private Random mRand;
	private int mNumOfNodes = 150;
	private int mRepetition = 200;
	Node[]mDataPoint;
	private Lock mLock;
	WorkerThread(){}
	WorkerThread(Node[]orgData){	// must have a way to point back to data, and tokens
		mRand = new Random();
		mDataPoint = orgData;
		mLock = new ReentrantLock();
	}
	public void run(){
		for (int run = 0; run < mRepetition; run++){
			int index = mRand.nextInt(mNumOfNodes);   //randomly choose a node to update
			GrabToken(index);
			Update(index);
			ResetToken(index);
			try {Thread.sleep(10);}//10 milliseconds sleep 
			catch(InterruptedException ex){Thread.currentThread().interrupt();}
		}	
	}
	private void GrabToken(int index){
		//TODO attempt to grab token
		//   loop&&wait till token is received
	}
	private void Update(int index){
		mLock.lock();
		try{
			mDataPoint[index].shuffleNode();
			Synch(index);
		}	
		//catch(){}
		finally{mLock.unlock();}
	}
	private void Synch(int index){
		//TODO attempt to tell other systems they need to update...
	}
	private void ResetToken(int index){
	}
	
}
