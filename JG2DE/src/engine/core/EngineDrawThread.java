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
	public GameDataManager gameManager;
	public int delay =1;
	/* (non-JavadoDc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while(shouldBeRunning) {
			drawingObject.customDraw(gameManager.drawGame(drawingObject.getWidth(), drawingObject.getHeight()));
			try {
				Thread.sleep(1000/delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
