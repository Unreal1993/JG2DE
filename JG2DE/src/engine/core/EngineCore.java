/**
 * Name    : EngineCore.java
 * Project : JG2DE
 * Author  : Robert M Hubinsky <r.hubinsky@hotmail.com>
 * Created : 6.11.2012
 */
package engine.core;

import java.awt.event.KeyEvent;

import engine.objects.DrawingObject;

public class EngineCore {
	/** How many times per second is the picture in buffer updated */
	private int _fpsLimit = 60;
	/** Holds whether or not the engine has been initialized and is running */
	private boolean _running = false;
	/** Holds the drawing object the engine will draw on */
	private DrawingObject _drawingObject;
	/** Entity with prefferences on drawing */
	private EngineDrawThread _drawingThread;
	/** Draws onto the DrawingObject */
	private Thread _threadDrawing;
	/** Holds out game data for current game engine */
	private GameDataManager _gDataManager;
	
	/** Creates engine core with default 60 FPS limit and 100 updates/s */
	public EngineCore(DrawingObject drawingObject) {
		_drawingObject = drawingObject;
	}
	/** Creates engine core with default 100 updates/s*/
	public EngineCore(DrawingObject drawingObject, int fpsLimit) {
		_drawingObject = drawingObject;
		_fpsLimit = fpsLimit;
	}

	/** Intializes the engine (Starts update and drawing threads) */
	public void Initialize() {
		// Start update & drawing thread here ( Dont forget to check for refference . )
		_gDataManager = new GameDataManager();
		_drawingThread = new EngineDrawThread();
		_drawingThread.shouldBeRunning = true;
		_drawingThread.delay = _fpsLimit;
		_drawingThread.drawingObject = _drawingObject;
		_drawingThread.gameManager = _gDataManager;
		_threadDrawing = new Thread(_drawingThread,"Drawing");
		_threadDrawing.start();
		
	}
	/** Stops engine and its working threads */
	public void Stop() {
		_threadDrawing.stop();
	}
	
	public void KeyPressed(KeyEvent e) {
		_gDataManager.keyboardInputHandler(e);
	}

	/**
	 * @return int _fpsLimit
	 */
	public int get_fpsLimit() {
		return _fpsLimit;
	}

	/**
	 * @param _fpsLimit
	 *            the _fpsLimit to set
	 */
	public void set_fpsLimit(int _fpsLimit) {
		this._fpsLimit = _fpsLimit;
	}

	/**
	 * @return boolean _running
	 */
	public boolean is_running() {
		return _running;
	}

	/**
	 * @return DrawingObject _drawingObject
	 */
	public DrawingObject get_drawingObject() {
		return _drawingObject;
	}

	/**
	 * @return EngineDrawThread _drawingThread
	 */
	public EngineDrawThread get_drawingThread() {
		return _drawingThread;
	}

	/**
	 * @return GameDataManager _gDataManager
	 */
	public GameDataManager get_gDataManager() {
		return _gDataManager;
	}
}
