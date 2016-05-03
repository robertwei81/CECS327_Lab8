import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Lab8Tester {
	public static void main(String a[]){
		InetAddress ipAddress = null;
		WorkerThread [] mThreads = null;
		int ThreadCount = 100;
		int NodeCount = 150;
		int CharactorCount = 500;
		Node[]Nodes = new Node[150];
		ArrayList<Token> Tokens = new ArrayList<Token>();
		Requestor mRequestAgent = new Requestor();

		try {ipAddress = InetAddress.getLocalHost();} 
		catch (UnknownHostException e) {e.printStackTrace();}
		
		//Initialize Nodes
		Token football; 
		for (int count=0;count<NodeCount;count++){
			Nodes[count] = new Node();				//create node
			Nodes[count].initNode(CharactorCount);	//initialize nodes with 500 chars
			football = new Token(count, ipAddress, ThreadID.get()); //create token for update
			try {
				while (mRequestAgent.getToken(football)==false)
					await();
			}
			finally{
			
			}
		}
		
		//Initialize Threads
		for (int thread=0; thread<ThreadCount;thread++){
			mThreads[thread] = new WorkerThread(Nodes);
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
