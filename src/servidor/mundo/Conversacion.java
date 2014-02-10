package servidor.mundo;

import java.io.Serializable;

public class Conversacion implements Serializable {

	private Chater contacto;
	private String conversacion;
	
	public Conversacion(Chater contacto, String conversacion) {
		this.contacto = contacto;
		this.conversacion = conversacion;
	}

	public Chater getContacto() {
		return contacto;
	}

	public void setContacto(Chater contacto) {
		this.contacto = contacto;
	}

	public String getConversacion() {
		return conversacion;
	}

	public void setConversacion(String conversacion) {
		this.conversacion = conversacion;
	}
	
	public String toString()
	{
		return contacto.getNombre()+" "+contacto.getApellido();
	}
}
