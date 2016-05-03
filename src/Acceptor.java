import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Acceptor{
	public Acceptor(){}
	
	public static void readMessage(int portNumber, String host, int x){
		try{
			InetAddress hostName = InetAddress.getByName(host);
			Socket mySocket = new Socket(hostName, portNumber);
			ObjectInputStream dataIn = new ObjectInputStream(mySocket.getInputStream());
			if(x == 0){
				Node inObject = (Node) dataIn.readObject();
			}else{
				Token inObject = (Token) dataIn.readObject();
				System.out.println(inObject.mIndex);
			}
			mySocket.close();
		}catch (Exception e){
			
		}
	}
	public static void main(String[] args){
		//Acceptor.readMessage(1234, "192.168.0.8");
	}
}
