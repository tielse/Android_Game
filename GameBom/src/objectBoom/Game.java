package objectBoom;

import java.io.Serializable;

import android.widget.FrameLayout;

public class Game implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected FrameLayout id;

	public Game(FrameLayout id) {
		super();
		this.id = id;
	}
}
