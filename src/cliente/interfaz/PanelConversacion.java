package cliente.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import servidor.mundo.Conversacion;

public class PanelConversacion extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7963014953137629740L;
	private TextBubbleBorder textBubbleBorder;
	private int posy = 44;
	
	public PanelConversacion()
	{
		textBubbleBorder = new TextBubbleBorder(Color.WHITE);
		setLayout(null);
	}

	public void refrescarConversacion(String mensaje, String cuenta) {
		if (!mensaje.equals("")) {
			
			String[] cadenas = mensaje.split("\n");
			String nombreActual = cadenas[0].split(":", 3)[1];
			String envio = "";
			int numLineas = 1;
			boolean primeraVez = true;
			for (String cadena : cadenas) {
				String[] info = cadena.split(":", 3);
				String comandoLinea = info[0];
				String nombreCadena = info[1];
				String mensajeCadena = info[2];

				if (nombreCadena.equals(nombreActual) && (comandoLinea.equals("0") || primeraVez)) {
					if (!primeraVez)
						numLineas++;
					envio += mensajeCadena + "\n";
					if (primeraVez)
						primeraVez = false;
				} else {
					agregarBoton(envio, cuenta, nombreActual, numLineas);
					envio = mensajeCadena + "\n";
					nombreActual = nombreCadena;
					numLineas = 1;
				}
			}
			agregarBoton(envio, cuenta, nombreActual, numLineas);
		}
	}
	
	public void agregarBoton(String texto, String cuentaCliente, String cuentaActual, int numLineas) {

		String[] datos = texto.split("\n");
		int longitud = 0;
		String cadena = "";
		for (String gr : datos) {
			if (gr.length() > longitud) {
				cadena = gr;
				longitud = gr.length();
			}
		}

		Font f = new Font("Segoe UI", Font.PLAIN, 12);
		Rectangle2D r = f.getStringBounds(cadena, new FontRenderContext(null, RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT, RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT));

		int ancho = (int) r.getWidth() + 20;
		int alto = (int) r.getHeight() + 10;

//		if (!texto.equals("\n")) {  // Si queremos que no pinte las lineas vacias.Las pinta si es un parrafo separado por una linea vacia
			
			if (cuentaCliente.equals(cuentaActual)) {
				JLabel mensaje = textBubbleBorder.agregarTextoCliente(texto+"\n", this, ancho, posy, alto, numLineas);
				JOptionPane.showInputDialog(null,mensaje);
				this.add(mensaje);
				posy += (alto * numLineas) + 6;
				this.setPreferredSize(new Dimension(this.getWidth(), posy));
				this.repaint();
			} else {
				textBubbleBorder.agregarTextoAmigo(texto, this, ancho, posy, alto, numLineas);
				posy += (alto * numLineas) + 6;
				this.setPreferredSize(new Dimension(this.getWidth(), posy));
				this.repaint();
			}
//		}
	}
	
	public void refrescarTexto(Conversacion c, String cuenta)
	{
		this.removeAll();
		posy =44;
		String texto = c.getConversacion();
		refrescarConversacion(texto, cuenta);
	}
	
	public void reiniciarTexto() {
		this.removeAll();
		posy = 44;
		this.repaint();

	}

	public void refrescarImagen(boolean b, ImageIcon imagen) {
		
		ImageIcon img = resizeImagen(imagen);
		 
		int altoImagen = img.getIconHeight();
		 int anchoImagen = img.getIconWidth();
		 JLabel lbl_Image = new JLabel(img);
		 
		 if(b)
		 {
			 lbl_Image.setBounds(this.getWidth()-anchoImagen-20, posy, anchoImagen, altoImagen);
			 this.add(lbl_Image);
			 posy += altoImagen + 6;
			 this.setPreferredSize(new Dimension(this.getWidth(), posy));
			 this.repaint();
		 }
		 else
		 {
			 lbl_Image.setBounds(this.getX()+10, posy, anchoImagen, altoImagen);
			 this.add(lbl_Image);
			 posy += altoImagen + 6;
			 this.setPreferredSize(new Dimension(this.getWidth(), posy));
			 this.repaint();			 
		 }
	}
	
	public ImageIcon resizeImagen(ImageIcon imageIcon)
	{
		Image image = imageIcon.getImage();
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		return imageIcon;
	}
	
}
