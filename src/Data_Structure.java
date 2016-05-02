
public class Data_Structure {
	Node[] mNodes;
	Token[] mTokens;
	Data_Structure(){
		 mNodes = new Node[150];
		 mTokens = new Token[150];		// all start at zero
	}
	void UpdateNode(int index, Node newOne){
		//TODO check token to match with current thread
		//TODO set token
		//TODO update node
		//TODO trigger update on other system, wait till done
		//TODO reset token value
		mNodes[index] = newOne;
	}
}
/*
DATA STRUCTURE
The assignment is to maintain a fixed size set of 150 nodes, which are replicated (duplicated) on 3 computers.
The distributed set is to be coherent at real time, i.e., each worker thread operates on an up-to-date node. 
That is, the software must continuously maintain the set & its nodes across the network.
*/