package chauthoi1010.gmail.com.Dialog;

import chauthoi1010.gmail.com.R;
import chauthoi1010.gmail.com.Database.Database;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_DialogDiem extends Dialog {
	private TextView[] lst_name, lst_diem;
	private Database db;
	private Button clear, close;

	public MainActivity_DialogDiem(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setContentView(R.layout.queue_activity);
		setTitle(Html.fromHtml("<b>The point of the game!</b>"));

		/* Dang sach name */
		danhsachname();
		/* Danh sach diem */
		danhsachdiem();
		/* Khoi tao data */
		data();
	}

	private void data() {
		clear = (Button) findViewById(R.id.xoa);
		close = (Button) findViewById(R.id.dong);
		/* Su kien Onclick interface */
		clear.setOnClickListener(new event());
		close.setOnClickListener(new event());
	}

	private class event implements android.view.View.OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == clear) {
				funtion_clear();
			} else if (v == close) {
				funtion_close();
			}
		}

	}

	private void funtion_clear() {
		db.open();
		db.deleteAllRow();
		db.close();
		csdlTmp();
		upList();
	}

	/* Du lieu Tam gom 5 nguoi */
	private void csdlTmp() {
		try {
			db.open();
			Cursor c = db.getAllRow();
			if (!c.moveToFirst()) {
				db.isertRow("Player", 100);
				db.isertRow("Player", 100);
				db.isertRow("Player", 100);
				db.isertRow("Player", 100);
				db.isertRow("Player", 100);
			}
			c.close();
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void funtion_close() {
		dismiss();
	}

	private void danhsachname() {
		lst_name = new TextView[5];
		lst_name[0] = (TextView) findViewById(R.id.name_1);
		lst_name[1] = (TextView) findViewById(R.id.name_2);
		lst_name[2] = (TextView) findViewById(R.id.name_3);
		lst_name[3] = (TextView) findViewById(R.id.name_4);
		lst_name[4] = (TextView) findViewById(R.id.name_5);
		upList();
	}

	private void danhsachdiem() {
		lst_diem = new TextView[5];
		lst_diem[0] = (TextView) findViewById(R.id.diem_1);
		lst_diem[1] = (TextView) findViewById(R.id.diem_2);
		lst_diem[2] = (TextView) findViewById(R.id.diem_3);
		lst_diem[3] = (TextView) findViewById(R.id.diem_4);
		lst_diem[4] = (TextView) findViewById(R.id.diem_5);
		upList();
	}

	private void upList() {
		int i = 0;
		try {
			/* Bat CSDL */
			db.open();
			/* Cursor dang con tro trong SQLite lay thong trong ham getAllRow */
			Cursor c = db.getAllRow();
			/* Duyet tu vi tri dau cho den cuoi */
			if (c.moveToFirst()) {
				do {
					String name = c.getString(c.getColumnIndex(Database.Name));
					int diem = c.getInt(c.getColumnIndex(Database.Diem));
					lst_name[i].setText(name);
					lst_diem[i].setText(String.valueOf(diem));
					if (i == 4)
						break;
					else
						i++;
				} while (c.moveToNext());
			}
			c.close();
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
