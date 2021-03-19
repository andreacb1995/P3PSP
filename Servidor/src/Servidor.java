import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	Servidor(){
		//Objeto servidor y voy a iniciar clase estatica para ver los clientes conectados.
		boolean activo = true;

		try {
			ServerSocket serverSocket = new ServerSocket(ConstantesServidor.PUERTO_ESCUCHA);
			System.out.println("El servidor se ha iniciado");
			if (ListaClientes.conectados == null) {
				ListaClientes.initList();
			}
			while (activo) {
				Socket socket = serverSocket.accept();
				System.out.println("Un cliente se ha conectado\n");
				ClienteConectado clienteConectado = new ClienteConectado(socket);
				clienteConectado.start();
			}
		} catch (IOException e) {
			System.out.println(ConstantesServidor.CMD_DISCON_ERR);
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Servidor servidor = new Servidor();
	}
}
