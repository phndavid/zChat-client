package cliente.interfaz;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import servidor.mundo.Chater;
import servidor.mundo.Conversacion;
import servidor.mundo.Peticion;

public class HiloRecepcion {

	// ---------------------------------------------------------------------------------------------------------
	// Constantes
	// ---------------------------------------------------------------------------------------------------------
	public final static String REGISTRO_USUARIO = "registroUsuario";
	public final static String INICIO_SESION = "inicioSesion";
	public final static String BUSCAR_AMISTAD_POR_CUENTA = "buscarAmistadPorCuenta";
	
	public final static String AGREGAR_CONTACTO = "agregarContacto";
	public final static String ENVIO_MENSAJE = "envioMensaje";
	public final static String ENVIO_IMAGEN = "envioImagen";
	public final static String RESPONDER_PETICION = "responderPeticion";
	public final static String CERRAR_SESION = "cerrarSesion";

	public final static String RESPUESTA_INTENTO_AGREGAR = "respuestaIntentoAgregar";
	public final static String RECEPCION_PETICION = "recepcionPeticion";
	public final static String RECEPCION_RESPUESTA_PETICION = "recepcionRespuestaPeticion";
	public final static String RECEPCION_IMAGEN = "recepcionImagen";
	public final static String RECEPCION_MENSAJE = "recepcionMensaje";
	public final static String RECEPCION_INFORME_INICIO = "recepcionInformeDeInicio";
	public final static String RECEPCION_INFORME_CIERRE = "recepcionInformeDeCierre";
	
	public static final int PUERTO = 6500;
	public static final String SERVIDOR_NEL = "192.168.1.104";
	public static final String SERVIDOR_AND = "192.168.3.132";
	private static final Object BORRAR_CONVERSACION = "borrarConversacion";

	// ---------------------------------------------------------------------------------------------------------
	// Relaciones
	// ---------------------------------------------------------------------------------------------------------
	private VentanaAmigos ventanaAmigos;
	private VentanaZChat ventanaZChat;
	private Chater cliente;
	// ---------------------------------------------------------------------------------------------------------
	// Atributos
	// ---------------------------------------------------------------------------------------------------------
	private Socket canal;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	// ---------------------------------------------------------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------------------------------------------------------
	public HiloRecepcion(VentanaZChat ventanaZChat, VentanaAmigos ventanaAmigos) throws Exception {
		this.cliente = null;
		this.ventanaAmigos = ventanaAmigos;
		this.ventanaZChat = ventanaZChat;
	}
	
