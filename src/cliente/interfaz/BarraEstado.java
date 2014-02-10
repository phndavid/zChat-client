package cliente.interfaz;



import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * 
 * @author phndavid
 * 
 */
public class BarraEstado extends JPanel implements ActionListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2298400872235310645L;
	
	private JLabel lbl_AmigosNuevos;
	private JLabel lbl_Conversaciones;
	private JLabel lbl_Empesados;
	
	
	
	public BarraEstado()
	{
		
		setLayout(new FlowLayout());
		Border border = LineBorder.createGrayLineBorder();
		setBorder(border);
		
		lbl_AmigosNuevos = new JLabel("Amigos Nuevos:  ");
		lbl_AmigosNuevos.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		
		lbl_Conversaciones = new JLabel("Conversaciones:  ");
		lbl_Conversaciones.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		
		lbl_Empesados = new JLabel("Empezados:  ");
		lbl_Empesados.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		
		add(lbl_AmigosNuevos);
		add(lbl_Conversaciones);
		add(lbl_Empesados);

	}

	public void actionPerformed(ActionEvent e)
	{
	
	}

}
