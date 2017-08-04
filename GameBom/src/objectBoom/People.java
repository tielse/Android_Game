package objectBoom;

import java.io.Serializable;

import android.widget.FrameLayout;
import android.widget.ImageView;

public class People extends Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageView lock;

	public People(FrameLayout id, ImageView lock) {
		super(id);
		this.lock = lock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lock == null) ? 0 : lock.hashCode());
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
		People other = (People) obj;
		if (lock == null) {
			if (other.lock != null)
				return false;
		} else if (!lock.equals(other.lock))
			return false;
		return true;
	}

}
