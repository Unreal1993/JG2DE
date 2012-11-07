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
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import engine.helpers.Vector2D;

/** Holds our game data */
public class GameDataManager {
	private GameState _state = GameState.InMenu;
	private Menu gameMenu = new Menu();
	
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
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);

		gameMenu.Draw(g);
	}
	
	private void drawGame(Graphics g,int width,int height)
	{
		
	}	
	
	public void keyboardInputHandler(KeyEvent e){
		if(_state == GameState.InGame) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
			{
				break;
			}
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
			{
				break;
			}
			case KeyEvent.VK_ESCAPE:
				this._state = GameState.InMenu;
				break;
		}	
		}else if(_state == GameState.InMenu) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_UP:
				case KeyEvent.VK_W:
				{
					if(gameMenu.selectedIndex >0) {
						gameMenu.selectedIndex--;
					}else {
						gameMenu.selectedIndex = gameMenu._menuItems.size()-1;
					}
					break;	
				}
				case KeyEvent.VK_DOWN:
				case KeyEvent.VK_S:
				{
					if(gameMenu.selectedIndex < gameMenu._menuItems.size()-1) {
						gameMenu.selectedIndex++;
					}else {
						gameMenu.selectedIndex = 0;
					}
					break;	
				}
				case KeyEvent.VK_ESCAPE:
					this._state = GameState.InGame;
					break;
			}				
		}
	}
	
	enum GameState{
		InIntro,
		InGame,
		InMenu,
		InCredits,
		InOptions,
	}
	
	private class Menu{
		public ArrayList<MenuItem> _menuItems = new ArrayList<MenuItem>();
		public int selectedIndex = 0;
		public int lastSelectedIndex =0;
		private int _growVar = 0;
		private boolean _grow = true;
		public Menu() {
			for(int i =0;i<10;i++) {
				_menuItems.add(new MenuItem("Test " + i,new Vector2D(170,10+(50*i))));
			}
		}
		
		public void Draw(Graphics g){
			BufferedImage ImgRotate = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
			
			for(int i =0;i<_menuItems.size();i++) {
				if(selectedIndex == i) {
					if(lastSelectedIndex == i) {
						if((_growVar <= 50) && _grow) {
							if(_growVar >= 50)
								_grow = false;
							_growVar++;
						}else if(_growVar >= 0){
							if(_growVar <= 1)
								_grow = true;
							_growVar--;
						}
						_menuItems.get(i).Draw(g,true,_growVar);
					}else {
						lastSelectedIndex = i;
						_growVar = 0;
					}
				}
				else
					_menuItems.get(i).Draw(g,false,0);
			}
		}
	}

	
	private class MenuItem{
		private Vector2D _position;
		private String _text;
		private Color _background = Color.DARK_GRAY;
		private Color _border = Color.WHITE;
		private Color _selected = Color.ORANGE;
		private Font _font = new Font("SansSerif", Font.BOLD, 14);
		private int _width = 150;
		public MenuItem(String text,Vector2D pos) {
			_position = pos;
			_text = text;
		}
		
		public void Draw(Graphics g,boolean selected,int grow) {
			Color lastColor = g.getColor();
			FontMetrics fontMetrics = g.getFontMetrics();
			g.setFont(_font);
			if(!selected) {
				g.setColor(_background);
				g.fillRect((int)_position.get_x(),
						   (int)_position.get_y(), 
						   _width,
						   fontMetrics.getHeight()+10);
				g.setColor(_border);
				g.drawRect((int)_position.get_x(),
						   (int)_position.get_y(),
						   _width, fontMetrics.getHeight()+10);
				g.drawString(_text,
							(int)(_position.get_x() + (_width/2)-_text.length()*3.5),
							(int)_position.get_y()+fontMetrics.getHeight()+3);
			}else {
				g.setColor(_selected);
				g.fillRect((int)_position.get_x()-(grow/2),
						   (int)_position.get_y(),
						   _width+grow,
						   fontMetrics.getHeight()+10);
				g.setColor(_border);
				g.drawRect((int)_position.get_x()-(grow/2),
						   (int)_position.get_y(),
						   _width+grow, 
						   fontMetrics.getHeight()+10);
				g.drawString(_text,
						    (int)(_position.get_x() + (_width/2)-_text.length()*3.5),
						    (int)_position.get_y()+fontMetrics.getHeight()+3);
			}
			
			g.setColor(lastColor);
		}
	}
}
