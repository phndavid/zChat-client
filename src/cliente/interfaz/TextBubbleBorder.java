package cliente.interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.AbstractBorder;

public class TextBubbleBorder extends AbstractBorder {

	
	private Color color;
	private int thickness = 4;
	private int radii = 8;
	private int pointerSize = 7;
	private Insets insets = null;
	private BasicStroke stroke = null;
	private int strokePad;
	private int pointerPad = 4;
	RenderingHints hints;

	TextBubbleBorder(Color color) {
		
	
		new TextBubbleBorder(color, 4, 8, 7);
	}

	TextBubbleBorder(Color color, int thickness, int radii, int pointerSize) {
		
		this.thickness = thickness;
		this.radii = radii;
		this.pointerSize = pointerSize;
		this.color = color;

		stroke = new BasicStroke(thickness);
		strokePad = thickness / 2;

		hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int pad = radii + strokePad;
		int bottomPad = pad + pointerSize + strokePad;
		insets = new Insets(pad, pad, bottomPad, pad);
	}

	@Override
	public Insets getBorderInsets(Component c) {
		return insets;
	}

	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		return getBorderInsets(c);
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

		Graphics2D g2 = (Graphics2D) g;

		int bottomLineY = height - thickness - pointerSize;

		RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(0 + strokePad, 0 + strokePad, width - thickness, bottomLineY, radii, radii);

		Polygon pointer = new Polygon();

		// left point
		pointer.addPoint(strokePad + radii + pointerPad, bottomLineY);
		// right point
		pointer.addPoint(strokePad + radii + pointerPad + pointerSize, bottomLineY);
		// bottom point
		pointer.addPoint(strokePad + radii + pointerPad + (pointerSize / 2), height - strokePad);
		Area area = new Area(bubble);
		area.add(new Area(pointer));
		g2.setRenderingHints(hints);

		Area spareSpace = new Area(new Rectangle(0, 0, width, height));
		spareSpace.subtract(area);
		g2.setClip(spareSpace);
		g2.clearRect(0, 0, width, height);
		g2.setClip(null);

		g2.setColor(color);
		g2.setStroke(stroke);
		g2.draw(area);
	}

	public JLabel agregarTextoCliente(String mensaje,PanelConversacion panelConversacion, int ancho,int posy,int alto, int numLineas) {
			
				JLabel yo = new JLabel(mensaje);
				
//				yo.setEditable(false);
				yo.setBounds((panelConversacion.getWidth() - ancho) - 20, posy, ancho, (alto * numLineas));
				yo.setBorder(new TextBubbleBorder(new Color(0, 162, 232), 2, 4, 0));
				yo.setOpaque(true);
				yo.setBackground(new Color(181, 230, 29));
				yo.setForeground(Color.WHITE);
		
				return yo;
	} 
	public JLabel agregarTextoAmigo(String mensaje, PanelConversacion panelConversacion,int ancho,int posy,int alto,int numLineas) {;
		
				JLabel otro = new JLabel(mensaje);
//				otro.setEditable(false);
				otro.setBounds(panelConversacion.getX() + 10, posy, ancho, (alto * numLineas));
				otro.setBorder(new TextBubbleBorder(new Color(181, 230, 29), 2, 4, 0));
				otro.setOpaque(true);
				otro.setBackground(new Color(0, 162, 232));
				otro.setForeground(Color.WHITE);
				panelConversacion.add(otro);
			
				return otro;
	}


}