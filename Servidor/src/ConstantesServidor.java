
public class ConstantesServidor {
	static int PUERTO_ESCUCHA = 4532;
	static String CMD_AYUDA = "#ayuda\n#listar : lista todos los usuarios conectados.\n#charlar <usuario> : "
			+ "comienza la comunicación con el usuario <usuario>\n#salir : se desconecta del chat.\n"; //muestra todas las opciones
	static String CMD_CHARLAR_ERR = "[ERROR] El usuario no se encuentra conectado. Utiliza el comando #listar para ver los usuarios conectados.\n";//error de respuesta,si el cliente no se ha conectado
	static String CMD_CHARLAR_OK= "Ahora estás conectado con el usuario.Escribe para hablarle.\n"; //La conversación se ha iniciado con...
	static String CMD_ERR= "[ERROR] El comando introducido no se reconoce como comando.\n"; //comando no reconocido
	static String CMD_DISCON_ERR="[ERROR] Un cliente ha cerrado el chat.\n"; //hablando con alguien y que se desconecte
	String CMD_DISCON2_ERR; //error de la conversacion(se apague de repente)
	static String BYE_NSG= "Chat cerrado.\n"; //cerrar,mensaje de desconexión
}
