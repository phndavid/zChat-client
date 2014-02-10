package cliente.interfaz;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author phndavid
 * 
 */
public class BarraChat extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5771388722875043314L;
	public final static String BUSCAR = "Buscar";
	public final static String SALIR = "Salir";
	public final static String TXT_BUSCAR = "Texto Buscar";
	
	private JTextField txt_Buscar;
	private VentanaAmigos ventanaAmigos;
	private PestanasPrincipales panelPestanas;

	public BarraChat(VentanaAmigos ventanaAmigos,PestanasPrincipales panelBuscarAmigos)
	{
		
		this.ventanaAmigos = ventanaAmigos;
		this.panelPestanas = panelBuscarAmigos;
		setLayout(new FlowLayout());

		txt_Buscar = new JTextField(40);
		txt_Buscar.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Buscar.setText("Buscar Amigo");
		txt_Buscar.setForeground(Color.GRAY);
		txt_Buscar.setActionCommand(TXT_BUSCAR);
		txt_Buscar.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e)
			{
				if(txt_Buscar.getText().equals(""))
				{

					txt_Buscar.setText("Buscar Amigo");
					txt_Buscar.setForeground(Color.GRAY);
				}
			}

			public void focusGained(FocusEvent e)
			{
				if(txt_Buscar.getText().equals("Buscar Amigo"))
				{

					txt_Buscar.setText("");
					txt_Buscar.setForeground(Color.BLACK);
				}

			}
		});
		
		JButton btn_Salir  = new JButton("Salir");
		btn_Salir.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		btn_Salir.setActionCommand(SALIR);
		btn_Salir.addActionListener(this);
		
		JButton btn_Buscar  = new JButton("Buscar");
		btn_Buscar.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		btn_Buscar.setActionCommand(BUSCAR);
		btn_Buscar.addActionListener(this);
		
		add(btn_Salir);
		add(txt_Buscar);
		add(btn_Buscar);
	}

	public void actionPerformed(ActionEvent e)
	{
		String comand = e.getActionCommand();

			if(comand.equals(BUSCAR))
			{
				String cuentaUsuario = txt_Buscar.getText();
				ventanaAmigos.buscarAmistadPorCuenta(cuentaUsuario);
				panelPestanas.setSelectedComponent(panelPestanas.getPanelBuscarAmigos());	
			}
			else
				 if(comand.equals(SALIR))
					ventanaAmigos.dispose();
	}

}
