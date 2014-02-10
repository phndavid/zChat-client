package servidor.mundo;

import java.io.Serializable;
import java.util.ArrayList;

public class Peticion implements Serializable {

	private ArrayList<String> informacion;
	private String cuentaResponde;
	
	public Peticion(ArrayList<String> informacion, String cuentaResponde)
	{
		this.informacion = informacion;
		this.cuentaResponde = cuentaResponde;
	}

	public ArrayList<String> getInformacion() {
		return informacion;
	}

	public void setInformacion(ArrayList<String> informacion) {
		this.informacion = informacion;
	}

	public String getCuentaResponde() {
		return cuentaResponde;
	}

	public void setCuentaResponde(String cuentaResponde) {
		this.cuentaResponde = cuentaResponde;
	}
	
	public String toString()
	{
		return informacion.get(0);
	}
}
