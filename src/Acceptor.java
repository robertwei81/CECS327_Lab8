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
    Token CurrentToken;
    public ArrayList<InetAddress>mSystemList= new ArrayList<InetAddress>();

    public Acceptor(){OpenReceiveSocket();}					//default, dont open socket till run time
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
		
	}
        
	/*public static void main(String[] args){
		//Acceptor.readMessage(1234, "192.168.0.8");
	}*/
}
