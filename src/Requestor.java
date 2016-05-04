import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Requestor{
	Socket dataSocket;
	ObjectOutputStream dataOut;
	ServerSocket connectionSocket;
	public Requestor(int portNum){
		try {
			connectionSocket = new ServerSocket(portNum);
			dataSocket = connectionSocket.accept();
			dataOut = new ObjectOutputStream(dataSocket.getOutputStream());
		} catch (IOException e) {}
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
	public void getToken(Token football) {
		// TODO Auto-generated method stub
		// send msg to other system to see if token is taken, 
		//  if taken by either system, return false and waits
		//  if not taken, set the token on their system as taken and proceed
	}
	public static void main(String[] args){
		//Requestor.sendData(5000, new Token());
	}
}
