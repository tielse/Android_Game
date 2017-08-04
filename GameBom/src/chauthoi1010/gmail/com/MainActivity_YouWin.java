package chauthoi1010.gmail.com;

import chauthoi1010.gmail.com.Database.Database;
import chauthoi1010.gmail.com.Dialog.MainActivity_DialogExit;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity_YouWin extends Activity {
	int Diem = 0;
	private Database db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.youwinner_activity);
		Bundle b = getIntent().getExtras();
		if (b != null)
			Diem = b.getInt("diem");
		db = new Database(this);
		Button ok = (Button) findViewById(R.id.bt_ok);
		ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (db.kt_luu(Diem)) {
					Intent i = new Intent(MainActivity_YouWin.this,
							Luudiem.class);
					i.putExtra("diem", Diem);
					startActivity(i);
					finish();

				}
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			MainActivity_DialogExit dialogexit = new MainActivity_DialogExit(
					this, this);
			dialogexit.show();
		}
		return super.onKeyDown(keyCode, event);
	}
}
