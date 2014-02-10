package cliente.interfaz;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import servidor.mundo.Chater;
import servidor.mundo.Conversacion;

public class PestanasPrincipales extends JTabbedPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4147911185825924483L;

	// ---------------------------------------------------------------------------------------------------------
	// Relaciones
	// ---------------------------------------------------------------------------------------------------------
	private VentanaAmigos ventanaAmigos;

	private PanelBuscarAmigos panelBuscarAmigos;
	private PanelMisDatos panelMisDatos;
	private ListaAmigos listaAmigos;
	private PanelChat panelChat;
	private PanelSolicitudes panelSolicitudes;


	// ---------------------------------------------------------------------------------------------------------
	// Constructor
	// ---------------------------------------------------------------------------------------------------------
	public PestanasPrincipales(VentanaAmigos ventanaAmigos) {
		
		this.ventanaAmigos = ventanaAmigos;

		listaAmigos = new ListaAmigos();
		JScrollPane scrollListaAmigos = new JScrollPane(listaAmigos);
		
		panelMisDatos = new PanelMisDatos();
		panelBuscarAmigos = new PanelBuscarAmigos(this);
		panelSolicitudes = new PanelSolicitudes(this);
		panelChat = new PanelChat(this);
		
		setFont(new Font("Segoe Print", Font.BOLD, 12));
		addTab("Mis Contactos", new ImageIcon("./data/vehiculo.png"), scrollListaAmigos);
		addTab("Mis Convesaciones", new ImageIcon("./data/vehiculo.png"), this.panelChat);
		addTab("Mis Datos", new ImageIcon("./data/cliente.png"), this.panelMisDatos);
		addTab("Buscar Amistades", new ImageIcon("./data/cliente.png"), this.panelBuscarAmigos);
		addTab("Mis Solicutudes", new ImageIcon("./data/cliente.png"), this.panelSolicitudes);

	}

	// ---------------------------------------------------------------------------------------------------------
	// Metodos del hilo 
	// ---------------------------------------------------------------------------------------------------------
	public void responderPeticion(String cuentaResponde,
			boolean respuesta)
	{
		ventanaAmigos.responderPeticion(cuentaResponde,respuesta);
	}
	public void  buscarAmistadPorCuenta(String cuentaUsuario) {
		ventanaAmigos.buscarAmistadPorCuenta(cuentaUsuario);
	}
	public void agregarContacto(String cuentaUsuario)
	{
		ventanaAmigos.agregarContacto(cuentaUsuario);
	}
	
	public Chater buscar(String cuenta)
	{
		return ventanaAmigos.buscar(cuenta);
	}
	public void refrescarImagen(boolean local, ImageIcon imagen)
	{
		panelChat.refrescarImagen(local,imagen);
	}
	
	public void envionMensaje(String receptor,String mensaje){
		ventanaAmigos.envionMensaje(receptor, mensaje);
	}
	public void envioImagen(String receptor, ImageIcon imagen){
		ventanaAmigos.envioImagen(receptor, imagen);
	}
	public Chater getCliente()
	{
		return ventanaAmigos.getCliente();
	}

	// ------------------------------------------------------------------------------------------------------------
		// Metodos refrescar
		// ---------------------------------------------------------------------------------------------------------
	public void refrescarMisDatos(String miCuenta, String nombre,String apellido, String sexo, String fechaNacimiento,	String correoActual)
	{
		panelMisDatos.refrescarMisDatos(miCuenta, nombre, apellido, sexo, fechaNacimiento, correoActual);
	}

	// Refresca la informacion basica de un cliente buscado por un amigo.
	public void refrescarInformacionBasicaDeAmistad(String miCuenta, String nombre, String apellido, String sexo,
	        String fechaNacimiento, String correoActual) {
		panelBuscarAmigos.refrescarInformacionBasicaDeAmistad(miCuenta, nombre, apellido, sexo, fechaNacimiento, correoActual);
	}
	
	// Refresca la lista de peticiones que tiene un cliente.
	public void refrescarListaPeticiones(Object peticiones){
		panelSolicitudes.refrescarListaDePeticiones(peticiones);
	}
	
	// Refresca la lista  general de amigos que tiene un cliente
	public void refrescarListaAmigos(Object amigos){
		listaAmigos.refrescarListaDeAmigos(amigos);
		
	}
	// Refresca la lista de contactos segun el estado(Conectado/Desconectado) del amigo.
	public void refrescarListas(Object amigos,boolean estado){
		panelChat.refrescarLitas(amigos, estado);
	}
	
	public void refrescarListaConectados(Chater amigo, String comando) {
		panelChat.refrescarListaConectados(amigo,comando);
	}

	public void refrescarConversacion(String mensaje) {
		panelChat.refrescarConversacion(mensaje);
	}

	public void refrescarConversaciones(Chater sender) {
		panelChat.refrescarConversaciones(sender);
		
	}

	public Conversacion getConversacionActual() {
		return panelChat.getConversacionActual();
	}

	public void borrarConversacion()
	{
		ventanaAmigos.borrarConversacion();
	}
	public PanelBuscarAmigos getPanelBuscarAmigos() {
		return panelBuscarAmigos;
	}
}
