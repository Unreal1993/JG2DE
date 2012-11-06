/**
  * Name    : EngineDrawThread.java
  * Project : JG2DE
  * Author  : Robert M Hubinsky <r.hubinsky@hotmail.com>
  * Created : 6.11.2012
  */
package engine.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import engine.helpers.Vector2D;
import engine.objects.DrawingObject;

public class EngineDrawThread implements Runnable{

	public boolean shouldBeRunning ;
	public DrawingObject drawingObject;
	public int delay =1;
	private BufferedImage _img = new BufferedImage(600,600,BufferedImage.TYPE_INT_ARGB);
	/* (non-JavadoDc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		Graphics g = _img.getGraphics();
		int i = 0;
		while(shouldBeRunning) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, drawingObject.getWidth(), drawingObject.getHeight());
			g.setColor(Color.BLACK);
			g.drawRect(i, i, 20, 20);
			drawingObject.customDraw(_img);
			i++;
			try {
				Thread.sleep(1000/delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
