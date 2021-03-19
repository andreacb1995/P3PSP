import java.util.ArrayList;
import java.util.List;

public class ListaClientes {
	static List<ClienteConectado> conectados;
	
	static void initList() {
		//inicializar la lista e incluir los clientes conectados que le llegen
		conectados = new ArrayList<ClienteConectado>();
		
	}
	
	static int getNumConectados() {
		return conectados.size();
	
	}
	
	static void deleteClienteById(long id) {
		int i = getPosicionById(id);
		conectados.remove(i);
		
	}
	
	static String getNombres() {
		String listaNombres = "";
		for (ClienteConectado clienteConectado : conectados) {
			listaNombres = listaNombres + clienteConectado.getNick() + "\n";
		}
		return listaNombres;
		
	}
	
	static boolean enviarMensaje(long id, String mensaje) {
		// Envia el mensaje al cliente id
				String nickEnviaMensaje = "";
				for (ClienteConectado clienteConectado : conectados) {
					if (clienteConectado.getId() == id) {
						nickEnviaMensaje = clienteConectado.getNick();
					}
				}
				for (ClienteConectado clienteConectado : conectados) {
					if (clienteConectado.getId() == id) {
						clienteConectado.enviarRespuesta(">" + nickEnviaMensaje + ": " + mensaje +"\n");
						return true;
					}
				}
				return false;
		
	}
	
	static int getPosicionById(long id) {
		int position = 0;
		for (int i = 0; i < conectados.size(); i++) {
			if (conectados.get(i).getId() == id) {
				position = i;
			}
		}
		return position;
		
	}
	
	static long getIdByNick(String nick) {
		long id = 0;
		for (ClienteConectado clienteConectado : conectados) {
			if (clienteConectado.getNick().equals(nick)) {
				id = clienteConectado.getId();
				return id;
			}
		}
		return id;
		
	}
	
	
}
