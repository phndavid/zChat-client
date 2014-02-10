package cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import servidor.mundo.Peticion;

public class PanelSolicitudes extends JPanel implements ActionListener, ListSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4811646400884665002L;

	private PestanasPrincipales panelPestanas;

	public final static String ACEPTAR = "Aceptar";
	public final static String RECHAZAR = "Rechazar";

	private JTextField txt_Cuenta;
	private JTextField txt_Nombre;
	private JTextField txt_Apellido;
	private JTextField txt_Sexo;
	private JTextField txt_FechaNacimiento;
	private JTextField txt_CorreoActual;
	private JList<Peticion> listaSolicitudes;
	private DefaultListModel<Peticion> dtModeloUsuarios;


	public PanelSolicitudes(PestanasPrincipales panelPestanas) {

		this.panelPestanas = panelPestanas;
		setLayout(null);

		JLabel lblTitulo = new JLabel("Mis Solicitudes");
		lblTitulo.setFont(new Font("Segoe Print", Font.BOLD, 20));
		lblTitulo.setBounds(290, 44, 152, 20);
		add(lblTitulo);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		btnAceptar.setBounds(515, 309, 100, 23);
		btnAceptar.setActionCommand(ACEPTAR);
		btnAceptar.addActionListener(this);
		add(btnAceptar);

		JButton btnRechazar = new JButton("Rechazar");
		btnRechazar.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		btnRechazar.setBounds(380, 309, 100, 23);
		btnRechazar.setActionCommand(RECHAZAR);
		btnRechazar.addActionListener(this);
		add(btnRechazar);

		txt_Nombre = new JTextField();
		txt_Nombre.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Nombre.setBounds(449, 143, 179, 20);
		add(txt_Nombre);
		txt_Nombre.setColumns(10);

		txt_Apellido = new JTextField();
		txt_Apellido.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Apellido.setBounds(449, 174, 179, 20);
		add(txt_Apellido);
		txt_Apellido.setColumns(10);

		txt_Sexo = new JTextField();
		txt_Sexo.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Sexo.setBounds(449, 205, 179, 20);
		add(txt_Sexo);
		txt_Sexo.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblNombre.setBounds(391, 149, 65, 14);
		add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblApellido.setBounds(391, 180, 65, 14);
		add(lblApellido);

		JLabel lblSexo = new JLabel("Sexo:");
		lblSexo.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblSexo.setBounds(412, 211, 44, 14);
		add(lblSexo);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new TitledBorder("Solicitudes de amistad"));
		panel.setBounds(10, 95, 292, 237);

		listaSolicitudes = new JList<Peticion>();
		listaSolicitudes.setBounds(126, 156, 193, -63);
		listaSolicitudes.addListSelectionListener(this);
		listaSolicitudes.setModel(new DefaultListModel<Peticion>());
		listaSolicitudes.setBackground(Color.WHITE);
		listaSolicitudes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollpane = new JScrollPane(listaSolicitudes);
		scrollpane.setSize(350, 400);
		panel.add(scrollpane, BorderLayout.CENTER);
		add(panel);

		txt_Cuenta = new JTextField();
		txt_Cuenta.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_Cuenta.setBounds(449, 109, 179, 20);
		add(txt_Cuenta);
		txt_Cuenta.setColumns(10);

		JLabel lblCuenta = new JLabel("Cuenta:");
		lblCuenta.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblCuenta.setBounds(401, 111, 55, 14);
		add(lblCuenta);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblFechaDeNacimiento.setBounds(313, 239, 128, 14);
		add(lblFechaDeNacimiento);

		txt_FechaNacimiento = new JTextField();
		txt_FechaNacimiento.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_FechaNacimiento.setBounds(449, 236, 179, 20);
		add(txt_FechaNacimiento);
		txt_FechaNacimiento.setColumns(10);

		JLabel lblCorreaActual = new JLabel("Correa actual:");
		lblCorreaActual.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		lblCorreaActual.setBounds(362, 273, 94, 14);
		add(lblCorreaActual);

		txt_CorreoActual = new JTextField();
		txt_CorreoActual.setFont(new Font("Segoe Print", Font.PLAIN, 12));
		txt_CorreoActual.setBounds(449, 267, 179, 20);
		add(txt_CorreoActual);
		txt_CorreoActual.setColumns(10);

		txt_Apellido.setEditable(false);
		txt_CorreoActual.setEditable(false);
		txt_Cuenta.setEditable(false);
		txt_FechaNacimiento.setEditable(false);
		txt_Nombre.setEditable(false);
		txt_Sexo.setEditable(false);

	}

	public void refrescarListaDePeticiones(Object peticiones) {

		dtModeloUsuarios = (DefaultListModel<Peticion>) listaSolicitudes.getModel();
		if (peticiones instanceof ArrayList) {
			eliminarListaDeUsuarios();
			Iterator<Peticion> iteraPeticion = ((ArrayList<Peticion>) peticiones).iterator();
			while (iteraPeticion.hasNext()) {
				Peticion peticion = iteraPeticion.next();
				dtModeloUsuarios.addElement(peticion);
			}
		} else {
			if(!dtModeloUsuarios.contains((Peticion)peticiones))
			dtModeloUsuarios.addElement((Peticion) peticiones);
		}

	}
	
	public void borrarElementoDeLista()
	{
		Peticion peticion = listaSolicitudes.getSelectedValue();
		dtModeloUsuarios.removeElement(peticion);
	}

	public void eliminarListaDeUsuarios() {

		int filas = listaSolicitudes.getModel().getSize();
		for (int i = 0; i < filas; i++) {
			if (dtModeloUsuarios != null)
				dtModeloUsuarios.remove(0);

		}
	}

	public void refrescarInfoDePeticion(String cuenta, String nombre, String apellido, String sexo, String fechaNacimiento,
	        String correoActual) {

		txt_Cuenta.setText(cuenta);
		txt_Nombre.setText(nombre);
		txt_Apellido.setText(apellido);
		txt_Sexo.setText(sexo);
		txt_FechaNacimiento.setText(fechaNacimiento);
		txt_CorreoActual.setText(correoActual);

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {

		Peticion p = listaSolicitudes.getSelectedValue();
		if (p != null) {
			ArrayList<String> info = p.getInformacion();
			String cuenta = info.get(0);
			String nombre = info.get(1);
			String apellido = info.get(2);
			String sexo = info.get(3);
			String fechaNacimiento = info.get(4);
			String correoActual = info.get(5);
			refrescarInfoDePeticion(cuenta, nombre, apellido, sexo, fechaNacimiento, correoActual);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String cuentaResponde = txt_Cuenta.getText();
		if ((cuentaResponde != null) && (!cuentaResponde.equals(""))) {

			if (e.getActionCommand().equals(ACEPTAR)){
				panelPestanas.responderPeticion(cuentaResponde, true);
				refrescarInfoDePeticion("", "", "", "", "", "");
				borrarElementoDeLista();
				JOptionPane.showMessageDialog(this, "Se a agregado " + cuentaResponde + " a tu lista de amigos", "Felicidades", JOptionPane.INFORMATION_MESSAGE);
						
			}else if (e.getActionCommand().equals(RECHAZAR)) {
				panelPestanas.responderPeticion(cuentaResponde, false);
				refrescarInfoDePeticion("", "", "", "", "", "");
				borrarElementoDeLista();
				JOptionPane.showMessageDialog(this, "Has rechazado el contacto: " + cuentaResponde);
				
			}

		} 
		else 
		{
			JOptionPane.showMessageDialog(this, "Selecciona una solicitud", "Atencion usuario", JOptionPane.WARNING_MESSAGE);
		}
	}
}
