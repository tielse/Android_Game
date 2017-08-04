package chauthoi1010.gmail.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MainActivity_Next extends Activity {
	private Intent call;
	private ImageView next, back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.huongdan_activity);
		data();
	}

	private void data() {
		next = (ImageView) findViewById(R.id.img_next);
		back = (ImageView) findViewById(R.id.img_back);
		next.setOnClickListener(new eventN());
		back.setOnClickListener(new eventN());
	}

	/* interface s/k click */
	private class eventN implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == next)
				next_play();
			else if (v == back)
				back_close();
		}
	}

	private void next_play() {
		call = new Intent(MainActivity_Next.this, MainActivity_Game.class);
		startActivity(call);
		finish();
	}

	/* Xu ly quay lai MainActivity_Item */
	private void back_close() {
		call = new Intent(MainActivity_Next.this, MainActivity_Item.class);
		startActivity(call);
		finish();
	}
}
