import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Requestor extends Thread{
	Socket dataSocket;
	ObjectOutputStream dataOut;
	int UpdatePort = 1024;
	int RequestTokenPort = 1024;
	int defaultPort = 1024;
	Token mFootball;
	public Requestor() {}
	public Requestor(InetAddress toHost, int portNum){
		OpenSendSocket(toHost, 1024);
	}
	public void OpenSendSocket(InetAddress toHost, int portNum){
		try{
			dataSocket = new Socket(toHost, defaultPort);
			dataOut = new ObjectOutputStream(dataSocket.getOutputStream());
		}catch(Exception e){
			
		}
	}
	public Requestor(InetAddress toHost, Token footballUpdate){
		mFootball = footballUpdate;
		OpenSendSocket(toHost,defaultPort);
	}
	public Requestor(Token PermissionApproved) {
		//return token to host that requested it
		OpenSendSocket(PermissionApproved.mSystemIP,defaultPort);
	}
	public void closeSocket(){
		try{
			dataSocket.close();
			dataOut.close();
		} catch(IOException e){}
	}
	public void Update(Token football, InetAddress toHost) {
		//update football, and send it out for updating
		mFootball = football;
		mFootball.mTokenState=2;
		OpenSendSocket(toHost,defaultPort);
	}

	public void run(){
		try{
			dataOut.writeObject(mFootball);
			dataOut.flush();
		} catch(IOException e){}
		closeSocket();
	}
}
