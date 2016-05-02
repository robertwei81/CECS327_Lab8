import java.net.*;
public class Token {
	int mIndex;
	InetAddress mSystemIP;
	int ThreadID;
	Token(int NodeNumber, InetAddress ip, int threadID){
		mIndex = NodeNumber;
		mSystemIP = ip;
		ThreadID = threadID;
	}
}
