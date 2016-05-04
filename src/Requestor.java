import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
<<<<<<< HEAD
import java.net.InetAddress;
import java.net.Socket;

public class Requestor extends Thread{
	Socket dataSocket;
	ObjectOutputStream dataOut;
	int UpdatePort = 1024;
	int RequestTokenPort = 1024;
	int defaultPort = 1024;
	Token mFootball;
	public Requestor() {}
	public Requestor(InetAddress toHost, int portNum){
		OpenSendSocket(toHost, 1024);
	}
	public void OpenSendSocket(InetAddress toHost, int portNum){
		try{
			dataSocket = new Socket(toHost, defaultPort);
			dataOut = new ObjectOutputStream(dataSocket.getOutputStream());
		}catch(Exception e){
			
		}
	}
	public Requestor(InetAddress toHost, Token footballUpdate){
		mFootball = footballUpdate;
		OpenSendSocket(toHost,defaultPort);
	}
	public Requestor(Token PermissionApproved) {
		//return token to host that requested it
		OpenSendSocket(PermissionApproved.mSystemIP,defaultPort);
	}
	public void closeSocket(){
		try{
			this.dataSocket.close();
		} catch(IOException e){}
	}
	public void Update(Token football, InetAddress toHost) {
		//update football, and send it out for updating
		mFootball = football;
		mFootball.mTokenState=2;
		OpenSendSocket(toHost,defaultPort);
	}

	public void run(){
		try{
			this.dataOut.writeObject(mFootball);
			this.dataOut.flush();
		} catch(IOException e){}
=======
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
>>>>>>> origin/master
	}
	
}
