package cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.BusinessBlackSteelSkin;

public class VentanaZChat extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4558461343955730203L;

	private HiloRecepcion hiloRecepcion;
	private PanelRegistro panelRegistro;
	private PanelLogIn panelLogIn;

	private JTabbedPane pestana;

	public VentanaZChat()
	{

		setLayout(new BorderLayout());
		setTitle("Letters app");
		setSize(700, 400);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		try
		{
			hiloRecepcion = new HiloRecepcion(this,null);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		panelRegistro = new PanelRegistro(this);
		panelLogIn = new PanelLogIn(this);

		pestana = new JTabbedPane();
		pestana.setFont(new Font("Segoe Print", Font.BOLD, 12));
		pestana.addTab("Iniciar Sesion", this.panelLogIn);
		pestana.addTab("Registro", this.panelRegistro);

		add(pestana, BorderLayout.CENTER);

		center();
	}

	private void center()
	{
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xEsquina = (screen.width - getWidth()) / 2;
		int yEsquina = (screen.height - getHeight()) / 2;
		setLocation(xEsquina, yEsquina);
	}
	// ---------------------------------------------------------------------------------------------------------
	// Metodos del hilo
	// ---------------------------------------------------------------------------------------------------------
	/**
	 * Metodo: InicioSesion Descripcion: este metodo permite iniciar sesion con
	 * una cuenta de usuario y la contrasena
	 * 
	 * @param cuentaUsuario
	 *            Nombre de la cuenta de usuario
	 * @param contrasena
	 *            Contrasena de la cuenta de usuario
	 * @throws Exception
	 */
	public void inicioSesion(String cuentaUsuario, String contrasena)
			throws Exception
	{
		hiloRecepcion.inicioSesion(cuentaUsuario, contrasena);
	}
	/**
	 * Metodo:Registro Usuario
	 * Descipcion: Este metodo permite registrar un nuevo usuario
	 * @param cuentaUsuario
	 * @param nombreUsuario
	 * @param apellidoUsuario
	 * @param contrasena
	 * @param sexoUsuario
	 * @param correActualUsuario
	 * @param fechaNacimiento
	 * @throws Exception
	 */
	public void registroUsuario(String cuentaUsuario, String nombreUsuario,
			String apellidoUsuario, String contrasena, String sexoUsuario,
			String correActualUsuario, String fechaNacimiento) throws Exception
	{
		hiloRecepcion.registroUsuario(cuentaUsuario, nombreUsuario,
				apellidoUsuario, contrasena, sexoUsuario, correActualUsuario,
				fechaNacimiento);
	}


	public void dispose()
	{
		hiloRecepcion.cerrarSesion();
		super.dispose();

	}

	public static void main(String[] args)
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
		VentanaZChat ventana = new VentanaZChat();
		ventana.setVisible(true);
	}
}
