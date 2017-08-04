package objectBoom;

import java.io.Serializable;

import android.widget.FrameLayout;
import android.widget.ImageView;

public class Item extends Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageView lock_boom;

	public Item(FrameLayout id, ImageView lock_boom) {
		super(id);
		this.lock_boom = lock_boom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lock_boom == null) ? 0 : lock_boom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (lock_boom == null) {
			if (other.lock_boom != null)
				return false;
		} else if (!lock_boom.equals(other.lock_boom))
			return false;
		return true;
	}

}
