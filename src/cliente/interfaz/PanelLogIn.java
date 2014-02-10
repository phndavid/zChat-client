package cliente.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelLogIn extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8684816697736677331L;
	public final static String INICIAR_SESION = "Iniciar Sesion";
	public final static String REGISTRAR = "Registrar";

	private VentanaZChat ventanaZChat;
	private JTextField txt_CuentaUsuario;
	private JPasswordField password;

	// ---------------------------------------------------------------------------------------------------------
	// Constructor
	// --------------------------------------------------------------------------------------------------------
	public PanelLogIn(VentanaZChat ventanaZChat)
	{
		this.ventanaZChat = ventanaZChat;
		setLayout(new GridLayout(1, 2));

		JPanel panelRight = new JPanel();
		panelRight.setLayout(new GridLayout(1, 1));
		panelRight.add(new JLabel(new ImageIcon("./data/chat.png")));

		JPanel panelLeft = new JPanel();
		panelLeft.setLayout(new GridBagLayout());
		panelLeft.setBackground(Color.WHITE);

		GridBagConstraints constraints = new GridBagConstraints();
		JLabel lblLogIn = new JLabel("Inicia Sesion:");
		lblLogIn.setFont(new Font("Segoe Print", Font.BOLD, 20));
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weighty = 0.001;
		panelLeft.add(lblLogIn, constraints);

		JLabel lblName = new JLabel("Nombre de tu cuenta:");
		lblName.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		panelLeft.add(lblName, constraints);

		txt_CuentaUsuario = new JTextField(20);
		txt_CuentaUsuario.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_CuentaUsuario.setText("Ej: phndavid");
		txt_CuentaUsuario.setForeground(Color.GRAY);
		constraints.gridx = 0;
		constraints.gridy = 4;
		txt_CuentaUsuario.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e)
			{
				if(txt_CuentaUsuario.getText().equals(""))
				{

					txt_CuentaUsuario.setText("Ej: phndavid");
					txt_CuentaUsuario.setForeground(Color.GRAY);
				}
			}

			public void focusGained(FocusEvent e)
			{
				if(txt_CuentaUsuario.getText().equals("Ej: phndavid"))
				{

					txt_CuentaUsuario.setText("");
					txt_CuentaUsuario.setForeground(Color.BLACK);
				}

			}
		});
		panelLeft.add(txt_CuentaUsuario, constraints);

		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		panelLeft.add(lblPassword, constraints);

		password = new JPasswordField(20);
		password.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		constraints.gridx = 0;
		constraints.gridy = 6;
		panelLeft.add(password, constraints);

		JButton btnIniciarSesion = new JButton("     Iniciar Sesion      ");
		btnIniciarSesion.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		
		btnIniciarSesion.setActionCommand(INICIAR_SESION);
		btnIniciarSesion.addActionListener(this);
		btnIniciarSesion.setSize(50, 20);
		constraints.gridx = 0;
		constraints.gridy = 7;
		panelLeft.add(btnIniciarSesion, constraints);

		add(panelRight);
		add(panelLeft);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals(INICIAR_SESION))
		{
			try
			{
				String cuentaUsuario = txt_CuentaUsuario.getText();
				@SuppressWarnings("deprecation")
				String contrasena = password.getText();

				ventanaZChat.inicioSesion(cuentaUsuario, contrasena);

			}catch(Exception e2)
			{
				e2.getStackTrace();
			}
		}
	}


}
