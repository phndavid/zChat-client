package cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import servidor.mundo.Chater;
import servidor.mundo.Conversacion;

public class ListasConversaciones  extends JList<Conversacion>{
	/**
	 * 
	 */
    private static final long serialVersionUID = 6621254411169335111L;

    private DefaultListModel<Conversacion> dtModelListaConversaciones;
    
	public ListasConversaciones(){
		setLayout(new BorderLayout());
		setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		setModel(new DefaultListModel<Conversacion>());
		dtModelListaConversaciones = (DefaultListModel<Conversacion>) this.getModel();		
	}
	
	public void refrescarListaConversaciones(Conversacion amigos) 
	{
		if(!dtModelListaConversaciones.contains(amigos))
		dtModelListaConversaciones.addElement(amigos);
	}
	
	public void borrarConversacion(Conversacion conversacion)
	{
		dtModelListaConversaciones.removeElement(conversacion);
		this.validate();
		this.repaint();
	}
}
