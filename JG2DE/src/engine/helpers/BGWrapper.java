/**
  * Name    : BGWrapper.java
  * Project : JG2DE
  * Author  : Robert M Hubinsky <r.hubinsky@hotmail.com>
  * Created : 9.11.2012
  */
package engine.helpers;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Font;

/** Cubic wrapper for simple backgrounds */
public class BGWrapper {
	private Color _bgColor;	
	private ArrayList<String> _textinBackground = new ArrayList<String>();
	private Font font = new Font("Times new Roman",Font.BOLD,12);
	public BGWrapper(Color backgroundColor) {
		_bgColor = backgroundColor;
	}
	/** Adds a line of text to the background overlay .  */
	public void addText(String text) {
		_textinBackground.add(text);
	}
	/** Clears the text used for drawing over the background */
	public void clearText() {
		_textinBackground.clear();
	}
	/** Handles the drawing of this element .  */
	public void Draw(Graphics g) {
		
	}
}
