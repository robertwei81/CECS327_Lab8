import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Requestor {
<<<<<<< HEAD
	int port;
	String message;
	public Requestor(){}
	public Requestor(int portNum, String msg){
		port = portNum;
		message = msg;
=======
	public Requestor(){
>>>>>>> origin/master
	}
	public static void sendMessage(int portNum, String msg){
		try{
			ServerSocket connectionSocket = new ServerSocket(portNum);
			System.out.println("now ready to accept a connection");
			Socket dataSocket = connectionSocket.accept();
			System.out.println("connection accepted");
			OutputStream outStream = dataSocket.getOutputStream();
			PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(outStream));
			socketOutput.println(msg);
			socketOutput.flush();
			System.out.println("message sent");
			dataSocket.close();
			System.out.println("data socket closed");
			connectionSocket.close();
			System.out.println("connection socket closed");
		} catch(Exception e){
			
		}
	}
<<<<<<< HEAD
	public boolean getToken(Token football) {
		// TODO Auto-generated method stub
		// send msg to other system to see if token is taken, 
		//  if taken by either system, return false and waits
		//  if not taken, set the token on their system as taken and proceed
		return true;//this need to be visited
	}
	
=======
	public static void main(String[] args){
		Requestor.sendMessage(1234, "This message is a test.");
	}
>>>>>>> origin/master
}
