package chauthoi1010.gmail.com;

import chauthoi1010.gmail.com.Database.Database;
import chauthoi1010.gmail.com.Dialog.MainActivity_DialogDiem;
import chauthoi1010.gmail.com.Dialog.MainActivity_DialogExit;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Luudiem extends Activity {
	private Button menu, choilai, luu, xem_diem;
	private TextView txt_diem;
	private EditText ed_ten;
	private Database db;
	int diem = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.savepoint_activity);
		data();
	}

	private void data() {
		db = new Database(this);
		Bundle b = getIntent().getExtras();
		if (b != null)
			diem = b.getInt("diem");
		choilai = (Button) findViewById(R.id.bt_choilai);
		luu = (Button) findViewById(R.id.bt_luudiem);
		menu = (Button) findViewById(R.id.bt_menu);
		xem_diem = (Button) findViewById(R.id.bt_xemdiem);
		txt_diem = (TextView) findViewById(R.id.txt_diem);
		txt_diem.setText(String.valueOf(diem));
		ed_ten = (EditText) findViewById(R.id.edit_name);
		choilai.setOnClickListener(new event());
		luu.setOnClickListener(new event());
		menu.setOnClickListener(new event());
		xem_diem.setOnClickListener(new event());
	}

	private class event implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == choilai)
				call_choilai();
			else if (v == luu)
				call_luu();
			else if (v == menu)
				call_menu();
			else if (v == xem_diem)
				call_xemdiem();
		}

	}

	private void call_choilai() {
		Intent i = new Intent(this, MainActivity_Game.class);
		startActivity(i);
		finish();
	}

	private void call_luu() {
		String name = ed_ten.getText().toString();
		int diemchoi = Integer.parseInt(txt_diem.getText().toString());
		if (name.length() == 0 || name == null) {
			Toast.makeText(this, "Ban chua nhap ten", Toast.LENGTH_LONG).show();
			return;
		}
		try {
			db.open();
			Cursor c = db.getAllRow();
			if (c.moveToPosition(4)) {
				String id = c.getString(c.getColumnIndex(Database.RowID));
				int math = c.getInt(c.getColumnIndex(Database.Diem));
				if (math < diemchoi) {
					db.updatePlayer(id, name, diemchoi);
					LinearLayout l = (LinearLayout) findViewById(R.id.linear_layout);
					luu.setVisibility(View.GONE);
					l.setVisibility(View.GONE);
					xem_diem.setVisibility(View.VISIBLE);
					xemdiemcao();
				}
			}
			c.close();
			db.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void xemdiemcao() {
		MainActivity_DialogDiem xemdiem = new MainActivity_DialogDiem(this);
		xemdiem.show();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		finish();
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		finish();
	}

	private void call_menu() {
		Intent i = new Intent(this, MainActivity_Display.class);
		startActivity(i);
		finish();
	}

	private void call_xemdiem() {
		xemdiemcao();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			MainActivity_DialogExit Dialg_exit = new MainActivity_DialogExit(
					this, this);
			Dialg_exit.show();
		}
		return super.onKeyDown(keyCode, event);
	}
}
