import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Acceptor {
	public Acceptor(){
	}
	public static void readMessage(int portNumber, String host){
		try{
			InetAddress hostName = InetAddress.getByName(host);
			int acceptorPort = portNumber;
			Socket mySocket = new Socket(hostName, acceptorPort);
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
	public static void main(String[] args){
		Acceptor.readMessage(1234, "192.168.0.8");
	}
}
