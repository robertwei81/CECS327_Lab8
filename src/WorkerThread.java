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
	public ArrayList<InetAddress>mSystemList;
	
	public WorkerThread(){}	// must have a way to point back to data, and tokens
	public WorkerThread(Node[] nodes, ArrayList<InetAddress> systems) {
		mRand = new Random();
		mDataPoint = nodes;
		mLock = new ReentrantLock();
		mSystemList = systems;
	}
	public void run(){
		for (int run = 0; run < mRepetition; run++){
			int index = mRand.nextInt(mNumOfNodes);   //randomly choose a node to update
			GrabToken(index);
			mAcceptAgent=new Acceptor();
			mAcceptAgent.start();
			while (mAcceptAgent.mFootball.mTokenState!=1) //busy wait
			{	//while loop checks if agent caught an token for update, aka state 1
				while (mAcceptAgent.mFootball.mTokenState==-1)
					try {sleep(15);} catch (InterruptedException e) {e.printStackTrace();}
				GrabToken(index);
				mAcceptAgent=new Acceptor();
				mAcceptAgent.start();
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
		for (int count=0; count < mSystemList.size();count++){
			mRequestAgent = new Requestor(mSystemList.get(count),Asking);
		}
		mRequestAgent.start();			// start agent
	}
	private void Update(int index){
		mLock.lock();
		try{
			mDataPoint[index].ShuffleNode();
			mRequestAgent = new Requestor();
			mRequestAgent.mFootball=mAcceptAgent.mFootball;  //grab latest token from agent
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
