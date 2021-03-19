import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class ClienteConectado extends Thread {
	String nick;
	String nickname;
	long id;
	Socket socket;
	DataInputStream in;
	DataOutputStream out;
	public DataInputStream in2;
	public DataOutputStream out2;
	long idCouver;
	long idCou;
	public ClienteConectado clienteConectado;

	
	ClienteConectado(Socket socket) {
		this.socket = socket;
	}

	public ClienteConectado(String nick, long id, Socket socket, DataInputStream in, DataOutputStream out,
			long idCou) {
		super();
		this.nick = nick;
		this.id = id;
		this.socket = socket;
		this.in = in;
		this.out = out;
		this.idCouver = idCou;
	}

	@Override
	public void run() {
		boolean seguir = true;
		try {
			id = System.currentTimeMillis();
			idCou=0;
//			Socket s= socket;
			String comandoRecibido;
			in2 = new DataInputStream(socket.getInputStream());
			out2 = new DataOutputStream(socket.getOutputStream());
			nickname = in2.readUTF();
			out2.writeUTF("Estas conectado con el nick " + nickname);
			out2.writeUTF(ConstantesServidor.CMD_AYUDA);
			clienteConectado = new ClienteConectado(nickname, id, socket, in2, out2, idCou);
			ListaClientes.conectados.add(clienteConectado);

			while (seguir) {
				comandoRecibido = in2.readUTF();
				if (comandoRecibido.startsWith("#")) {
					procesarCmd(comandoRecibido);
				}
				if (comandoRecibido.equals("#salir"))
					seguir = false;
				
				if (clienteConectado.getIdCouver() != 0) {
					idCou = clienteConectado.getIdCouver();
					ListaClientes.enviarMensaje(idCou, comandoRecibido);
						
				}
				
			}
		} catch (IOException e1) {
			System.out.println(ConstantesServidor.CMD_DISCON_ERR);
		}
	}

	void enviarRespuesta(String res) {
		try {
			out.writeUTF(res);
		} catch (IOException e) {

			try {
				out2.writeUTF(ConstantesServidor.CMD_ERR);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	void procesarCmd(String cmd) throws IOException {
		final String ayuda = "#ayuda";
		final String listar = "#listar";
		final String charlar = "#charlar";
		final String salir = "#salir";

		StringTokenizer stringTokenizer = new StringTokenizer(cmd);
		String comandoRecibido = stringTokenizer.nextToken();
		switch (comandoRecibido.toLowerCase()) {
		case (ayuda):
			out2.writeUTF(ConstantesServidor.CMD_AYUDA);
			break;
		case (listar):
			out2.writeUTF(ListaClientes.getNombres());
			break;
		case (charlar):
			nick = stringTokenizer.nextToken();
			idCouver = ListaClientes.getIdByNick(nick);
			if (idCouver != 0) {
				clienteConectado.setIdCouver(idCouver);
				out2.writeUTF(ConstantesServidor.CMD_CHARLAR_OK);
			} else {
				out2.writeUTF(ConstantesServidor.CMD_CHARLAR_ERR);
			}
			break;
		case (salir):
			out2.writeUTF(ConstantesServidor.BYE_NSG);
			ListaClientes.deleteClienteById(id);
			break;
		default:
			out2.writeUTF(ConstantesServidor.CMD_ERR);
			break;
		}
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public DataInputStream getIn() {
		return in;
	}

	public void setIn(DataInputStream in) {
		this.in = in;
	}

	public DataOutputStream getOut() {
		return out;
	}

	public void setOut(DataOutputStream out) {
		this.out = out;
	}

	public long getIdCouver() {
		return idCouver;
	}

	public void setIdCouver(long idCouver) {
		this.idCouver = idCouver;
	}
}
