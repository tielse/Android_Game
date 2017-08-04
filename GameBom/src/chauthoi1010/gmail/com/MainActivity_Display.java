package chauthoi1010.gmail.com;

import chauthoi1010.gmail.com.Controler.Controler;
import chauthoi1010.gmail.com.Database.Database;
import chauthoi1010.gmail.com.Dialog.MainActivity_DialogDiem;
import chauthoi1010.gmail.com.Dialog.MainActivity_DialogExit;
import chauthoi1010.gmail.com.Dialog.MainActivity_DialogInfor;
import chauthoi1010.gmail.com.Sound.Sound;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class MainActivity_Display extends Activity {
	// Tao hieu ung dong cho View dung Amation
	private Animation animation_xoay;
	private Animation animation_facein;
	private ImageView play, huongdan, thoat, xephang;
	private Intent i;
	// Xu ly tien trinh
	int dem = 0;
	// Load Database luu thong tin diem nguoi
	private Database db;
	// Load Sound
	private Sound sound;
	private ImageView img_sound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.display_activity);
		animation_facein = AnimationUtils.loadAnimation(this, R.anim.fadein);
		animation_xoay = AnimationUtils.loadAnimation(this, R.anim.anim_xoay);
		sound = new Sound(this, R.raw.background_1);
		if (Controler.SOUND)
			sound.play();
		csdlTmp();
		CallImageView();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (sound != null)
			sound.replease();
		super.onDestroy();
	}

	// Lan dau choi thi tao ra 1 CSDL
	public void csdlTmp() {
		// Tao data mau lan dau khi choi
		db = new Database(this);
		// Dua data mau 5 nguoi choi
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

	@SuppressLint("HandlerLeak")
	private void CallImageView() {
		play = (ImageView) findViewById(R.id.imageButton_play);
		huongdan = (ImageView) findViewById(R.id.imageButton_infor);
		thoat = (ImageView) findViewById(R.id.imageButton_exit);
		xephang = (ImageView) findViewById(R.id.imageButton_vip);
		img_sound = (ImageView) findViewById(R.id.imageView_sound);
		play.setOnClickListener(new EventPress());
		huongdan.setOnClickListener(new EventPress());
		thoat.setOnClickListener(new EventPress());
		xephang.setOnClickListener(new EventPress());
		img_sound.setOnClickListener(new EventPress());
		final Handler h = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (sound.complet && Controler.SOUND) {
					sound.complet = false;
					sound.replay();
					System.out.println("replay");
				}
				switch (dem) {
				case 0:
					play.startAnimation(animation_facein);
					huongdan.clearAnimation();
					thoat.clearAnimation();
					xephang.clearAnimation();
					break;
				case 1:
					play.clearAnimation();
					huongdan.startAnimation(animation_xoay);
					thoat.clearAnimation();
					xephang.clearAnimation();
					break;
				case 2:
					play.clearAnimation();
					huongdan.clearAnimation();
					thoat.startAnimation(animation_xoay);
					xephang.clearAnimation();
					break;
				case 3:
					play.clearAnimation();
					huongdan.clearAnimation();
					thoat.clearAnimation();
					xephang.startAnimation(animation_xoay);
					break;
				default:
					break;
				}
				super.handleMessage(msg);
			}
		};
		// Dung thread de xu ly tien trinh animation
		Thread th = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					dem++;
					if (dem > 2)
						dem = 0;
					Message msg = h.obtainMessage();
					h.sendMessage(msg);
				}
			}
		});
		th.start();
	}

	private class EventPress implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == play)
				Eventplay();
			else if (v == huongdan)
				Eventhuogndan();
			else if (v == thoat)
				Eventthoat();
			else if (v == img_sound)
				Eventsound();
			else if (v == xephang)
				Eventxephang();

		}

	}

	// Xu ly mo thu hang
	private void Eventxephang() {
		MainActivity_DialogDiem dia_diem = new MainActivity_DialogDiem(this);
		dia_diem.show();
	}

	// Xu ly Sound
	private void Eventsound() {
		if (Controler.SOUND == false) {
			img_sound.setImageResource(R.drawable.sound_off);
			Controler.SOUND = false;
			if (sound.mPlaying) {
				sound.stop();

			}
		} else {
			img_sound.setImageResource(R.drawable.sound_on);
			Controler.SOUND = true;
			if (!sound.mPlaying)
				sound.play();
		}

	}

	// Xu ly Intent load MainActivity_Item
	private void Eventplay() {
		i = new Intent(MainActivity_Display.this, MainActivity_Item.class);
		sound.stop();
		startActivity(i);
		finish();
	}

	private void Eventhuogndan() {
		MainActivity_DialogInfor dia_infor = new MainActivity_DialogInfor(this);
		dia_infor.show();
	}

	private void Eventthoat() {
		MainActivity_DialogExit dia_exit = new MainActivity_DialogExit(
				MainActivity_Display.this, MainActivity_Display.this);
		dia_exit.show();
	}

	// Su kien khi nhan phim thoat
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			MainActivity_DialogExit dia_exit = new MainActivity_DialogExit(
					this, this);
			dia_exit.show();
		}
		return super.onKeyDown(keyCode, event);
	}
}
