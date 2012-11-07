/**
  * Name    : DrawingObject.java
  * Project : JG2DE
  * Author  : Robert M Hubinsky <r.hubinsky@hotmail.com>
  * Created : 6.11.2012
  */
package engine.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DrawingObject extends JLabel{
	private BufferedImage _img;
	
	public void paint(Graphics g) {
		// Pass graphics object to super construct
		super.paint(g);
		// Draw our image
		if(_img != null)
			g.drawImage(_img, 0, 0, null);
	}
	/** Draws the image with graphics object */
	public void customDraw(BufferedImage img) {
		_img = img;
		this.repaint();
	}
}
