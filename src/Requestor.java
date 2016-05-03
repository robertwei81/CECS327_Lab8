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
	}
	
	public static void main(String[] args){
		//Requestor.sendData(5000, new Token());
	}
}
