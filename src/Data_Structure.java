
public class Data_Structure {
	Node[] Nodes;
	float [] Tokens;
	Data_Structure(){
		 Nodes = new Node[150];
		 Tokens = new float [150];		// all start at zero
	}
	void UpdateNode(){
		//TODO check token to match with current thread
		//TODO set token
		//TODO update node
		//TODO trigger update on other system, wait till done
		//TODO reset token value
	}
}
/*
DATA STRUCTURE
The assignment is to maintain a fixed size set of 150 nodes, which are replicated (duplicated) on 3 computers.
The distributed set is to be coherent at real time, i.e., each worker thread operates on an up-to-date node. 
That is, the software must continuously maintain the set & its nodes across the network.
*/