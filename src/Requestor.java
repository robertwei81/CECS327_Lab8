import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Requestor {
	int port;
	String message;
	public Requestor(int portNum, String msg){
		port = portNum;
		message = msg;
	}
	public void sendMessage(){
		try{
			ServerSocket connectionSocket = new ServerSocket(this.port);
			System.out.println("now ready to accept a connection");
			Socket dataSocket = connectionSocket.accept();
			System.out.println("connection accepted");
			OutputStream outStream = dataSocket.getOutputStream();
			PrintWriter socketOutput = new PrintWriter(new OutputStreamWriter(outStream));
			socketOutput.println(this.message);
			socketOutput.flush();
			System.out.println("message sent");
			dataSocket.close();
			System.out.println("data socket closed");
			connectionSocket.close();
			System.out.println("connection socket closed");
		} catch(Exception e){
			
		}
	}
	
}