	public void activarSonidoClip(String path)
	{
		File archivoSonido = new File(path);
		 Clip sound;
		try {
			sound = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
			sound.open(AudioSystem.getAudioInputStream(archivoSonido));
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	public void comunicacionServer() throws Exception {
		canal = new Socket(SERVIDOR_NEL, PUERTO);
		out = new ObjectOutputStream(canal.getOutputStream());
		in = new ObjectInputStream(canal.getInputStream());
	}

	public void recibirInformacion() {
		Thread hiloReceptor = new Thread() {
			public void run() {
				Object[] datos;
				try {
					while ((!canal.isClosed()) && (datos = ((Object[]) in.readObject())) != null) {

						String comando = (String) datos[0];

						switch (comando) {

							case REGISTRO_USUARIO:
								registroUsuario(datos);
								break;

							case INICIO_SESION:
								iniciarSesion(datos);
								break;
								
							case BUSCAR_AMISTAD_POR_CUENTA:
								buscarAmistadPorCuenta(datos);
								break;
							case RESPUESTA_INTENTO_AGREGAR:
								respuestaIntentoAgregar(datos);
								break;

							case RECEPCION_PETICION:
								recepcionPeticion(datos);
								break;

							case RECEPCION_MENSAJE:
								recepcionMensaje(datos);
								break;

							case RECEPCION_IMAGEN:
								recepcionImagen(datos);
								break;

							case RECEPCION_INFORME_INICIO:
								recepcionInformeInicio(datos);
								break;
							case RECEPCION_INFORME_CIERRE:
								recepcionInformeCierre(datos);
								break;

							case RECEPCION_RESPUESTA_PETICION:
								recepcionRespuestaPeticion(datos);
								break;
							case CERRAR_SESION:
								boolean cerrarCanal = (boolean)datos[1];
								cerrarSesion();
								if(cerrarCanal) {
									canal.close();
								}
								break;
							default:
								break;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		hiloReceptor.start();
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//                                                                             Recepcion de HiloRecepcion
	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void registroUsuario(Object[] datos) {
		boolean condi = (boolean) datos[1];
		try {
			if (condi) {
				canal.close();
				JOptionPane.showMessageDialog(null, "Pudo registrarse. Iniciará sesión inmediatamente.");
			} else {
				canal.close();
				JOptionPane.showMessageDialog(null, "No pudo registrarse. Intente con otro nombre");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void iniciarSesion(Object[] datos) {
		boolean inicio = (boolean) datos[1];
		try {
			if (inicio) {
				this.ventanaAmigos = new VentanaAmigos(this,ventanaZChat);
				ventanaAmigos.setVisible(true);
				ventanaZChat.setVisible(false);
				ArrayList<String> misDatos = (ArrayList<String>) datos[2];
				ArrayList<Chater> contactosConectados = (ArrayList<Chater>) datos[3];
				ArrayList<Chater> contactosFull = (ArrayList<Chater>) datos[4];
				ArrayList<Peticion> peticiones = (ArrayList<Peticion>) datos[5];
				ArrayList<Conversacion> conversaciones = (ArrayList<Conversacion>) datos[6];

				String cuenta = misDatos.get(0);
				String nombre = misDatos.get(1);
				String apellido = misDatos.get(2);
				String sexo = misDatos.get(3);
				String fechaNacimiento = misDatos.get(4);
				String correoActual = misDatos.get(5);

//				for(Chater ch: contactosConectados)
//				{
//					ch.setEstadoConexion(true);
//				}

				cliente = new Chater(cuenta, nombre, apellido, null, sexo, correoActual, fechaNacimiento, null, null, false);
				//Cargar el mundo
				cliente.getContactos().addAll(contactosFull);
				cliente.getContactosConectados().addAll(contactosConectados);
				cliente.getPeticiones().addAll(peticiones);
				cliente.getConversaciones().addAll(conversaciones);
				
				//Acciones que inicializan la interfaz del usuario.
				ventanaAmigos.refrescarMisDatos(cuenta, nombre, apellido, sexo, fechaNacimiento, correoActual);
				//Acciones que refrescan listas de contactos.
				ventanaAmigos.refrescarListaAmigos(contactosFull);
				
				for(Chater c: contactosConectados)
				{
					ventanaAmigos.refrescarListaConectados(c, "Agregar");
				}
			//////////////////////////////////////////////////////
				for(Conversacion co: conversaciones)
				{
					if(!co.getConversacion().equals(""))
						ventanaAmigos.refrescarConversaciones(co.getContacto());
				}
				ventanaAmigos.refrescarListas(contactosFull, false);
				//Acciones que refrescan las peticiones
				ventanaAmigos.refrescarListaPeticiones(peticiones);
				
							
				
				
			} else {
				canal.close();
				JOptionPane.showMessageDialog(null, "No se pudo conectar el usuario");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void buscarAmistadPorCuenta(Object[] datos) {

		boolean encontroAmistad = (boolean) datos[1];
		if (encontroAmistad) {

			ArrayList<String> infoAmistad = (ArrayList<String>) datos[2];
			String cuenta = infoAmistad.get(0);
			String nombre = infoAmistad.get(1);
			String apellido = infoAmistad.get(2);
			String sexo = infoAmistad.get(3);
			String fechaNacimiento = infoAmistad.get(4);
			String correoActual = infoAmistad.get(5);

			ventanaAmigos.refrescarInformacionBasicaDeAmistad(cuenta, nombre, apellido, sexo, fechaNacimiento, correoActual);
		} else {
			JOptionPane.showMessageDialog(ventanaAmigos, "No se ha encontrado la persona buscada, tal vez no exista", "Atención usuario", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public Chater getCliente()
	{
		return cliente;
	}
	
	public Chater buscar(String cuentaCliente)
	{
		ArrayList<Chater> contactosCliente = cliente.getContactos();
		for(Chater c: contactosCliente)
		{
			if(c.getCuenta().equals(cuentaCliente))
			{
				return c;
			}
		}
		return null;
	}

	public void respuestaIntentoAgregar(Object[] datos) {
		boolean respuestaInmediata = (boolean) datos[1];
		String cuentaIntento = (String) datos[2];
		if (respuestaInmediata)
			JOptionPane.showMessageDialog(null, "Su solicitud de  amistad se a enviado exitósamente a " + cuentaIntento);
		else
			JOptionPane.showMessageDialog(null, "La cuenta de usuario: '"+ cuentaIntento +"' no existe");
		// Faltan las acciones de la interfaz.

	}

	public void recepcionPeticion(Object[] datos) {
		Peticion peticion = (Peticion) datos[1];
		ventanaAmigos.refrescarListaPeticiones(peticion);
		
		// Falta la accion de refrescar peticiones.

	}

	public void recepcionMensaje(Object[] datos) {
		
		Chater sender = (Chater) datos[1];
		String mensaje = (String) datos[2];
		ventanaAmigos.refrescarConversaciones(sender);
		//Si la conversacion seleccionada actualmente es igual a la del que envio esto.
		Conversacion con = ventanaAmigos.getCliente().buscarConversacion(sender.getCuenta());
		Conversacion cActual = ventanaAmigos.getConversacionActual();
		con.setConversacion(con.getConversacion() + mensaje);
		if(cActual != null && cActual == con)
		ventanaAmigos.refrescarConversacion(mensaje);
		// Método que refresca la conversación de ese sender.

	}

	public void recepcionImagen(Object[] datos) 
	{
		System.out.println("Recibiò imagen.");
		String sender2 = (String) datos[1];
		ImageIcon imagen = (ImageIcon) datos[2];
		ventanaAmigos.refrescarImagen(false, imagen);
	}

	public void recepcionRespuestaPeticion(Object[] datos) 
	{
		Chater hijoDelOtro = (Chater) datos[1];
		boolean estadoHijo = (boolean) datos[2];
		hijoDelOtro.setEstadoConexion(estadoHijo);
		ventanaAmigos.refrescarListaAmigos(hijoDelOtro);
		ventanaAmigos.refrescarListas(hijoDelOtro, estadoHijo);
		Conversacion c = new Conversacion(hijoDelOtro, "");
		cliente.getConversaciones().add(c);
	}

	public void recepcionInformeInicio(Object[] datos) 
	{
		Chater chaterConectado = (Chater) datos[1];
		cliente.getContactosConectados().add(chaterConectado);
		chaterConectado.setEstadoConexion(true);
		ventanaAmigos.refrescarListaConectados(chaterConectado, "Agregar");
	}

	public void recepcionInformeCierre(Object[] datos) 
	{
		Chater chaterDesconectado = (Chater) datos[1];
		chaterDesconectado.setEstadoConexion(false);
		cliente.getContactosConectados().remove(chaterDesconectado);
		ventanaAmigos.refrescarListaConectados(chaterDesconectado, "Borrar");
	}
	
	// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	//                                                                              Envíos del cliente
	// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void registroUsuario(String cuentaUsuario, String nombreUsuario, String apellidoUsuario, String contrasena,
	        String sexoUsuario, String correActualUsuario, String fechaNacimiento) throws Exception {
		if (canal == null || (canal != null && canal.isClosed())) {
			comunicacionServer();
			recibirInformacion();
		}
		Object[] datos = new Object[] { REGISTRO_USUARIO, cuentaUsuario, nombreUsuario, apellidoUsuario, contrasena, sexoUsuario,
		        fechaNacimiento, correActualUsuario };
		out.writeObject(datos);

	}

	public void inicioSesion(String cuentaUsuario, String contrasena) throws Exception 
	{
		if (canal == null || (canal != null && canal.isClosed())) {
			{
				comunicacionServer();
				recibirInformacion();
			}
			Object[] datos = new Object[] { INICIO_SESION, cuentaUsuario, contrasena };
			out.writeObject(datos);
		}
	}
	public void  buscarAmistadPorCuenta(String cuentaUsuario) 
	{
		
		try {

			Object[] datos = new Object[] {BUSCAR_AMISTAD_POR_CUENTA, cuentaUsuario};
			out.writeObject(datos);

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	public void agregarContacto(String cuentaUsuario) 
	{
		try {

			Object[] datos = new Object[] { AGREGAR_CONTACTO, cuentaUsuario};
			out.writeObject(datos);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void envioMensaje(String cuentaReceptor, String mensaje) {
		
		Object[] datos = new Object[] { ENVIO_MENSAJE, cuentaReceptor, mensaje };
		try {
			out.writeObject(datos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void envioImagen(String receptor, ImageIcon imagen) {
		if (imagen != null) {
			Object[] datos = new Object[] { ENVIO_IMAGEN, receptor, imagen };
			try {
				out.writeObject(datos);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void responderPeticion(String cuentaPropone, boolean respuesta) {
		try 
		{	
			Object[] datos = new Object[] { RESPONDER_PETICION, cuentaPropone, respuesta };
			out.writeObject(datos);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void cerrarSesion() {
		if ((canal != null && !canal.isClosed())) {
			try {
				Object[] info = new Object[] { CERRAR_SESION };
				out.writeObject(info);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void borrarConversacion()
	{
		Conversacion borrar = ventanaAmigos.getConversacionActual();
		if(borrar != null)
		{
			try {
				Object[] info = new Object[]{BORRAR_CONVERSACION, borrar.getContacto().getCuenta()};
				out.writeObject(info);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
