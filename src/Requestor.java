import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Requestor{
	public Requestor(){}
	public static void sendData(int portNum, Token outToken){
		try{
			ServerSocket connectionSocket = new ServerSocket(portNum);
			Socket dataSocket = connectionSocket.accept();
			ObjectOutputStream dataOut = new ObjectOutputStream(dataSocket.getOutputStream());
			dataOut.writeObject(outToken);
			dataOut.flush();
			dataSocket.close();
			connectionSocket.close();
		} catch(Exception e){
		}
	}
<<<<<<< HEAD
	public void getToken(Token football) {
		// TODO Auto-generated method stub
		// send msg to other system to see if token is taken, 
		//  if taken by either system, return false and waits
		//  if not taken, set the token on their system as taken and proceed
=======
	public static void sendData(int portNum, Node outNode){
		try{
			ServerSocket connectionSocket = new ServerSocket(portNum);
			Socket dataSocket = connectionSocket.accept();
			ObjectOutputStream dataOut = new ObjectOutputStream(dataSocket.getOutputStream());
			dataOut.writeObject(outNode);
			dataOut.flush();
			dataSocket.close();
			connectionSocket.close();
		} catch(Exception e){
		}
>>>>>>> origin/master
	}
	
	public static void main(String[] args){
		//Requestor.sendData(5000, new Token());
	}
}
