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

import engine.helpers.Stopwatch;
import engine.helpers.Vector2D;

/** Holds our game data */
public class GameDataManager {
	private GameState _state = GameState.InMenu;
	private Menu gameMenu;
	private Font _mainFont = new Font("SansSerif", Font.BOLD, 30);
	private Font _versionFont = new Font("SansSerif", Font.BOLD, 9);
	private String _gameName = "-=Life Race=-";
	private String _versionString = "v0.0.1 - Extremly early test version . ";
	
	public GameDataManager(int width) {
		gameMenu = new Menu(width);
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
				case InOptions:
					break;
				case InCredits:
					drawCredits(g,width,height);
					break;
				case InIntro:
					break;
			}
		g.dispose();
		return _img;
	}
		
	/** Function that handles the overlay and draws menu */
	private void drawMenu(Graphics g,int width,int height) 
	{
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		int rand;
		g.setColor(Color.GRAY);
		for ( int i = 0;i<200;i++) {
			rand = (int) Math.round(Math.random()*width);
			g.drawLine(rand, 0, rand, height);
		}
		g.setColor(Color.GRAY);
		g.fillRect(width/2-220/2, -1, 220, height+1);
		g.setColor(Color.WHITE);
		g.drawRect(width/2-220/2, 0, 220, height);
		g.drawRect(width/2-220/2+3, 3, 214, height-6);
		gameMenu.Draw(g,width);
		g.setFont(_mainFont);
		FontMetrics metrics = g.getFontMetrics();
		g.drawString(_gameName,(int)((width/2)-_gameName.length()*7.5), metrics.getHeight()+20);
		g.setFont(_versionFont);
		g.drawString(_versionString, width/2-100, height-7);
	}
	/** Function handling drawing of the game . */
	private void drawGame(Graphics g,int width,int height)
	{

	}	
	/** Function handling drawing of the credits */
	private void drawCredits(Graphics g,int width,int height) {
			
	}
	/** Handles the keyboard input while in menu and game. */
	public void keyboardInputHandler(KeyEvent e){
		switch(_state) {
			case InGame:
			{
				switch(e.getKeyCode()) {
					case KeyEvent.VK_UP:
					case KeyEvent.VK_W:
					{
						break;
					}
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_A:
					{
						break;
					}
					case KeyEvent.VK_RIGHT:
					case KeyEvent.VK_D:
					{
						break;
					}
					case KeyEvent.VK_DOWN:
					case KeyEvent.VK_S:
					{
						break;
					}
					case KeyEvent.VK_SPACE:
					{
						break;
					}
					case KeyEvent.VK_ESCAPE:
						this._state = GameState.InMenu;
						break;
				}	
				break;
			}
			case InMenu:
			{
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
				case KeyEvent.VK_ENTER:
					switch(gameMenu.selectedMenuItem().get_text())
					{
						case "Start":
							_state = GameState.InGame;
							break;
						case "Reset":
							_state = GameState.InGame;
							// TODO : Add a reset for a game
							break;
						case "Credits":
							_state = GameState.InCredits;
							break;
						case "Exit":
							
							break;
					}
					break;
				case KeyEvent.VK_ESCAPE:
					this._state = GameState.InGame;
					break;
			}		
				break;
			}
			case InCredits:
			{
				switch(e.getKeyCode()) {
					case KeyEvent.VK_ESCAPE:
					{
						_state = GameState.InMenu;
						break;
					}
				}
				break;
			}
			case InIntro:
			{
				
				break;
			}
			case InOptions:
			{
				
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
		private int _growSize = 30;
		private int _lastWidth;
		private Vector2D _baseVector;
		public Menu(int width) {
			_lastWidth = width;
			createMenu(width);
		}
		private void createMenu(int width) {
			_lastWidth =width;
			_baseVector = null;
			_menuItems.clear();
			addMenuItem("Start");
			addMenuItem("Reset");
			addMenuItem("Credits");
			addMenuItem("Exit");
		}
		
		private void addMenuItem(String menuText) {
			if(_baseVector == null)
				_baseVector = new Vector2D(_lastWidth/2-150/2,120);
			else
				_baseVector = new Vector2D(_baseVector.get_x(),_baseVector.get_y()+(50));
			
			_menuItems.add(new MenuItem(menuText,_baseVector));
		}
		
		public MenuItem selectedMenuItem() {
			return _menuItems.get(selectedIndex);
		}
		
		public void Draw(Graphics g,int width){		
			if(_lastWidth != width) {
				createMenu(width);
			}
			for(int i =0;i<_menuItems.size();i++) {
				if(selectedIndex == i) {
					if(lastSelectedIndex == i) {
						if((_growVar <= _growSize) && _grow) {
							if(_growVar >= _growSize)
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
		private Color _selected = Color.GRAY;
		private Color _btnForeground = Color.BLACK;
		private int _width = 150;
		/** Font used */
		private Font _font = new Font("SansSerif", Font.BOLD, 14);
		public MenuItem(String text,Vector2D pos) {
			_position = pos;
			_text = text;
		}
		
		public void Draw(Graphics g,boolean selected,int grow) {
			Color lastColor = g.getColor();
			g.setFont(_font);
			FontMetrics fontMetrics = g.getFontMetrics();
			if(!selected) {
				g.setColor(_border);
				g.drawLine((int)_position.get_x()-15,
						   (int)_position.get_y()+( fontMetrics.getHeight()+10)/2-3,
						   (int)_position.get_x()+this._width+15,
						   (int)_position.get_y()+( fontMetrics.getHeight()+10)/2-3);
				g.drawLine((int)_position.get_x()-15,
						   (int)_position.get_y()+( fontMetrics.getHeight()+10)/2+3,
						   (int)_position.get_x()+this._width+15,
						   (int)_position.get_y()+( fontMetrics.getHeight()+10)/2+3);
				g.drawLine((int)_position.get_x()-32,
						   (int)_position.get_y()+( fontMetrics.getHeight()+10)/2,
						   (int)_position.get_x()+this._width+32,
						   (int)_position.get_y()+( fontMetrics.getHeight()+10)/2);
				g.setColor(_btnForeground);
				g.fillRect((int)_position.get_x(),
						   (int)_position.get_y(), 
						   _width,
						   fontMetrics.getHeight()+10);
				g.setColor(_background);
				g.fillRect((int)_position.get_x()+3,
						   (int)_position.get_y()+2, 
						   _width-5,
						   fontMetrics.getHeight()+10-3);
				g.setColor(_border);
				g.drawRect((int)_position.get_x(),
						   (int)_position.get_y(),
						   _width, fontMetrics.getHeight()+10);
				g.drawLine((int) _position.get_x() + 2,
						   (int) _position.get_y() + 2,
						   (int) _position.get_x() + 25,
						   (int) _position.get_y() + 2);
				
				g.drawLine((int) _position.get_x() + 2,
						   (int) _position.get_y() + 2,
						   (int) _position.get_x() + 2,
						   (int) _position.get_y() + 10);
				
				g.drawLine((int) _position.get_x() + 2,
						   (int) _position.get_y()+( fontMetrics.getHeight()+10)-2,
						   (int) _position.get_x() + 2,
						   (int) _position.get_y()+( fontMetrics.getHeight()+10)-10);
				
				g.drawLine((int) _position.get_x() + 2,
						   (int) _position.get_y()+( fontMetrics.getHeight()+10)-2,
						   (int) _position.get_x() + 25,
						   (int) _position.get_y()+( fontMetrics.getHeight()+10)-2);
				
				g.drawLine((int) _position.get_x() + this._width -2,
						   (int) _position.get_y() +2,
						   (int) _position.get_x() + this._width -25,
						   (int) _position.get_y() +2);
				
				g.drawLine((int) _position.get_x() +this._width - 2,
						   (int) _position.get_y() + 2,
						   (int) _position.get_x() +this._width - 2,
						   (int) _position.get_y() + 10);
				
				g.drawLine((int) _position.get_x() +this._width- 2,
						   (int) _position.get_y()+( fontMetrics.getHeight()+10)-2,
						   (int) _position.get_x() + this._width- 2,
						   (int) _position.get_y()+( fontMetrics.getHeight()+10)-10);
				
				g.drawLine((int) _position.get_x() +this._width- 2,
						   (int) _position.get_y()+( fontMetrics.getHeight()+10)-2,
						   (int) _position.get_x() +this._width- 25,
						   (int) _position.get_y()+( fontMetrics.getHeight()+10)-2);
				
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
				
				for(int i = 3;i<15;i = i+3) {
					g.drawLine((int)_position.get_x()-(grow/2)+i,
							   (int)_position.get_y()+i,
							   (int)_position.get_x()-(grow/2)+i,
							   (int)_position.get_y() + fontMetrics.getHeight()+10-i);
				}
				
				for(int i = 3;i<15;i = i+3) {
					g.drawLine((int)_position.get_x()+_width+(grow/2)-i,
							   (int)_position.get_y()+i,
							   (int)_position.get_x()+_width+(grow/2)-i,
							   (int)_position.get_y() + fontMetrics.getHeight()+10-i);
				}

			}
			
			g.setColor(lastColor);
		}

		/**
		 * @return String _text
		 */
		public String get_text() {
			return _text;
		}

		/**
		 * @param _text the _text to set
		 */
		public void set_text(String _text) {
			this._text = _text;
		}
	}
	
	class Game{
		private Stopwatch _gameTime = new Stopwatch("GameTime");
		
		Game(){
			
		}
		
		public void pause() {
			
		}
		public void resume() {
			
		}
	}
}
