package chauthoi1010.gmail.com;

import chauthoi1010.gmail.com.Dialog.MainActivity_DialogExit;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity_GameOver extends Activity {
	private Button choilai, menu, thoat;
	private Intent i;
	private MainActivity_DialogExit dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.gameover_activity);
		data();
	}

	private void data() {
		choilai = (Button) findViewById(R.id.button_gameover_choilai);
		menu = (Button) findViewById(R.id.button_gameover_mainmenu);
		thoat = (Button) findViewById(R.id.button_gameover_thoat);
		choilai.setOnClickListener(new xuly());
		menu.setOnClickListener(new xuly());
		thoat.setOnClickListener(new xuly());
	}

	private class xuly implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == choilai)
				funtion_play();
			else if (v == menu)
				funtion_menu();
			else if (v == thoat)
				funtion_exit();
		}

	}

	private void funtion_play() {
		i = new Intent(this, MainActivity_Game.class);
		startActivity(i);
		finish();
	}

	private void funtion_menu() {
		i = new Intent(this, MainActivity_Display.class);
		startActivity(i);
		finish();
	}

	private void funtion_exit() {
		dialog = new MainActivity_DialogExit(this, this);
		dialog.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			dialog = new MainActivity_DialogExit(MainActivity_GameOver.this,
					MainActivity_GameOver.this);
			startActivity(i);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
