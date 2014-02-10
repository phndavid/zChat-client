package cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import servidor.mundo.Chater;
import servidor.mundo.Conversacion;

public class VentanaAmigos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5618072784935018389L;

	private VentanaZChat ventanaZChat;
	private HiloRecepcion hiloRecepcion;

	private BarraChat barraChat;
	private BarraEstado barraEstado;
	private PestanasPrincipales pestanasPrincipales;
	private MenuChat menuZchat;

	// ---------------------------------------------------------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------------------------------------------------------
	public VentanaAmigos(HiloRecepcion hiloRecepcion, VentanaZChat ventanaZChat) {

		this.hiloRecepcion = hiloRecepcion;
		this.ventanaZChat = ventanaZChat;
		
		setTitle("Letters app");
		setSize(660, 550);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		setLayout(new BorderLayout());
		menuZchat = new MenuChat();
		setJMenuBar(menuZchat);

		pestanasPrincipales = new PestanasPrincipales(this);
		barraEstado = new BarraEstado();
		barraChat = new BarraChat(this,pestanasPrincipales);

		add(barraChat, BorderLayout.NORTH);
		add(pestanasPrincipales, BorderLayout.CENTER);
		add(barraEstado, BorderLayout.SOUTH);

		center();
	}

	private void center() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xEsquina = (screen.width - getWidth()) / 2;
		int yEsquina = (screen.height - getHeight()) / 2;
		setLocation(xEsquina, yEsquina);
	}

	// ---------------------------------------------------------------------------------------------------------
	// Metodos del hilo
	// ---------------------------------------------------------------------------------------------------------
	public void responderPeticion(String cuentaPropone, boolean respuesta) {
		hiloRecepcion.responderPeticion(cuentaPropone, respuesta);
	}

	public void buscarAmistadPorCuenta(String cuentaUsuario) {
		hiloRecepcion.buscarAmistadPorCuenta(cuentaUsuario);
	}

	public void agregarContacto(String cuentaUsuario) {
		hiloRecepcion.agregarContacto(cuentaUsuario);
	}

	public Chater buscar(String cuenta) {
		return hiloRecepcion.buscar(cuenta);
	}

	public void envionMensaje(String receptor, String mensaje) {
		hiloRecepcion.envioMensaje(receptor, mensaje);
	}

	public void envioImagen(String receptor, ImageIcon imagen) {
		hiloRecepcion.envioImagen(receptor, imagen);
	}

	public Chater getCliente() {
		return hiloRecepcion.getCliente();
	}

	public void refrescarImagen(boolean local, ImageIcon imagen) {
		pestanasPrincipales.refrescarImagen(local, imagen);
	}

	// ---------------------------------------------------------------------------------------------------------
	// Metodos refrescar de las pestanas
	// ---------------------------------------------------------------------------------------------------------

	// Refresca la informacion basica de un cliente buscado por un amigo.
	public void refrescarInformacionBasicaDeAmistad(String miCuenta, String nombre, String apellido, String sexo,
	        String fechaNacimiento, String correoActual) {
		pestanasPrincipales.refrescarInformacionBasicaDeAmistad(miCuenta, nombre, apellido, sexo, fechaNacimiento, correoActual);
	}

	public void refrescarMisDatos(String miCuenta, String nombre, String apellido, String sexo, String fechaNacimiento,
	        String correoActual) {
		pestanasPrincipales.refrescarMisDatos(miCuenta, nombre, apellido, sexo, fechaNacimiento, correoActual);
	}

	// Refresca la lista de peticiones que tiene un cliente.
	public void refrescarListaPeticiones(Object peticiones) {
		pestanasPrincipales.refrescarListaPeticiones(peticiones);
	}

	// Refresca la lista general de amigos que tiene un cliente
	public void refrescarListaAmigos(Object amigos) {
		pestanasPrincipales.refrescarListaAmigos(amigos);
	}

	// Refresca la lista de contactos segun el estado(Conectado/Desconectado)
	// del amigo.
	public void refrescarListas(Object amigos, boolean estadoAmigo) {
		pestanasPrincipales.refrescarListas(amigos, estadoAmigo);
	}

	public void refrescarListaConectados(Chater amigo, String comando) {
		pestanasPrincipales.refrescarListaConectados(amigo, comando);
	}

	public void refrescarConversacion(String mensaje) {

		pestanasPrincipales.refrescarConversacion(mensaje);
	}

	public void dispose() {
		ventanaZChat.dispose();
		ventanaZChat.setVisible(true);
		super.dispose();
	}

	public Conversacion getConversacionActual() {
		return pestanasPrincipales.getConversacionActual();
	}

	public void refrescarConversaciones(Chater sender) {

		pestanasPrincipales.refrescarConversaciones(sender);
	}

	public void borrarConversacion() {
		hiloRecepcion.borrarConversacion();
	}

}