package objectBoom;

import java.util.ArrayList;
import java.util.List;

public class DanhSachObject {
	private List<Game> lst;

	public DanhSachObject() {
		// TODO Auto-generated constructor stub
		lst = new ArrayList<Game>();
	}

	public boolean add_people(People p) {
		if (lst.contains(p))
			return false;
		lst.add(p);
		return true;
	}

	public boolean add_pro(Product pro) {
		if (lst.contains(pro))
			return false;
		lst.add(pro);
		return true;
	}

	public boolean add_item(Item item) {
		if (lst.contains(item))
			return false;
		lst.add(item);
		return true;
	}
}
