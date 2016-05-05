import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Acceptor extends Thread{
	ServerSocket OpenInSocket;
	Socket DataSocket;
	ObjectInputStream dataIn;
	int ReceivingPort = 1024;
	int defaultPort = 1024;
	InetAddress hostName;
    Node[] nodes;
    Token mFootball;
    public ArrayList<InetAddress>mSystemList= new ArrayList<InetAddress>();

    public Acceptor(){
    	mFootball = new Token();
    	mFootball.mTokenState=-1;
    }					//default, dont open socket till run time
	public void OpenReceiveSocket(){
		try {
			OpenInSocket = new ServerSocket(defaultPort);
			DataSocket = OpenInSocket.accept();
			dataIn = (ObjectInputStream) DataSocket.getInputStream();
			try {mFootball = (Token) dataIn.readObject();} 
			catch (ClassNotFoundException e) {e.printStackTrace();			}
		} catch (IOException e) {}
	}
	public void readToken() throws ClassNotFoundException, IOException{	
		Token inObject = (Token) dataIn.readObject();
                if (inObject.mTokenState == 0){
                    if (nodes[inObject.mIndex].mLock.tryLock()){
                        inObject.mTokenState = 1;
                        Requestor sendBackAgent = new Requestor(inObject);
                        sendBackAgent.start();
                    }else if (inObject.mTokenState == 1){
                        return;
                    }
                    else if( inObject.mTokenState == 2){
                        nodes[inObject.mIndex] = inObject.mNode;
                    }
                }
		return;
	}
	public void closeSocket() throws IOException{
		DataSocket.close();
		OpenInSocket.close();
	}
	public void SetSystemList(ArrayList<InetAddress>System){
		mSystemList=System;
	}
	public void run(){
		OpenReceiveSocket();
	
		//try {closeSocket();} catch (IOException e) {e.printStackTrace();}
	}
}
