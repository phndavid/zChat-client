package cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import servidor.mundo.Chater;

public class ListaAmigos extends JList<Chater> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5155563128728603373L;
	private DefaultListModel<Chater> dtModelListaAmigos;

	public ListaAmigos() {

		setLayout(new BorderLayout());
		setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		setModel(new DefaultListModel<Chater>());
	}

	public void refrescarListaDeAmigos(Object amigos) {
		dtModelListaAmigos = (DefaultListModel<Chater>) this.getModel();

		if (amigos instanceof ArrayList) {

			eliminarListaDeAmigos();
			ArrayList<Chater> amigos1 = (ArrayList<Chater>) amigos;
			Iterator<Chater> iteraAmigos = amigos1.iterator();
			while (iteraAmigos.hasNext()) {
				Chater amigo = (Chater) iteraAmigos.next();
				dtModelListaAmigos.addElement(amigo);
			}
		} else {

			dtModelListaAmigos.addElement((Chater) amigos);
		}
	}

	public void eliminarListaDeAmigos() {

		int filas = this.getModel().getSize();
		for (int i = 0; i < filas; i++) {
			if (dtModelListaAmigos != null)
				dtModelListaAmigos.remove(0);

		}
	}

	public void borrarDeListaDeAmigos(Chater amigo) {
		JList<Chater> l = this;
		dtModelListaAmigos.removeElement(amigo);
	}

}
