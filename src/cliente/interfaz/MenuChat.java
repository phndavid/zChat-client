package cliente.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuChat extends JMenuBar implements ActionListener {

	
	private static final long serialVersionUID = -1921389783693630936L;
	public final static String CARGAR = "Abrir";
	public final static String GUARDAR ="Guardar";
	public final static String GUARDAR_COMO ="Guardar Como";
	public final static String SALIR = "Cerrar Sesion";
	
	private JMenu jm_Archivo;
	private JMenuItem jmi_Abrir;
	private JMenuItem jmi_Guardar;
	private JMenuItem jmi_GuardarComo;
	private JMenuItem jmi_MenuSalir;

	private JMenu jm_Preferencias;

	private JMenu jm_Ayuda;

	public MenuChat()
	{
		
		jm_Archivo = new JMenu();
		jm_Archivo.setText("Archivo");

		jm_Preferencias = new JMenu();
		jm_Preferencias.setText("Preferencias");

		jm_Ayuda = new JMenu();
		jm_Ayuda.setText("Ayuda");

		inicializarCargar();
		inicializarGuardar();
		inicializarGuardarComo();
		inicializarSalir();

		jm_Archivo.add(jmi_Abrir);
		jm_Archivo.add(jmi_Guardar);
		jm_Archivo.add(jmi_GuardarComo);
		jm_Archivo.add(jmi_MenuSalir);



		add(jm_Archivo);
		add(jm_Preferencias);
		add(jm_Ayuda);
	}


	private void inicializarGuardar() {
		
		jmi_Guardar = new JMenuItem();
		jmi_Guardar.setActionCommand(GUARDAR);
		jmi_Guardar.addActionListener(this);
		jmi_Guardar.setText("Guardar");
	}
	private void inicializarGuardarComo() {
		
		jmi_GuardarComo = new JMenuItem();
		jmi_GuardarComo.setActionCommand(GUARDAR_COMO);
		jmi_GuardarComo.addActionListener(this);
		jmi_GuardarComo.setText("Guardar Como");
	}
		
	private void inicializarCargar()
	{
		jmi_Abrir = new JMenuItem();
		jmi_Abrir.setActionCommand(CARGAR);
		jmi_Abrir.setText("Abrir                                                ");
		jmi_Abrir.addActionListener(this);
	}

	private void inicializarSalir()
	{
		jmi_MenuSalir = new JMenuItem();
		jmi_MenuSalir.setActionCommand(SALIR);
		jmi_MenuSalir.setText("Cerrar Sesion");
		jmi_MenuSalir.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{

	}
}