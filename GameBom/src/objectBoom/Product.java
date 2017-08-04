package objectBoom;

import java.io.Serializable;

import android.widget.FrameLayout;
import android.widget.ImageView;

public class Product extends Game implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageView lock_item;

	public Product(FrameLayout id, ImageView lock_item) {
		super(id);
		this.lock_item = lock_item;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lock_item == null) ? 0 : lock_item.hashCode());
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
		Product other = (Product) obj;
		if (lock_item == null) {
			if (other.lock_item != null)
				return false;
		} else if (!lock_item.equals(other.lock_item))
			return false;
		return true;
	}

}
