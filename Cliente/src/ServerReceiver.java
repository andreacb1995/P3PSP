import java.io.DataInputStream;
import java.io.IOException;

public class ServerReceiver extends Thread{
	DataInputStream in;
	boolean activo;
	
	ServerReceiver(DataInputStream in ){
		this.in = in;
	}
	
	@Override
	public void run(){
		//bucle infinito con try/catch
		activo = true;
		try {
			while (activo) {
				String mensajeRecibido = in.readUTF();
				System.out.println(mensajeRecibido);
				
			}
		} catch (IOException e) {
			System.out.println("Conexión cerrada con el servidor");
		}
	}
	
	void parar(){
		activo=false;
	}

}
