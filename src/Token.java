import java.net.*;
public class Token {
	int mIndex;
	InetAddress mSystemIP;
	int mThreadID;
	int mTokenState; 
	//0=requesting state
	//1=received by other systems(and okay for token)
	//2=update notice to other system
	Token(){}
	Token(int NodeNumber, InetAddress ip, int threadID){
		mIndex = NodeNumber;
		mSystemIP = ip;
		mThreadID = threadID;
		mTokenState = 0;
	}
}
