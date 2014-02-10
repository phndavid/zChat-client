package cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.crypto.AEADBadTagException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import servidor.mundo.Chater;
import servidor.mundo.Conversacion;

public class PanelMensaje extends JPanel implements ActionListener, ListSelectionListener ,ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7063305273207881380L;
	public final static String SEND = "Send";
	public final static String SEND_IMAGE = "Send Image";
	public final static String DELETE = "DELETE";
	public final static String DELETE_CON = "Delete Con";
	public static final int MAX = 40;

	private PanelChat panelChat;
	
	private PanelConversacion panelConversacion;
	private ListasConversaciones listasConversaciones;
	
	private JTextArea areaTexto;
	private JCheckBox checkBox;

	public PanelMensaje(PanelConversacion panelConversacion, ListasConversaciones listasConversaciones, PanelChat panelChat) {

		this.panelConversacion = panelConversacion;
		this.listasConversaciones = listasConversaciones;
		this.listasConversaciones.addListSelectionListener(this);
		this.panelChat = panelChat;

		setBorder(new TitledBorder("Message"));
		setLayout(new BorderLayout());
		
		areaTexto = new JTextArea();
		areaTexto.setWrapStyleWord(true);
		areaTexto.setLineWrap(true);
		JScrollPane scrollMensaje = new JScrollPane(areaTexto);

		JPanel panelSend = new JPanel();
		panelSend.setLayout(new BorderLayout());
		
		checkBox = new JCheckBox("Press ''Enter'' to send",false);
		checkBox.addChangeListener(this);
		panelSend.add(checkBox,BorderLayout.SOUTH);
		
		JButton btnSend = new JButton("Send");
		btnSend.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		btnSend.setActionCommand(SEND);
		btnSend.addActionListener(this);
		panelSend.add(btnSend,BorderLayout.CENTER);
		
		JPanel panelButton = new JPanel();
		panelButton.setLayout(new GridLayout(1, 3));

		JButton btnSendImage = new JButton("Send Image");
		btnSendImage.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		btnSendImage.setActionCommand(SEND_IMAGE);
		btnSendImage.addActionListener(this);
		panelButton.add(btnSendImage);

		JButton btnDeleteCon = new JButton("Delete conversation");
		btnDeleteCon.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		btnDeleteCon.setActionCommand(DELETE_CON);
		btnDeleteCon.addActionListener(this);
		panelButton.add(btnDeleteCon);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		btnDelete.setActionCommand(DELETE);
		btnDelete.addActionListener(this);
		panelButton.add(btnDelete);

		add(scrollMensaje, BorderLayout.CENTER);
		add(panelSend, BorderLayout.EAST);
		add(panelButton, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Conversacion conversacion = listasConversaciones.getSelectedValue();

		if (conversacion != null) {
			
			Chater contacto = conversacion.getContacto();
			String cuentaReceptor = contacto.getCuenta();
			if (e.getActionCommand().equals(SEND))
				sendMessage(conversacion, cuentaReceptor);
			else if (e.getActionCommand().equals(SEND_IMAGE))
				sendImage(cuentaReceptor);
			else if (e.getActionCommand().equals(DELETE))
				areaTexto.setText("");
			else if (e.getActionCommand().equals(DELETE_CON))
				deleteConversation();
		} else
			JOptionPane.showMessageDialog(this, "Selecciona una conversacion");
	}
	@Override
    public void stateChanged(ChangeEvent e) {
	    
			if(checkBox.isSelected()) {
				areaTexto.resetKeyboardActions();
				areaTexto.addKeyListener(new KeyListener(){
				    @Override
				    public void keyPressed(KeyEvent e){
				    	Conversacion conversacion = listasConversaciones.getSelectedValue();

						if (conversacion != null) {
							if (e.getKeyCode() == KeyEvent.VK_ENTER) {
									Chater contacto = conversacion.getContacto();
									String cuentaReceptor = contacto.getCuenta();
									sendMessage(conversacion, cuentaReceptor);
								}
							}
				    }
				    @Override
				    public void keyTyped(KeyEvent e) {
				    }

				    @Override
				    public void keyReleased(KeyEvent e) {
				    }
				});
			}else {
				KeyListener[] listener = areaTexto.getKeyListeners();
				for (int i = 0; i < listener.length; i++) {
	                areaTexto.removeKeyListener(listener[i]);
                }
			}
    }

	//-----------------------------------------------------------------------------------------------------------
	//Acciones de los botones.
	//-----------------------------------------------------------------------------------------------------------

	public void sendMessage(Conversacion conversacion, String cuentaReceptor) {
		String mensaje = romperLineas(areaTexto);
		String[] mens = mensaje.split("\n");
		String texto = agregarFormato(mensaje);
		refrescarMesajeLocal(texto);
		conversacion.setConversacion(conversacion.getConversacion() + texto);
		panelChat.envionMensaje(cuentaReceptor, texto);
		areaTexto.setText("");
	}
	public void sendImage(String cuentaReceptor) {
		File archivo = seleccionarArchivo();
		if (archivo != null) {
			ImageIcon imagen = new ImageIcon(archivo.getPath());
			refrescarImagen(true, imagen);
			panelChat.envioImagen(cuentaReceptor, imagen);
		}
	}
	
	public String quitarEspacios(String texto)
	{
		String[] info = texto.split("\n");
		String cadena = "";
		for(String linea: info)
		{
			if(!linea.equals(""))
			{
				cadena += linea+"\n";
			}
		}
		return cadena;
	}
	
	public void deleteConversation() {
		listasConversaciones.getSelectedValue().setConversacion("");
		panelConversacion.reiniciarTexto();
		panelChat.borrarConversacion();
		listasConversaciones.borrarConversacion(listasConversaciones.getSelectedValue());
	}
	//-----------------------------------------------------------------------------------------------------------
	// Metodos para refrescar la conversacion.
	//-----------------------------------------------------------------------------------------------------------
	
	public File seleccionarArchivo() {
		File archivo = null;
		JFileChooser seleccion = new JFileChooser();
		seleccion.showOpenDialog(this);
		archivo = seleccion.getSelectedFile();
		return archivo;
	}
	
	public void refrescarMesajeLocal(String mensaje) {
		String cuentaCliente = panelChat.getPanelPestanas().getCliente().getCuenta();
		panelConversacion.refrescarConversacion(mensaje, cuentaCliente);
	}

//	public String quitarEspacios(String mensaje) {
//		String textoSinEspacios = "";
//		String[ ] lineas = mensaje.split("\n");
//		for (int i = 0; i < lineas.length; i++) {
//	        String tx = lineas[i];
//	        if(tx.contains("\n"))
//	        	tx.replaceAll("\n", "");
//	        textoSinEspacios += tx +"\n";
//        }
//		return textoSinEspacios;
//	}
	public String romperLineas(JTextArea textArea) {
		StringBuilder text = new StringBuilder(textArea.getText());
		int lineHeight = textArea.getFontMetrics(textArea.getFont()).getHeight();
		Point view = new Point(textArea.getWidth(), textArea.getInsets().top);
		int length = textArea.getDocument().getLength();
		int endOfLine = textArea.viewToModel(view);
		int lines = 0;

	
		while (endOfLine < length) {
			int adjustedEndOfLine = endOfLine + lines;
			text.insert(adjustedEndOfLine + 1, '\n');
			lines++;
			view.y += lineHeight;
			endOfLine = textArea.viewToModel(view);
		}

		return text.toString();
	}

	public String agregarFormato(String mensaje) {
		
		String cad = "";
		String[] lineas = mensaje.split("\n");
		String cuenta = panelChat.getPanelPestanas().getCliente().getCuenta();
		boolean primeraVez = true;
		String ultimaLineaConTexto = "";
		boolean espa = false;
		
		String espacio = "";

		for (int i=0; i<lineas.length;i++) {
			
			String linea = lineas[i];
			
			if(i+1<lineas.length)
				espacio = lineas[i+1];
			
//			if(linea.equals("") && espacio.equals(""))
//				espa = true;
			
			System.out.println("Linea: "+linea);
			if(!linea.equals("")) { // Si queremos que no se puedan enviar  lineas vacias.
			
				if (primeraVez) {
					cad += "1:" + cuenta + ":" + linea + "\n";
					primeraVez = false;
				} else {
					cad += "0:" + cuenta + ":" + linea + "\n";
				}
				espa  = false;
			}
		}
		return cad;
		
		
	}

	public void refrescarImagen(boolean local, ImageIcon imagen) {
		panelConversacion.refrescarImagen(local, imagen);
	}

	public Conversacion getConversacionActual() {
		return listasConversaciones.getSelectedValue();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			String cuentaCliente = panelChat.getPanelPestanas().getCliente().getCuenta();
			Conversacion conversa = listasConversaciones.getSelectedValue();
			if (conversa != null)
				panelConversacion.refrescarTexto(conversa, cuentaCliente);
		}
	}



	
	

	

}
