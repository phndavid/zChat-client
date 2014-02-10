package cliente.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import servidor.mundo.Chater;

public class PanelBuscarAmigos extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8475860430682879742L;
	
	public final static String BUSCAR_AMISTAD = "Buscar amistad";
	public final static String ENVIAR_SOLICITUD = "Enviar solicitud";

	private PestanasPrincipales panelPestanas;

	private JTextField txt_BuscarAmistad;
	private JTextField txt_Cuenta;
	private JTextField txt_Nombre;
	private JTextField txt_Apellido;
	private JTextField txt_Sexo;
	private JTextField txt_FechaNacimiento;
	private JTextField txt_CorreoActual;

	
	public PanelBuscarAmigos(PestanasPrincipales panelPestanas)
	{
	
		this.panelPestanas = panelPestanas;
		setLayout(null);
		
		JLabel lblBuscarAmistades = new JLabel("Buscar Amigo");
		lblBuscarAmistades.setFont(new Font("Segoe Print", Font.BOLD, 20));
		lblBuscarAmistades.setBounds(290, 44, 139, 20);
		add(lblBuscarAmistades);
		
		txt_BuscarAmistad = new JTextField();
		txt_BuscarAmistad.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_BuscarAmistad.setText("Buscar Amistad");
		txt_BuscarAmistad.setForeground(Color.GRAY);
		txt_BuscarAmistad.setBounds(76, 90, 204, 20);
		txt_BuscarAmistad.setColumns(10);
		txt_BuscarAmistad.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e)
			{
				if(txt_BuscarAmistad.getText().equals(""))
				{
					txt_BuscarAmistad.setText("Buscar Amistad");
					txt_BuscarAmistad.setForeground(Color.GRAY);
				}
			}

			public void focusGained(FocusEvent e)
			{
				if(txt_BuscarAmistad.getText().equals("Buscar Amistad"))
				{
					txt_BuscarAmistad.setText("");
					txt_BuscarAmistad.setForeground(Color.BLACK);
				}

			}
		});
		add(txt_BuscarAmistad);
		
		
		JButton btnEnviarSolicitud = new JButton("Enviar Solicitud");
		btnEnviarSolicitud.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		btnEnviarSolicitud.setBounds(52, 342, 148, 23);
		btnEnviarSolicitud.setActionCommand(ENVIAR_SOLICITUD);
		btnEnviarSolicitud.addActionListener(this);
		add(btnEnviarSolicitud);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		btnBuscar.setBounds(290, 89, 83, 23);
		btnBuscar.setActionCommand(BUSCAR_AMISTAD);
		btnBuscar.addActionListener(this);
		add(btnBuscar);
		
		JLabel lblInformacionBasica = new JLabel("Informacion  Basica:");
		lblInformacionBasica.setForeground(new Color(0, 0, 0));
		lblInformacionBasica.setFont(new Font("Segoe Print", Font.BOLD, 14));
		lblInformacionBasica.setBounds(132, 124, 148, 20);
		add(lblInformacionBasica);
		
		txt_Nombre = new JTextField();
		txt_Nombre.setEditable(false);
		txt_Nombre.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Nombre.setBounds(185, 186, 188, 20);
		add(txt_Nombre);
		txt_Nombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblNombre.setBounds(101, 189, 59, 14);
		add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblApellido.setBounds(93, 226, 67, 14);
		add(lblApellido);
		
		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblSexo.setBounds(126, 251, 34, 14);
		add(lblSexo);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaNacimiento.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblFechaNacimiento.setBounds(27, 282, 148, 14);
		add(lblFechaNacimiento);
		
		txt_Apellido = new JTextField();
		txt_Apellido.setEditable(false);
		txt_Apellido.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Apellido.setBounds(185, 217, 188, 20);
		add(txt_Apellido);
		txt_Apellido.setColumns(10);
		
		txt_Sexo = new JTextField();
		txt_Sexo.setEditable(false);
		txt_Sexo.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Sexo.setBounds(185, 248, 188, 20);
		add(txt_Sexo);
		txt_Sexo.setColumns(10);
		
		txt_FechaNacimiento = new JTextField();
		txt_FechaNacimiento.setEditable(false);
		txt_FechaNacimiento.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_FechaNacimiento.setBounds(185, 279, 188, 20);
		add(txt_FechaNacimiento);
		txt_FechaNacimiento.setColumns(10);
		
		JLabel lblImagen = new JLabel(new ImageIcon("./data/userAmistad.png"));
		lblImagen.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblImagen.setBounds(418, 130, 200, 200);
		add(lblImagen);
		
		txt_Cuenta = new JTextField();
		txt_Cuenta.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Cuenta.setEditable(false);
		txt_Cuenta.setBounds(185, 155, 188, 20);
		add(txt_Cuenta);
		txt_Cuenta.setColumns(10);
		
		JLabel lblCuenta = new JLabel("Cuenta:");
		lblCuenta.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblCuenta.setBounds(107, 161, 65, 14);
		add(lblCuenta);
		
		txt_CorreoActual = new JTextField();
		txt_CorreoActual.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_CorreoActual.setEditable(false);
		txt_CorreoActual.setBounds(185, 310, 188, 20);
		add(txt_CorreoActual);
		txt_CorreoActual.setColumns(10);
		
		JLabel lblCorreoActual = new JLabel("Correo Actual:");
		lblCorreoActual.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblCorreoActual.setBounds(67, 317, 99, 14);
		add(lblCorreoActual);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String cuentaUsuario = txt_BuscarAmistad.getText();
		if(e.getActionCommand().equals(BUSCAR_AMISTAD)) 
			panelPestanas.buscarAmistadPorCuenta(cuentaUsuario);
		
		else if(e.getActionCommand().equals(ENVIAR_SOLICITUD))
		{
			Chater c = panelPestanas.buscar(cuentaUsuario);
			String cuentaCliente = panelPestanas.getCliente().getCuenta();
			if(c == null && !cuentaUsuario.equals(cuentaCliente))
			panelPestanas.agregarContacto(cuentaUsuario);			
			else
				JOptionPane.showMessageDialog(this, "El contacto que deseas agregar ya existe en tu lista de amigos o eres tú");
		}		
	}

	public void refrescarInformacionBasicaDeAmistad(String miCuenta, String nombre, String apellido, String sexo, String fechaNacimiento,
	        String correoActual) {

		txt_Cuenta.setText(miCuenta);
		txt_Nombre.setText(nombre);
		txt_Apellido.setText(apellido);
		txt_Sexo.setText(sexo);
		txt_FechaNacimiento.setText(fechaNacimiento);
		txt_CorreoActual.setText(correoActual);

	}
}
