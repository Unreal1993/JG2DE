/**
  * Name    : Vector2D.java
  * Project : JG2DE
  * Author  : Robert M Hubinsky <r.hubinsky@hotmail.com>
  * Created : 6.11.2012
  */
package engine.helpers;

public class Vector2D {
	private float _x;
	private float _y;
	public Vector2D(float x,float y) {
		_x= x;
		_y=y;
	}
	/**
	 * @return float _x
	 */
	public float get_x() {
		return _x;
	}
	/**
	 * @param _x the _x to set
	 */
	public void set_x(float _x) {
		this._x = _x;
	}
	/**
	 * @return float _y
	 */
	public float get_y() {
		return _y;
	}
	/**
	 * @param _y the _y to set
	 */
	public void set_y(float _y) {
		this._y = _y;
	}
}
