import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

public class Cliente {
	//Dos hilos:
	//ServerR está pendiente de lo que le llega
	//ServerS está pendiente de lo que mandes
	
	private int numPuerto=4532;
	private static String dirDestino = "127.0.0.1";
	private ServerReceiver sr;
	private ServerSender ss;
	private static String nickname;
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	
	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		dirDestino = args[0];
		nickname = args[1];
		Cliente cliente = new Cliente(nickname);
		cliente.run();
		
	}
	
	public Cliente(String nickname) throws IOException{
		this.nickname = nickname;
		this.socket = new Socket(dirDestino, numPuerto);
		this.out = new DataOutputStream(socket.getOutputStream());
		this.in = new DataInputStream(socket.getInputStream());
		this.sr = new ServerReceiver(in);
		this.ss = new ServerSender(out);
		sr.start();
		out.writeUTF(nickname);
		
	}
	
	public void run() throws UnknownHostException, IOException, InterruptedException{
		ss.start();
		ss.join();
		sr.parar();
		shutdown();
		
	}
	
	public void shutdown() throws IOException{
		//Cerrar todos los stream de datos
		//throws exception
		out.close();
		in.close();
		socket.close();
	}
	
	
}
