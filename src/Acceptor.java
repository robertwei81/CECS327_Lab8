import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Acceptor extends Thread{
<<<<<<< HEAD
	ServerSocket OpenInSocket;
	Socket DataSocket;
	ObjectInputStream dataIn;
	int ReceivingPort = 1024;
	int defaultPort = 1024;
	InetAddress hostName;
    Node[] nodes;
    Token CurrentToken;
    public ArrayList<InetAddress>mSystemList= new ArrayList<InetAddress>();

    public Acceptor(){}					//default, dont open socket till run time
	public Acceptor(Node[] nodeList){ 	// create with node list, for update calls
        nodes = nodeList;
	}
	public void OpenReceiveSocket(){
		try {
			OpenInSocket = new ServerSocket(defaultPort);
			DataSocket = OpenInSocket.accept();
			dataIn = (ObjectInputStream) DataSocket.getInputStream();
			try {
				CurrentToken = (Token) dataIn.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {}
		
	}
	public Node readNode(int index) throws ClassNotFoundException, IOException{	
		Node inObject = (Node) dataIn.readObject();
                nodes[index] = inObject;
		return inObject;
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
	}
	public void SetSystemList(ArrayList<InetAddress>System){
		mSystemList=System;
	}
	public void OpenReceiveSocket(InetAddress primaryHostIP) {
		// TODO Auto-generated method stub
		
=======
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
>>>>>>> origin/master
	}
        
	/*public static void main(String[] args){
		//Acceptor.readMessage(1234, "192.168.0.8");
	}*/
}
