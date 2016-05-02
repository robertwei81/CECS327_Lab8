import java.io.*;
import java.net.*;
public class Token {
	int mIndex;
	InetAddress mSystemIP;
	Token(int NodeNumber, InetAddress ip){
		mIndex = NodeNumber;
		mSystemIP = ip;
	}
}
