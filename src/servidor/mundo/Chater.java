package servidor.mundo;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class Chater implements Serializable, Comparable<Chater>{

	private String cuenta;
	private String nombre;
	private String apellido;
	private String sexo;
	private String fechaNacimiento;
	private String correoActual;
	private String contrasena;
	
	private boolean estadoConexion;
	
	private Socket canal;
	private ObjectOutputStream out;
	private ArrayList<Chater> contactos;
	private ArrayList<Chater> contactosConectados;
	private ArrayList<Peticion> peticiones;
	private ArrayList<Conversacion> conversaciones;
	private Chater chaterHijo;
	
	
	public Chater(String cuenta,String nombre,String apellido, String contrasena,String sexo, String correoActual,String fechaNacimiento, Socket canal, ObjectOutputStream out,boolean tipoPadre)
	{
		
		contactos = new ArrayList<Chater>();
		contactosConectados = new ArrayList<Chater>();
		peticiones = new ArrayList<Peticion>();
		conversaciones = new ArrayList<Conversacion>();
		this.estadoConexion = false;
		this.cuenta = cuenta;
		this.nombre = nombre;
		this.apellido = apellido;
		this.contrasena = contrasena;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.correoActual = correoActual;
		this.canal = canal;
		this.out = out;
		if(tipoPadre)
			chaterHijo = new Chater(cuenta, nombre, apellido, contrasena, sexo, correoActual, fechaNacimiento, null, null ,false);
		
	}
	
	public ObjectOutputStream getOut() {
		return out;
	}

	public void setOut(ObjectOutputStream out) {
		this.out = out;
	}

	public ArrayList<String> getInformacion()
	{
		ArrayList<String> informacion = new ArrayList<String>();
		informacion.add(cuenta);
		informacion.add(nombre);
		informacion.add(apellido);
		informacion.add(sexo);
		informacion.add(fechaNacimiento);
		informacion.add(correoActual);
		informacion.add(contrasena);
		
		return informacion;
	}
	
	public ArrayList<Chater> getContactosConectadosHijos()
	{
		ArrayList<Chater> hijos = new ArrayList<Chater>();
		for(Chater chater: contactosConectados)
			hijos.add(chater.getHijo());
		return hijos;
	}
	
	public ArrayList<Conversacion> getConversacionesHijos()
	{
		ArrayList<Conversacion> hijos = new ArrayList<Conversacion>();
		for(Conversacion conversacion: conversaciones)
		{
			Chater padre = conversacion.getContacto();
			Conversacion c = new Conversacion(padre.getHijo(), conversacion.getConversacion());
			hijos.add(c);
		}
		return hijos;
	}
	
	public ArrayList<Chater> getContactosHijos()
	{
		ArrayList<Chater> hijos = new ArrayList<Chater>();
		for(Chater chater: contactos)
			hijos.add(chater.getHijo());
		return hijos;
	}
	
	public void guargarEnConversacion(String cuentaChater, String mensaje)
	{
		for(Conversacion c: conversaciones)
		{
			String cuenta = c.getContacto().getCuenta();
			if(cuenta.equals(cuentaChater))
			{
				c.setConversacion(c.getConversacion() + mensaje);
				break;
			}
		}
	}
	
	public Chater getHijo()
	{
		return chaterHijo;
	}
	
	public String getCuenta()
	{
		return cuenta;
	}

	public void setCuenta(String cuenta)
	{
		this.cuenta = cuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public Socket getCanal() {
		return canal;
	}
	
	public void setSocket(Socket canal)
	{
		this.canal = canal;
	}
	
	public String getContrasena()
	{
		return contrasena;
	}
	public String getFechaNacimiento()
	{
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento)
	{
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getApellido()
	{
		return apellido;
	}

	public void setApellido(String apellido)
	{
		this.apellido = apellido;
	}

	public String getSexo()
	{
		return sexo;
	}

	public void setSexo(String sexo)
	{
		this.sexo = sexo;
	}

	public String getCorreoActual()
	{
		return correoActual;
	}

	public void setCorreoActual(String correoActual)
	{
		this.correoActual = correoActual;
	}

	public ArrayList<Chater> getContactos() {
		return contactos;
	}

	public ArrayList<Chater> getContactosConectados() {
		return contactosConectados;
	}
	

	public ArrayList<Peticion> getPeticiones()
	{
		return peticiones;
	}

	public void setPeticionesRecibidas(ArrayList<Peticion> peticionesRecibidas)
	{
		this.peticiones = peticionesRecibidas;
	}

	public Chater buscarContactoPorCuenta(String cuenta)
	{
		for(Chater contactoConectado: contactos)
		{
			if(contactoConectado.getCuenta().equals(cuenta))
			{
				return contactoConectado;
			}
		}
		return null;
	}
	
	public ArrayList<Conversacion> getConversaciones() {
		return conversaciones;
	}

	public void setConversaciones(ArrayList<Conversacion> conversaciones) {
		this.conversaciones = conversaciones;
	}

	public Chater buscarContactoPorNombre(String nombre)
	{
		for(Chater contacto: contactosConectados)
		{
			if(contacto.getNombre().equals(nombre))
			{
				return contacto;
			}
		}
		return null;		
	}

	public boolean isEstadoConexion() {
		return estadoConexion;
	}

	public void setEstadoConexion(boolean estadoConexion) {
		this.estadoConexion = estadoConexion;
	}

	@Override
	public int compareTo(Chater c) {
		int res = cuenta.compareTo(c.getCuenta());
		return res;
	}
	
	public String toString()
	{
		return nombre+" "+apellido;
	}

	public Conversacion buscarConversacion(String cuenta) {

		Conversacion conversacion = null;
		for(Conversacion conver: conversaciones){
			if(conver.getContacto().getCuenta().equals(cuenta)){
				conversacion = conver;
			}
		}
		return conversacion;
	}
}
