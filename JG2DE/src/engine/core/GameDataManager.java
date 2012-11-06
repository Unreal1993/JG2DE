/**
  * Name    : GameDataManager.java
  * Project : JG2DE
  * Author  : Robert M Hubinsky <r.hubinsky@hotmail.com>
  * Created : 6.11.2012
  */
package engine.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.helpers.Vector2D;

/** Holds our game data */
public class GameDataManager {
	private GameState _state = GameState.InMenu;
	
	public GameDataManager() {
		
	}
	
	public BufferedImage drawGame(int width,int height) {
		BufferedImage _img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics g = _img.getGraphics();
			// Draw the game
			switch(_state) {
			case InGame:
				drawGame(g,width,height);
				break;
			case InMenu:
				drawMenu(g,width,height);
				break;
			}
		g.dispose();
		return _img;
	}
		
	
	private void drawMenu(Graphics g,int width,int height) 
	{
		Menu myMenu = new Menu();
		myMenu.Draw(g);
	}
	
	private void drawGame(Graphics g,int width,int height)
	{
		
	}	
	enum GameState{
		InGame,
		InMenu,
	}
	
	private class Menu{
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		public Menu() {
			for(int i =0;i<10;i++) {
				menuItems.add(new MenuItem("Test " + i,new Vector2D(170,40*i)));
			}
		}
		
		public void Draw(Graphics g){
			for(int i =0;i<menuItems.size();i++) {
				menuItems.get(i).Draw(g);
			}
		}
	}
	
	private class MenuItem{
		private Vector2D _position;
		private String _text;
		private Color _background = Color.DARK_GRAY;
		private Color _border = Color.WHITE;
		private Font _font = new Font("SansSerif", Font.BOLD, 14);
		private int _width = 150;
		public MenuItem(String text,Vector2D pos) {
			_position = pos;
			_text = text;
		}
		
		public void Draw(Graphics g) {
			Color lastColor = g.getColor();
			FontMetrics fontMetrics = g.getFontMetrics();
			g.setFont(_font);
			g.setColor(_background);
			g.fillRect((int)_position.get_x(),(int)_position.get_y(), _width, fontMetrics.getHeight()+10);
			g.setColor(_border);
			g.drawRect((int)_position.get_x(),(int)_position.get_y(), _width, fontMetrics.getHeight()+10);
			g.drawString(_text, (int)(_position.get_x() + (_width/2)-_text.length()*3.5),
						(int)_position.get_y()+fontMetrics.getHeight()+3);
			
			g.setColor(lastColor);
		}
	}
}
