import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Acceptor extends Thread{
	Socket dataSocket;
	ObjectOutputStream dataOut;
	ServerSocket connectionSocket;
	public Acceptor(){}
	public Acceptor(int portNum){
		try {
			this.connectionSocket = new ServerSocket(portNum);
			this.dataSocket = connectionSocket.accept();
			this.dataOut = new ObjectOutputStream(dataSocket.getOutputStream());
		} catch (IOException e) {}
	}
	public Acceptor(int portNum,Token footballUpdate){
		try {
			connectionSocket = new ServerSocket(portNum);
			dataSocket = connectionSocket.accept();
			dataOut = new ObjectOutputStream(dataSocket.getOutputStream());
		} catch (IOException e) {}
		//footballUpdate.mSystemIP;	//from which host
		//footballUpdate.mThreadID;	//on that host which thread
		//footballUpdate.mIndex;	//updating which node
		//footballUpdate.mTokenState;	//what are we doing? 
		//0-request token, 1-permission received okay, 2-request update
	}
	public void sendToken(Token outToken){
		try {
			this.dataOut.writeObject(outToken);
			this.dataOut.flush();
		} catch (IOException e) {}
	}
	public void sendNode(Node outNode){
		try {
			this.dataOut.writeObject(outNode);
			this.dataOut.flush();
		} catch (IOException e) {}
	}
	public void closeSocket(){
		try{
			this.dataSocket.close();
			this.connectionSocket.close();
		} catch(IOException e){}
	}
}
