/**
  * Name    : MainFrame.java
  * Project : JG2DE
  * Author  : Robert M Hubinsky <r.hubinsky@hotmail.com>
  * Created : 6.11.2012
  */
package engine.frame;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;

import engine.core.EngineCore;
import engine.objects.DrawingObject;

@SuppressWarnings("serial")
public class MainFrame extends JApplet implements KeyListener{
	DrawingObject _drawingObject;
	int _cubicSize = 600;
	EngineCore core;
	public MainFrame() {
		this.addKeyListener(this);
		this.setFocusable(true);
		requestFocusInWindow();
		_drawingObject = new 	DrawingObject();
		_drawingObject.setBounds(0, 0, _cubicSize,_cubicSize);
		_drawingObject.setMaximumSize(new Dimension(_cubicSize,_cubicSize));
		_drawingObject.setMinimumSize(new Dimension(300,600));
		this.getContentPane().add(_drawingObject);
		core = new EngineCore(_drawingObject,100);
		core.Initialize();
	}
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		core.KeyPressed(e);
	}
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
