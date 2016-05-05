import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Lab8Tester {  
	//Purpose: 	construct the necessary data constructs
	//			create the thread to do the work
	//			activate the threads
	public static void main(String a[]){
		InetAddress ipAddress = null;		// current host ip value
		try {ipAddress = InetAddress.getLocalHost();} 
		catch (UnknownHostException e) {e.printStackTrace();}
		
		InetAddress primaryHostIP = null;
		try {primaryHostIP = InetAddress.getByName("172.20.10.3");}
		catch (UnknownHostException e1) {e1.printStackTrace();} 	// need to change based on our setup
		
		WorkerThread [] mThreads = new WorkerThread[100];	// worker thread array
		int ThreadCount = 1;				// req: num of threads
		int NodeCount = 150;				// req: num of nodes
		int CharactorCount = 500;			// req: num of char per node
		Node[]Nodes = new Node[NodeCount];		// node array
		ArrayList<InetAddress> mSystemList = new ArrayList<InetAddress>();
		mSystemList.add(ipAddress);
		mSystemList.add(primaryHostIP);//add additional system list as needed
		Requestor mRequestAgent = new Requestor();  //may need to request for update for other systems
		Acceptor mAcceptorAgent = new Acceptor();


		
		//Initialize Nodes
		if (primaryHostIP == ipAddress){
			Token football = new Token(); //create token for update
			for (int count=0;count<NodeCount;count++){
				Nodes[count] = new Node();				//create node
				Nodes[count].initNode(CharactorCount);	//initialize nodes with 500 chars
				football.mIndex=count;					//set token node id to current index
				football.mSystemIP=ipAddress;			//set token system ip to this host
				football.mThreadID=ThreadID.get();		//set token thread id to this thread
				football.mTokenState=1;					//set token for update
				for (int list=0;list<mSystemList.size();list++){ //loop the system list
					if (mSystemList.get(list) != ipAddress){
						mRequestAgent.Update(football,mSystemList.get(list));	//pass ball to requester,and who to send to
						mRequestAgent.start();
					}
				}
			}	
		}else{
			//for systems other than the primary system that creates the nodes
			for(int count=0;count<NodeCount;count++){
				mAcceptorAgent = new Acceptor();
				mAcceptorAgent.start();
			}	
		}
		//Initialize Threads
		for (int thread=0; thread<ThreadCount;thread++){
			mThreads[thread] = new WorkerThread(Nodes,mSystemList);
		}
		//Start Threads
		for (int thread=0; thread<ThreadCount;thread++){
			mThreads[thread].start();
		}
	}
}
