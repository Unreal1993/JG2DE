/**
  * Name    : Stopwatch.java
  * Project : JG2DE
  * Author  : Robert M Hubinsky <r.hubinsky@hotmail.com>
  * Created : 8.11.2012
  */
package engine.helpers;
/** Helper class that will be used for gametime */
public class Stopwatch {
	private String _watchName;
	private long _sPassed;
	private long _timeStarted;
	private long _timeFinished;
	private boolean _running = false;

	public Stopwatch(String watchName){
		_watchName = watchName;
	}
	
	public void start() {
		_running = true;
		_timeStarted = System.currentTimeMillis()/1000;
	}
	
	public void stop() {
		_running = false;
		_timeFinished = System.currentTimeMillis()/1000;
	}
	
	public void pause() {
		_running = false;
		_timeFinished = System.currentTimeMillis()/1000;
		_sPassed = _timeFinished - _timeStarted;
	}
	
	public void resume() {
		_running = true;
		_timeStarted = System.currentTimeMillis()/1000;
	}
	
	public long secondsPassed() {
		long outcome = 0;
		if(_running) {
			outcome = _sPassed + ((System.currentTimeMillis()/1000)-_timeStarted);
		}else {
			outcome = _sPassed + (_timeFinished - _timeStarted);
		}
		return outcome;
	}
	
	/**
	 * @return String _watchName
	 */
	public String get_watchName() {
		return _watchName;
	}
	
	/**
	 * @return boolean _running
	 */
	public boolean is_running() {
		return _running;
	}
}
