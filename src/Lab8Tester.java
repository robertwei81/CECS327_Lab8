import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Lab8Tester {  
	//Purpose: 	construct the necessary data constructs
	//			create the thread to do the work
	//			activate the threads
	public static void main(String a[]){
		InetAddress ipAddress = null;		// current host ip value
		InetAddress primaryHostIP = null; 	// need to change based on our setup
		int portNumber = 1234; 				// need to change based on our setup
		WorkerThread [] mThreads = null;	// worker thread array
		int ThreadCount = 100;				// req: num of threads
		int NodeCount = 150;				// req: num of nodes
		int CharactorCount = 500;			// req: num of char per node
		Node[]Nodes = new Node[150];		// node array
		ArrayList<Token> Tokens = new ArrayList<Token>(); 	//arraylist of token? may need?
		ArrayList<InetAddress> mSystemList = new ArrayList<InetAddress>();
		mSystemList.add(primaryHostIP);
		mSystemList.add(ipAddress);
		mSystemList.addAll(//any others that we need to add goes to this list;)
		
		try {ipAddress = InetAddress.getLocalHost();} 
		catch (UnknownHostException e) {e.printStackTrace();}
		
		//Initialize Nodes
		if (primaryHostIP == ipAddress){
			Requestor mRequestAgent = new Requestor();  //may need to request for update for other systems
			Token football = new Token(); //create token for update
			for (int count=0;count<NodeCount;count++){
				Nodes[count] = new Node();				//create node
				Nodes[count].initNode(CharactorCount);	//initialize nodes with 500 chars
				football.mIndex=count;					//set token node id to current index
				football.mSystemIP=ipAddress;			//set token system ip to this host
				football.mThreadID=ThreadID.get();		//set token thread id to this thread
				football.mTokenState=1;					//set token for update
				mRequestAgent.sendToken(football);		//pass ball to requester
			}	
		}else{
			//for systems other than the primary system that creates the nodes
			for(int count=0;count<NodeCount;count++){
				Acceptor accept = new Acceptor(, primaryHostIP);				
			}	
		}
		//Initialize Threads
		for (int thread=0; thread<ThreadCount;thread++){
			mThreads[thread] = new WorkerThread(Nodes,Tokens);
		}
		//Start Threads
		for (int thread=0; thread<ThreadCount;thread++){
			mThreads[thread].start();
		}
	}
}
/*
DATA STRUCTURE
The assignment is to maintain a fixed size set of 150 nodes, which are replicated (duplicated) on 3 computers.
The distributed set is to be coherent at real time, i.e., each worker thread operates on an up-to-date node. 
That is, the software must continuously maintain the set & its nodes across the network.

WORKER THREADS
Each computer, that hosts a replica of the set, also hosts 100 worker threads. 
These threads concurrently (or in parallel) operate (only update operation is supported) on the nodes of the set. 
Each thread chooses a random node each time to update (generate a random number). 
Also, each thread repeats this 200 times with 10 msec of sleep between each update.

NODE UPDATE
Of course, each node operation requires mutual exclusion. 
Therefore, students need to determine how to use lock(s) to do this. 
Moreover, mutex must be enforced across the network (of 3 machines). 
That is, while a worker thread T on machine A is updating node W, no other worker thread on any computer can update node W. 
Furthermore, the next worker thread to update node W must use the values modified by thread A. 
However, while worker thread T on computer A is updating node W,
   one or more worker threads S on any of the 3 machines can update other nodes V.

NODES
Each node stores an array of 500 characters (for simplicity). 
When a worker thread updates a node, it randomly shuffles the 500 characters. 
  (This is a simple task to give a worker thread something to do.)
*/

//TODO check token to match with current thread
//TODO set token
//TODO update node
//TODO trigger update on other system, wait till done
//TODO reset token value
