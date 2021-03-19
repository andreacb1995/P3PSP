import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerSender extends Thread {
	DataOutputStream out;
	boolean activo;
	BufferedReader teclado;

	ServerSender(DataOutputStream out) {
		this.out = out;
	}

	public void run() {
		activo = true;
		teclado = new BufferedReader(new InputStreamReader(System.in));

		while (activo) {
			try {
				String comandoUsuario = teclado.readLine();
				out.writeUTF(comandoUsuario);
				if (comandoUsuario.equals("#salir")) {
					sleep(300);
					parar();
				}
			} catch (IOException | InterruptedException | NullPointerException e) {
				System.out.println("Conexión interrumpida.");
			}
		}
	}

	void parar() {
		// throw exception/interrupted exception
		activo = false;
		try {
			teclado.close();
			out.close();
		} catch (IOException e) {
			System.out.println("Conexión interrumpida.");
			e.printStackTrace();
		}

	}
}
