package cliente.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;

public class PanelMisDatos extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5927217653825657056L;
	
	private JTextField txt_Nombre;
	private JTextField txt_Apellido;
	private JTextField txt_Cuenta;
	private JTextField txt_Sexo;
	private JTextField txt_CorreoActual;
	private JTextField txt_FechaNacimiento;

	/**
	 * Create the panel.
	 */
	public PanelMisDatos() {
		setLayout(null);

		JButton btnNewButton = new JButton("Realizar Cambios");
		btnNewButton.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(26, 341, 146, 23);
		add(btnNewButton);

		JLabel lblMisDatos = new JLabel("Mis Datos");
		lblMisDatos.setFont(new Font("Segoe Print", Font.BOLD, 20));
		lblMisDatos.setBounds(290, 44, 139, 20);
		add(lblMisDatos);

		txt_Nombre = new JTextField();
		txt_Nombre.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Nombre.setEditable(false);
		txt_Nombre.setBounds(433, 127, 187, 20);
		add(txt_Nombre);
		txt_Nombre.setColumns(10);

		JLabel lblLabel = new JLabel(new ImageIcon("./data/user.png"));
		lblLabel.setBounds(10, 30, 300, 300);
		add(lblLabel);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblNombre.setBounds(376, 130, 59, 14);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblApellido.setBounds(376, 161, 59, 14);
		add(lblApellido);

		txt_Apellido = new JTextField();
		txt_Apellido.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Apellido.setEditable(false);
		txt_Apellido.setBounds(433, 158, 187, 20);
		add(txt_Apellido);
		txt_Apellido.setColumns(10);

		JLabel lblCuenta = new JLabel("Cuenta:");
		lblCuenta.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblCuenta.setBounds(377, 99, 46, 14);
		add(lblCuenta);

		txt_Cuenta = new JTextField();
		txt_Cuenta.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Cuenta.setEditable(false);
		txt_Cuenta.setBounds(433, 96, 187, 20);
		add(txt_Cuenta);
		txt_Cuenta.setColumns(10);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblSexo.setBounds(398, 192, 36, 14);
		add(lblSexo);

		txt_Sexo = new JTextField();
		txt_Sexo.setEditable(false);
		txt_Sexo.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Sexo.setBounds(433, 189, 187, 20);
		add(txt_Sexo);
		txt_Sexo.setColumns(10);

		JLabel lblCorreoActual = new JLabel("Correo actual:");
		lblCorreoActual.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblCorreoActual.setToolTipText("");
		lblCorreoActual.setBounds(346, 249, 99, 23);
		add(lblCorreoActual);

		txt_CorreoActual = new JTextField();
		txt_CorreoActual.setEditable(false);
		txt_CorreoActual.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_CorreoActual.setBounds(435, 252, 185, 20);
		add(txt_CorreoActual);
		txt_CorreoActual.setColumns(10);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblFechaDeNacimiento.setBounds(302, 223, 139, 14);
		add(lblFechaDeNacimiento);

		txt_FechaNacimiento = new JTextField();
		txt_FechaNacimiento.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_FechaNacimiento.setEditable(false);
		txt_FechaNacimiento.setBounds(434, 221, 186, 20);
		add(txt_FechaNacimiento);
		txt_FechaNacimiento.setColumns(10);

	}
	public void  refrescarMisDatos(String miCuenta,String nombre,String apellido,String sexo,String fechaNacimiento,String correoActual) {
		
		txt_Cuenta.setText(miCuenta);
		txt_Nombre.setText(nombre);
		txt_Apellido.setText(apellido);
		txt_Sexo.setText(sexo);
		txt_FechaNacimiento.setText(fechaNacimiento);
		txt_CorreoActual.setText(correoActual);
		
	}
	public JTextField getMiCuenta(){
		return txt_Cuenta;
	}
}
