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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class PanelRegistro extends JPanel implements ActionListener,
		FocusListener {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2980350179284751156L;
	
	public final static String FOCUS_CUENTA = "@Zchat.com";
	public final static String FOCUS_NOMBRE = "Nombre";
	public final static String FOCUS_APELLIDO = "Apellido";
	
	public final static String ACEPTAR = "Aceptar";
	public final static String CANCELAR = "Cancelar";

	private VentanaZChat ventanaZChat;
	
	private JTextField txt_CuentaUsuario;
	private JTextField txt_Nombre;
	private JTextField txt_Apellido;
	private JComboBox<String> jcb_Sexo;
	private JTextField txt_CorreoActual;
	private JTextField txt_FechaNacimiento;

	private JPasswordField txt_Contrasena;
	private JPasswordField txt_Confirmar_Contrasena;

	public PanelRegistro(VentanaZChat ventanaZChat)
	{
		this.ventanaZChat = ventanaZChat;

		setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		JLabel lbl_TituloCreaCuenta = new JLabel("Crea una cuenta de Zchat");
		lbl_TituloCreaCuenta.setFont(new Font("Segoe Print", Font.BOLD, 20));
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weighty = 0.001;
		add(lbl_TituloCreaCuenta, constraints);

		JPanel panelContenedor = new JPanel();
		panelContenedor.setLayout(new GridLayout(9, 2, 4, 4));

		JLabel lbl_CuentaUsuario = new JLabel("Cuenta usuario: ");
		lbl_CuentaUsuario.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		
		panelContenedor.add(lbl_CuentaUsuario);

		txt_CuentaUsuario = new JTextField();
		txt_CuentaUsuario.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_CuentaUsuario.setText(FOCUS_CUENTA);
		txt_CuentaUsuario.setForeground(Color.GRAY);
		txt_CuentaUsuario.addFocusListener(this);
		panelContenedor.add(txt_CuentaUsuario);
		
		JLabel lbl_Nombre = new JLabel("Nombre: ");
		lbl_Nombre.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		
		panelContenedor.add(lbl_Nombre);

		txt_Nombre = new JTextField();
		txt_Nombre.setText(FOCUS_NOMBRE);
		txt_Nombre.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Nombre.setForeground(Color.GRAY);
		txt_Nombre.addFocusListener(this);
		panelContenedor.add(txt_Nombre);

		JLabel lbl_Apellido = new JLabel("Apellido: ");
		lbl_Apellido.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		panelContenedor.add(lbl_Apellido);
		
		txt_Apellido = new JTextField();
		txt_Apellido.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Apellido.setText(FOCUS_APELLIDO);
		txt_Apellido.setForeground(Color.GRAY);
		txt_Apellido.addFocusListener(this);	
		panelContenedor.add(txt_Apellido);

		JLabel lbl_Contrasena = new JLabel("Contraseña: ");
		lbl_Contrasena.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		panelContenedor.add(lbl_Contrasena);

		txt_Contrasena = new JPasswordField();
		panelContenedor.add(txt_Contrasena);

		JLabel lbl_Confirma_Contrasena = new JLabel("Confirma tu contraseña: ");
		lbl_Confirma_Contrasena.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		
		panelContenedor.add(lbl_Confirma_Contrasena);

		txt_Confirmar_Contrasena = new JPasswordField();
		panelContenedor.add(txt_Confirmar_Contrasena);

		JLabel lbl_Sexo = new JLabel("Sexo:");
		lbl_Sexo.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		
		panelContenedor.add(lbl_Sexo);
		String[] sexo = { "Femenino", "Masculino", "Otro" };
		jcb_Sexo = new JComboBox<String>(sexo);
		jcb_Sexo.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		panelContenedor.add(jcb_Sexo);

		JLabel lbl_FechaNacimiento = new JLabel("Fecha de nacimiento:");
		lbl_FechaNacimiento.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		panelContenedor.add(lbl_FechaNacimiento);
		
		txt_FechaNacimiento = new JTextField();
		txt_FechaNacimiento.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		panelContenedor.add(txt_FechaNacimiento);
		

		JLabel lbl_CorreoActual = new JLabel("Direccion del correo actual:");
		lbl_CorreoActual.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		panelContenedor.add(lbl_CorreoActual);

		txt_CorreoActual = new JTextField();
		txt_CorreoActual.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		panelContenedor.add(txt_CorreoActual);

		JButton btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		btn_Cancelar.setActionCommand(CANCELAR);
		btn_Cancelar.addActionListener(this);
		panelContenedor.add(btn_Cancelar);

		JButton btn_Aceptar = new JButton("Aceptar");
		btn_Aceptar.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		btn_Aceptar.setActionCommand(ACEPTAR);
		btn_Aceptar.addActionListener(this);
		panelContenedor.add(btn_Aceptar);

		constraints.gridx = 0;
		constraints.gridy = 2;
		add(panelContenedor, constraints);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cuentaUsuario = txt_CuentaUsuario.getText();
		String nombreUsuario = txt_Nombre.getText();
		String apellidoUsuario = txt_Apellido.getText();
		@SuppressWarnings("deprecation")
        String contrasena = txt_Contrasena.getText();
		@SuppressWarnings("deprecation")
        String confirmarContrasena = txt_Confirmar_Contrasena.getText();
		String sexo = (String) jcb_Sexo.getSelectedItem();
		String correoActual = txt_CorreoActual.getText();
		String fechaNacimiento = txt_FechaNacimiento.getText();

		if (e.getActionCommand().equals(ACEPTAR)) {

			try {
				if ( (!cuentaUsuario.equals(FOCUS_CUENTA)) && (!nombreUsuario.equals(FOCUS_NOMBRE))
				        && (!apellidoUsuario.equals(FOCUS_APELLIDO)) && (!fechaNacimiento.equals("")) && (!correoActual.equals("")) && (!contrasena.equals("")) ) {
					if (contrasena.equals(confirmarContrasena)) {
						ventanaZChat.registroUsuario(cuentaUsuario, nombreUsuario, apellidoUsuario, contrasena, sexo, correoActual, fechaNacimiento);
						refrescarDatos();
					} else {
						JOptionPane.showMessageDialog(this, "Las contraseñas no son iguales, por favor verifique", "Atención usuario", JOptionPane.INFORMATION_MESSAGE);		
					}
				} else {
					JOptionPane.showMessageDialog(this, "Fantal campos por llenar", "Atención usuario", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

	}

	public void refrescarDatos()
	{
		
		txt_CuentaUsuario.setText(FOCUS_CUENTA);
		txt_CuentaUsuario.setForeground(Color.GRAY);
		
		txt_Nombre.setText(FOCUS_NOMBRE);
		txt_Nombre.setForeground(Color.GRAY);
		
		txt_Apellido.setText(FOCUS_APELLIDO);
		txt_Apellido.setForeground(Color.GRAY);
		
		txt_Contrasena.setText("");
		txt_Confirmar_Contrasena.setText("");
		txt_CorreoActual.setText("");
		txt_FechaNacimiento.setText("");
	}

	@Override
	public void focusGained(FocusEvent e)
	{

		if(e.getSource().equals(txt_CuentaUsuario)) {
			if(txt_CuentaUsuario.getText().equals(FOCUS_CUENTA))
			{
				txt_CuentaUsuario.setText("");
				txt_CuentaUsuario.setForeground(Color.BLACK);
			}
		}else if(e.getSource().equals(txt_Nombre)) {
			if(txt_Nombre.getText().equals(FOCUS_NOMBRE))
			{
				txt_Nombre.setText("");
				txt_Nombre.setForeground(Color.BLACK);
			}
		}else if(e.getSource().equals(txt_Apellido)) {
			if(txt_Apellido.getText().equals(FOCUS_APELLIDO))
			{
				txt_Apellido.setText("");
				txt_Apellido.setForeground(Color.BLACK);
			}
		}
		
	}

	@Override
	public void focusLost(FocusEvent e)
	{

		if(e.getSource().equals(txt_CuentaUsuario)) {
			if(txt_CuentaUsuario.getText().equals(""))
			{
				txt_CuentaUsuario.setText(FOCUS_CUENTA);
				txt_CuentaUsuario.setForeground(Color.GRAY);
			}
		}else if(e.getSource().equals(txt_Nombre)) {
			if(txt_Nombre.getText().equals(""))
			{
				txt_Nombre.setText(FOCUS_NOMBRE);
				txt_Nombre.setForeground(Color.GRAY);
			}
		}else if(e.getSource().equals(txt_Apellido)) {
			if(txt_Apellido.getText().equals(""))
			{
				txt_Apellido.setText(FOCUS_APELLIDO);
				txt_Apellido.setForeground(Color.GRAY);
			}
		}
		
	}

}
