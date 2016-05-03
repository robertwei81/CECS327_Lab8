import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Requestor {
	public Requestor(){
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
	public static void main(String[] args){
		Requestor.sendMessage(1234, "This message is a test.");
	}
}
