import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.locks.*;
public class WorkerThread extends Thread{
	private Random mRand;
	private int mNumOfNodes = 150;
	private int mRepetition = 200;
	private Node[]mDataPoint;
	private Lock mLock;
	private Requestor mRequestAgent;
	private Acceptor mAcceptAgent;
	
	public WorkerThread(){}	// must have a way to point back to data, and tokens
	public WorkerThread(Node[] nodes) {
		mRand = new Random();
		mDataPoint = nodes;
		mLock = new ReentrantLock();
	}
	public void run(){
		for (int run = 0; run < mRepetition; run++){
			int index = mRand.nextInt(mNumOfNodes);   //randomly choose a node to update
			GrabToken(index);
			while (mAcceptAgent.CurrentToken.mTokenState!=1) //busy wait
			{	//while loop checks if agent caught an token for update, aka state 1
				GrabToken(index);
			}
			Update(index); 			//update node on local system
			try {Thread.sleep(10);}//10 milliseconds sleep 
			catch(InterruptedException ex){Thread.currentThread().interrupt();}
		}	
	}
	private void GrabToken(int index){
		//Creation of a token and sent
		Token Asking = new Token();
		Asking.mIndex=index;
		Asking.mNode=null;
		try {Asking.mSystemIP = InetAddress.getLocalHost();} 
		catch (UnknownHostException e) {e.printStackTrace();}
		Asking.mThreadID=ThreadID.get();
		Asking.mTokenState=0;
		mRequestAgent = new Requestor();
		mRequestAgent.mFootball=Asking; // renew agent, and update its token
		mRequestAgent.start();			// renew agent
		mAcceptAgent = new Acceptor();
		mAcceptAgent.start();			// restart agent to catch a token(with permission set to 1)
	}
	private void Update(int index){
		mLock.lock();
		try{
			mDataPoint[index].ShuffleNode();
			mRequestAgent = new Requestor();
			mRequestAgent.mFootball=mAcceptAgent.CurrentToken;  //grab latest token from agent
			mRequestAgent.mFootball.mNode=mDataPoint[index];	//update node for token
			mRequestAgent.start(); 	//set it and forget ??? or do we get update confirmation
									// 1. Possibly no; because if an acceptor catches it with a state 2, it will update locally
									// 2. possibly yes; because other threads of that system may catch that token+node
			//if 2, then another acceptor will have to be started 
			//and with a while loop till the system catches the update confirmation
		}	
		finally{mLock.unlock();}
	}
}
