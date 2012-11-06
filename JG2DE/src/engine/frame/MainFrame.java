/**
  * Name    : MainFrame.java
  * Project : JG2DE
  * Author  : Robert M Hubinsky <r.hubinsky@hotmail.com>
  * Created : 6.11.2012
  */
package engine.frame;

import javax.swing.JApplet;

import engine.core.EngineCore;
import engine.objects.DrawingObject;

public class MainFrame extends JApplet {
	DrawingObject _drawingObject;
	public MainFrame() {
		_drawingObject = new DrawingObject();
		_drawingObject.setBounds(0, 0, 500,500);
		this.getContentPane().add(_drawingObject);
		EngineCore core = new EngineCore(_drawingObject,100);
		core.Initialize();
	}

}
