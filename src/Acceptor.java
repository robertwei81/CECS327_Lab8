import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Acceptor {
	public static void main(String[] args){
		try{
			InetAddress acceptorHost = InetAddress.getByName(args[0]);
			int acceptorPort = Integer.parseInt(args[1]);
			Socket mySocket = new Socket(acceptorHost, acceptorPort);
			System.out.println("Connection request granted");
			InputStream inStream = mySocket.getInputStream();
			BufferedReader socketInput = new BufferedReader(new InputStreamReader(inStream));
			System.out.println("waiting to read");
			String message = socketInput.readLine();
			System.out.println("Message received:");
			System.out.println("\t" + message);
			mySocket.close();
			System.out.println("data socket closed");
		}catch (Exception e){
			
		}
	}
}
