package cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import servidor.mundo.Chater;
import servidor.mundo.Conversacion;

public class PanelChat extends JPanel{

	/**
	 *  
	 */
	private static final long serialVersionUID = 5080777567531524879L;
	private PestanasPrincipales pestanasPrincipales;
	
	private PestanasListasChat panelListas;
	private PanelConversacion panelConversacion;
	private PanelMensaje panelMensaje;

	public PanelChat(PestanasPrincipales panelPestanas)
	{
		this.pestanasPrincipales = panelPestanas;
		setLayout(null);
		
		panelListas = new PestanasListasChat(this);
		panelListas.setBounds(410, 0, 230, 380);
		
		panelConversacion = new PanelConversacion();
		JScrollPane scroll = new JScrollPane(panelConversacion);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		panelMensaje = new PanelMensaje(panelConversacion, panelListas.getListasConversaciones(),this);

		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,scroll, panelMensaje);
		splitPane.setBackground(Color.WHITE);
		splitPane.setDividerLocation(250);
		splitPane.setBounds(0, 0, 410, 380);
		add(panelListas,BorderLayout.EAST);
		add(splitPane, BorderLayout.CENTER);
	}

	public void refrescarLitas(Object amigos,boolean estado){
		panelListas.refrescarListas(amigos, estado);
	}
	public void refrescarListaConectados(Chater amigo, String comando) {
		panelListas.refrescarListaConectados(amigo,comando);
	}
	public void envionMensaje(String receptor,String mensaje){
		pestanasPrincipales.envionMensaje(receptor, mensaje);
	}
	public void envioImagen(String receptor, ImageIcon imagen){
		pestanasPrincipales.envioImagen(receptor, imagen);
	}
	public PestanasPrincipales getPanelPestanas(){
		return pestanasPrincipales;
	}
	public void refrescarConversacion(String mensaje) {
		
		panelMensaje.refrescarMesajeLocal(mensaje);
	}
	public void refrescarImagen(boolean local, ImageIcon imagen)
	{
		panelMensaje.refrescarImagen(local,imagen);
	}
	public void refrescarConversaciones(Chater sender) {
		panelListas.refrescarConversaciones(sender);
	}
	
	public Conversacion getConversacionActual()
	{
		return panelMensaje.getConversacionActual();
	}
	public void borrarConversacion() {
		pestanasPrincipales.borrarConversacion();
		
	}

	

}
