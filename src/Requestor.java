import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class Requestor extends Thread{
	Socket mySocket;
	ObjectInputStream dataIn;
	InetAddress hostName;
	public Requestor(int portNumber, String host){
		try{
			hostName = InetAddress.getByName(host);
			mySocket = new Socket(hostName, portNumber);
			dataIn = new ObjectInputStream(mySocket.getInputStream());
		}catch(Exception e){
			
		}
	}
	public Node readNode() throws ClassNotFoundException, IOException{	
		Node inObject = (Node) this.dataIn.readObject();
		return inObject;
	}
	public Token readToken() throws ClassNotFoundException, IOException{	
		Token inObject = (Token) this.dataIn.readObject();
		return inObject;
	}
	public void closeSocket() throws IOException{
		this.mySocket.close();
	}
	
}
