package cliente.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListCellRenderer;

import servidor.mundo.Chater;
import servidor.mundo.Conversacion;

public class PestanasListasChat extends JTabbedPane implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4531900009389056148L;
	private PanelChat panelChat;
	private ListaAmigos listaConectados;
	private ListaAmigos listaMisAmigos;
	private ListasConversaciones listaConversaciones;
	private JScrollPane scrollConectados;
	private JScrollPane scrollConversaciones;
	private JScrollPane scrollDesconectados;
	public PestanasListasChat(PanelChat panelChat)
	{
		setFont(new Font("Segoe Print", Font.PLAIN, 12));
		this.panelChat = panelChat;
	
		listaConectados = new ListaAmigos();
		listaConectados.addMouseListener(this);
		listaConectados.setCellRenderer(new MyListCellThing());
		scrollConectados = new JScrollPane(listaConectados);
		
		
		listaConversaciones = new ListasConversaciones();
		listaConversaciones.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		listaConversaciones.setForeground(Color.BLUE);
		scrollConversaciones = new JScrollPane(listaConversaciones);
		
		listaMisAmigos = new ListaAmigos();
		listaMisAmigos.addMouseListener(this);
		listaMisAmigos.setCellRenderer(new MyListCellThing());
		scrollDesconectados = new JScrollPane(listaMisAmigos);

		addTab("Conversations", scrollConversaciones);
		addTab("My Friends", scrollDesconectados);
		addTab("Connected", scrollConectados);
	}
	
	public class MyListCellThing extends JLabel implements ListCellRenderer {

	    public MyListCellThing() {
	    	setFont(new Font("Segoe Print", Font.PLAIN, 12));
	    }
	    public Component getListCellRendererComponent(JList list, Object dato, int index, boolean isSelected, boolean cellHasFocus) {
	        setText(dato.toString());
	    	Chater chater = (Chater) dato;
	        boolean conectado = chater.isEstadoConexion();
	        if(conectado) {
	        	
	        	setForeground(new Color(34,177,76));
	        	setIcon(new ImageIcon("./data/conectado.png"));
	        }else {
	        	setForeground(Color.RED);
	        	setIcon(new ImageIcon("./data/desconectado.jpg"));
	        }
	        return this;
	    }
	}
	public void refrescarListas(Object amigos,boolean estado){
		
		if(estado)
			listaConectados.refrescarListaDeAmigos(amigos);
		
		listaMisAmigos.refrescarListaDeAmigos(amigos);
		
	}
	
	public void refrescarConversaciones(Chater sender) {
		DefaultListModel<Conversacion> modelo = (DefaultListModel<Conversacion>) listaConversaciones.getModel();
		String cuentaSender = sender.getCuenta();
		Conversacion conversa =  panelChat.getPanelPestanas().getCliente().buscarConversacion(cuentaSender);
		if(!modelo.contains(conversa))
		{
			modelo.addElement(conversa);
		}
	}
	
	public void refrescarListaConectados(Chater amigo, String comando) {
		if(comando.equals("Agregar"))
		listaConectados.refrescarListaDeAmigos(amigo);
		else
			listaConectados.borrarDeListaDeAmigos(amigo);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2){
			Chater chater = null;
			if(e.getComponent().equals(listaConectados))
				chater = listaConectados.getSelectedValue();
			else
				chater = listaMisAmigos.getSelectedValue();
			
			setSelectedComponent(this.scrollConversaciones);
			String cuenta = chater.getCuenta();
			Conversacion conversacion = panelChat.getPanelPestanas().getCliente().buscarConversacion(cuenta);
			listaConversaciones.refrescarListaConversaciones(conversacion);
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public ListasConversaciones getListasConversaciones(){
		return listaConversaciones;
	}
}
