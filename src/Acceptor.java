import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

public class Acceptor {
	InetAddress host;
	int port;
	public Acceptor(InetAddress hostName, int portNum){
		host = hostName;
		port = portNum;
	}
	
	public void read(){
		try{
			int acceptorPort = this.port;
			Socket mySocket = new Socket(this.host, acceptorPort);
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
