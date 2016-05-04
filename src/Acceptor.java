import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Acceptor {
	Socket mySocket;
	ObjectInputStream dataIn;
	InetAddress hostName;
	public Acceptor(int portNumber, String host){
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
	public static void main(String[] args){
		//Acceptor.readMessage(1234, "192.168.0.8");
	}
}
