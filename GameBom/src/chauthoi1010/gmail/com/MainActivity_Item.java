package chauthoi1010.gmail.com;

import java.util.HashMap;
import objectBoom.DanhSachObject;
import objectBoom.Game;
import objectBoom.People;
import objectBoom.Product;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity_Item extends Activity {
	private ImageView img_item_nv, img_item_next, img_item_back, img_back,
			img_next, img_item_boom, img_item_hp, img_unlock1, img_lock2,
			img_unlock2, img_lock3, img_unlock3, img_lock4, img_unlock4,
			boom_yellow, boom_red, boom_blue, boom_green, boom_black, ca_chua,
			cam, dau, tao, chuoi, sao, tim;
	private TextView txt_money_play, txt_money_mua1, txt_money_mua2,
			txt_money_mua3, txt_money_mua4, txt_open_key1, txt_close_key2,
			txt_open_key2, txt_close_key3, txt_open_key3, txt_close_key4,
			txt_open_key4, txt_people1, txt_people2, txt_people3, txt_people4,
			txt_sao, txt_boom, txt_lua, txt_mau, txt_boom_yellow, txt_boom_red,
			txt_boom_blue, txt_boom_green, txt_boom_black, txt_check_yellow,
			txt_check_red, txt_check_blue, txt_check_green, txt_check_black,
			txt_xoa_yellow, txt_xoa_red, txt_xoa_blue, txt_xoa_green,
			txt_xoa_black, txt_ca_chua, txt_cam, txt_dau, txt_tao, txt_chuoi,
			txt_sao_hp, txt_tim, txt_check_ca_chua, txt_check_cam,
			txt_check_dau, txt_check_tao, txt_check_chuoi, txt_check_sao,
			txt_check_tim, txt_xoa_ca_chua, txt_xoa_cam, txt_xoa_dau,
			txt_xoa_tao, txt_xoa_chuoi, txt_xoa_sao, txt_xoa_tim;
	private FrameLayout itemnv_display1, itemboom_display2, itemboom_display3;
	private int tien_choi, tien_mua2, tien_mua3, tien_mua4, count = 0,
			tien_mua_black, tien_mua_yellow, tien_mua_red, tien_mua_ca_chua,
			tien_mua_cam, tien_mua_dau, tien_mua_tao, tien_mua_chuoi,
			tien_mua_sao, tien_mua_tim, tien_mua_blue, tien_mua_green;
	private Intent i;
	private Bundle b;
	private int dem = 0;
	private boolean kiemtra_mua = false;
	private HashMap<Integer, ImageView> hsh;

	// private DanhSachObject ds;
	// private People people1, people2, people3, people4;
	// private Game g1, g2, g3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.item_activity);
		@SuppressWarnings("unused")
		final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		Item_Event();
	}

	@SuppressLint("UseSparseArrays")
	private void Item_Event() {
		// FrameLayout
		itemnv_display1 = (FrameLayout) findViewById(R.id.itemnv_display1);
		itemboom_display2 = (FrameLayout) findViewById(R.id.itemboom_display2);
		itemboom_display3 = (FrameLayout) findViewById(R.id.itemboom_display3);
		// ImageView
		img_item_nv = (ImageView) findViewById(R.id.img_item_nv);
		img_item_next = (ImageView) findViewById(R.id.img_item_next);
		img_item_back = (ImageView) findViewById(R.id.img_item_back);
		// img_lock1 = (ImageView) findViewById(R.id.img_lock1);
		img_unlock1 = (ImageView) findViewById(R.id.img_unlock1);
		img_lock2 = (ImageView) findViewById(R.id.img_lock2);
		img_unlock2 = (ImageView) findViewById(R.id.img_unlock2);
		img_lock3 = (ImageView) findViewById(R.id.img_lock3);
		img_unlock3 = (ImageView) findViewById(R.id.img_unlock3);
		img_lock4 = (ImageView) findViewById(R.id.img_lock4);
		img_unlock4 = (ImageView) findViewById(R.id.img_unlock4);
		img_back = (ImageView) findViewById(R.id.img_back);
		img_next = (ImageView) findViewById(R.id.img_next);
		img_item_boom = (ImageView) findViewById(R.id.img_item_boom);
		img_item_hp = (ImageView) findViewById(R.id.img_item_hp);
		boom_yellow = (ImageView) findViewById(R.id.boom_yellow);
		boom_red = (ImageView) findViewById(R.id.boom_red);
		boom_blue = (ImageView) findViewById(R.id.boom_blue);
		boom_green = (ImageView) findViewById(R.id.boom_green);
		boom_black = (ImageView) findViewById(R.id.boom_black);
		ca_chua = (ImageView) findViewById(R.id.ca_chua);
		cam = (ImageView) findViewById(R.id.cam);
		dau = (ImageView) findViewById(R.id.dau);
		tao = (ImageView) findViewById(R.id.tao);
		chuoi = (ImageView) findViewById(R.id.chuoi);
		sao = (ImageView) findViewById(R.id.sao);
		tim = (ImageView) findViewById(R.id.tim);
		// TextView
		txt_money_play = (TextView) findViewById(R.id.txt_money_play);
		txt_money_mua1 = (TextView) findViewById(R.id.txt_money_mua1);
		txt_money_mua2 = (TextView) findViewById(R.id.txt_money_mua2);
		txt_money_mua3 = (TextView) findViewById(R.id.txt_money_mua3);
		txt_money_mua4 = (TextView) findViewById(R.id.txt_money_mua4);
		txt_open_key1 = (TextView) findViewById(R.id.txt_openkey1);
		txt_open_key2 = (TextView) findViewById(R.id.txt_openkey2);
		txt_close_key2 = (TextView) findViewById(R.id.txt_clokey2);
		txt_open_key3 = (TextView) findViewById(R.id.txt_openkey3);
		txt_close_key3 = (TextView) findViewById(R.id.txt_clokey3);
		txt_open_key4 = (TextView) findViewById(R.id.txt_openkey4);
		txt_close_key4 = (TextView) findViewById(R.id.txt_clokey4);
		txt_people1 = (TextView) findViewById(R.id.txt_people1);
		txt_people2 = (TextView) findViewById(R.id.txt_people2);
		txt_people3 = (TextView) findViewById(R.id.txt_people3);
		txt_people4 = (TextView) findViewById(R.id.txt_people4);
		txt_sao = (TextView) findViewById(R.id.txt_sao);
		txt_boom = (TextView) findViewById(R.id.txt_boom);
		txt_lua = (TextView) findViewById(R.id.txt_lua);
		txt_mau = (TextView) findViewById(R.id.txt_mau);
		txt_boom_yellow = (TextView) findViewById(R.id.txt_boom_yellow);
		txt_boom_red = (TextView) findViewById(R.id.txt_boom_red);
		txt_boom_blue = (TextView) findViewById(R.id.txt_boom_blue);
		txt_boom_green = (TextView) findViewById(R.id.txt_boom_green);
		txt_boom_black = (TextView) findViewById(R.id.txt_boom_black);
		txt_check_yellow = (TextView) findViewById(R.id.txt_check_yellow);
		txt_check_red = (TextView) findViewById(R.id.txt_check_red);
		txt_check_blue = (TextView) findViewById(R.id.txt_check_blue);
		txt_check_green = (TextView) findViewById(R.id.txt_check_green);
		txt_check_black = (TextView) findViewById(R.id.txt_check_black);
		txt_xoa_yellow = (TextView) findViewById(R.id.txt_xoa_yellow);
		txt_xoa_black = (TextView) findViewById(R.id.txt_xoa_black);
		txt_xoa_red = (TextView) findViewById(R.id.txt_xoa_red);
		txt_xoa_blue = (TextView) findViewById(R.id.txt_xoa_blue);
		txt_xoa_green = (TextView) findViewById(R.id.txt_xoa_green);
		txt_ca_chua = (TextView) findViewById(R.id.txt_ca_chua);
		txt_cam = (TextView) findViewById(R.id.txt_cam);
		txt_dau = (TextView) findViewById(R.id.txt_dau);
		txt_tao = (TextView) findViewById(R.id.txt_tao);
		txt_chuoi = (TextView) findViewById(R.id.txt_chuoi);
		txt_sao_hp = (TextView) findViewById(R.id.txt_msao);
		txt_tim = (TextView) findViewById(R.id.txt_tim);
		txt_check_ca_chua = (TextView) findViewById(R.id.txt_check_ca_chua);
		txt_check_cam = (TextView) findViewById(R.id.txt_check_cam);
		txt_check_dau = (TextView) findViewById(R.id.txt_check_dau);
		txt_check_tao = (TextView) findViewById(R.id.txt_check_tao);
		txt_check_chuoi = (TextView) findViewById(R.id.txt_check_chuoi);
		txt_check_sao = (TextView) findViewById(R.id.txt_check_sao);
		txt_check_tim = (TextView) findViewById(R.id.txt_check_tim);
		txt_xoa_ca_chua = (TextView) findViewById(R.id.txt_xoa_ca_chua);
		txt_xoa_cam = (TextView) findViewById(R.id.txt_xoa_cam);
		txt_xoa_dau = (TextView) findViewById(R.id.txt_xoa_dau);
		txt_xoa_tao = (TextView) findViewById(R.id.txt_xoa_tao);
		txt_xoa_chuoi = (TextView) findViewById(R.id.txt_xoa_chuoi);
		txt_xoa_sao = (TextView) findViewById(R.id.txt_xoa_sao);
		txt_xoa_tim = (TextView) findViewById(R.id.txt_xoa_tim);
		// Cho tien nguoi choi
		// bien String sang kieu so Integer
		tien_choi = Integer.parseInt(txt_money_play.getText().toString());
		tien_mua2 = Integer.parseInt(txt_money_mua2.getText().toString());
		tien_mua3 = Integer.parseInt(txt_money_mua3.getText().toString());
		tien_mua4 = Integer.parseInt(txt_money_mua4.getText().toString());

		tien_mua_yellow = Integer
				.parseInt(txt_boom_yellow.getText().toString());
		tien_mua_red = Integer.parseInt(txt_boom_red.getText().toString());
		tien_mua_blue = Integer.parseInt(txt_boom_blue.getText().toString());
		tien_mua_green = Integer.parseInt(txt_boom_green.getText().toString());
		tien_mua_black = Integer.parseInt(txt_boom_black.getText().toString());
		tien_mua_ca_chua = Integer.parseInt(txt_ca_chua.getText().toString());
		tien_mua_cam = Integer.parseInt(txt_cam.getText().toString());
		tien_mua_dau = Integer.parseInt(txt_dau.getText().toString());
		tien_mua_tao = Integer.parseInt(txt_tao.getText().toString());
		tien_mua_chuoi = Integer.parseInt(txt_chuoi.getText().toString());
		tien_mua_sao = Integer.parseInt(txt_sao_hp.getText().toString());
		tien_mua_tim = Integer.parseInt(txt_tim.getText().toString());
		// Su ly su kien display1
		img_item_nv.setOnClickListener(new eventListener());
		img_item_boom.setOnClickListener(new eventListener());
		img_item_hp.setOnClickListener(new eventListener());
		img_back.setOnClickListener(new eventListenerBack());
		img_next.setOnClickListener(new eventListenerNext());
		// Doi tuong nguoi chon
		// people1 = new People(itemnv_display1, img_lock1);
		// people2 = new People(itemnv_display1, img_lock2);
		// people3 = new People(itemnv_display1, img_lock3);
		// people4 = new People(itemnv_display1, img_lock4);
		// g1 = new Game(itemnv_display1);
		// g2 = new Game(itemboom_display2);
		// g3 = new Game(itemboom_display3);
		// ds = new DanhSachObject();
		/* Dung HashMap de quan ly */
		hsh = new HashMap<Integer, ImageView>();
		// datahash();
	}

	// private void datahash() {
	// /* Luu tru Item Nhanvat */
	// hsh.put(tien_mua2, img_lock2);
	// hsh.put(tien_mua3, img_lock3);
	// hsh.put(tien_mua4, img_lock4);
	// /* Luu tru Item Boom */
	// hsh.put(tien_mua_black, boom_black);
	// hsh.put(tien_mua_yellow, boom_yellow);
	// hsh.put(tien_mua_red, boom_red);
	// hsh.put(tien_mua_blue, boom_blue);
	// hsh.put(tien_mua_green, boom_green);
	// /* Luu tru Item HP */
	// hsh.put(tien_mua_ca_chua, ca_chua);
	// hsh.put(tien_mua_cam, cam);
	// hsh.put(tien_mua_dau, dau);
	// hsh.put(tien_mua_tao, tao);
	// hsh.put(tien_mua_chuoi, chuoi);
	// hsh.put(tien_mua_sao, sao);
	// hsh.put(tien_mua_tim, tim);
	// }

	private class eventListenerNext implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// Xu ly nut Next
			try {
				if (kiemtra_mua == true) {
					i = new Intent(MainActivity_Item.this,
							MainActivity_Next.class);
					// b = new Bundle();
					// // b.putSerializable("Load", g1);
					// b.putSerializable("Tong hop", hsh);
					// i.putExtras(b);
					startActivity(i);
					finish();
				} else
					Toast.makeText(getApplicationContext(),
							"Vui long mua Boom va Item de vao choi!",
							Toast.LENGTH_LONG).show();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

	}

	private class eventListenerBack implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			i = new Intent(MainActivity_Item.this, MainActivity_Display.class);
			startActivity(i);
			finish();
		}

	}

	private class eventListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// i = new Intent(MainActivity_Item.this, MainActivity_Next.class);
			// b = new Bundle();
			if (v == img_item_nv) {
				itemnv_display1.setVisibility(View.VISIBLE);
				itemboom_display2.setVisibility(View.INVISIBLE);
				itemboom_display3.setVisibility(View.INVISIBLE);
				img_unlock1.setVisibility(View.VISIBLE);
				txt_sao.setText("5");
				txt_boom.setText("1");
				txt_lua.setText("1");
				txt_mau.setText("1");
				txt_open_key1.setVisibility(View.VISIBLE);
				img_item_next.setOnClickListener(new ListenerItem());
				img_item_back.setOnClickListener(new ListenerItem());
			} else if (v == img_item_boom) {
				// xu ly img_item_boom
				itemboom_display2.setVisibility(View.VISIBLE);
				itemboom_display3.setVisibility(View.INVISIBLE);
				itemnv_display1.setVisibility(View.INVISIBLE);
				if (tien_choi >= tien_mua_black) {
					txt_check_black.setVisibility(View.VISIBLE);
					txt_xoa_black.setVisibility(View.INVISIBLE);
					boom_black.setOnClickListener(new muaBoom());
				}
				if (tien_choi >= tien_mua_yellow) {
					txt_check_yellow.setVisibility(View.VISIBLE);
					txt_xoa_yellow.setVisibility(View.INVISIBLE);
					boom_yellow.setOnClickListener(new muaBoom());
				}
				if (tien_choi >= tien_mua_red) {
					txt_check_red.setVisibility(View.VISIBLE);
					txt_xoa_red.setVisibility(View.INVISIBLE);
					boom_red.setOnClickListener(new muaBoom());
				}
				if (tien_choi >= tien_mua_blue) {
					txt_check_blue.setVisibility(View.VISIBLE);
					txt_xoa_blue.setVisibility(View.INVISIBLE);
					boom_blue.setOnClickListener(new muaBoom());
				}
				if (tien_choi >= tien_mua_green) {
					txt_check_green.setVisibility(View.VISIBLE);
					txt_xoa_green.setVisibility(View.INVISIBLE);
					boom_green.setOnClickListener(new muaBoom());
				}
			} else if (v == img_item_hp) {
				itemboom_display3.setVisibility(View.VISIBLE);
				itemboom_display2.setVisibility(View.INVISIBLE);
				itemnv_display1.setVisibility(View.INVISIBLE);
				if (tien_choi >= tien_mua_ca_chua) {
					txt_check_ca_chua.setVisibility(View.VISIBLE);
					txt_xoa_ca_chua.setVisibility(View.INVISIBLE);
					ca_chua.setOnClickListener(new muaHP());
				}
				if (tien_choi >= tien_mua_cam) {
					txt_check_cam.setVisibility(View.VISIBLE);
					txt_xoa_cam.setVisibility(View.INVISIBLE);
					cam.setOnClickListener(new muaHP());
				}
				if (tien_choi >= tien_mua_dau) {
					txt_check_dau.setVisibility(View.VISIBLE);
					txt_xoa_dau.setVisibility(View.INVISIBLE);
					dau.setOnClickListener(new muaHP());
				}
				if (tien_choi >= tien_mua_tao) {
					txt_check_tao.setVisibility(View.VISIBLE);
					txt_xoa_tao.setVisibility(View.INVISIBLE);
					tao.setOnClickListener(new muaHP());
				}
				if (tien_choi >= tien_mua_chuoi) {
					txt_check_chuoi.setVisibility(View.VISIBLE);
					txt_xoa_chuoi.setVisibility(View.INVISIBLE);
					chuoi.setOnClickListener(new muaHP());
				}
				if (tien_choi >= tien_mua_sao) {
					txt_check_sao.setVisibility(View.VISIBLE);
					txt_xoa_sao.setVisibility(View.INVISIBLE);
					sao.setOnClickListener(new muaHP());
				}
				if (tien_choi >= tien_mua_tim) {
					txt_check_tim.setVisibility(View.VISIBLE);
					txt_xoa_tim.setVisibility(View.INVISIBLE);
					tim.setOnClickListener(new muaHP());
				}
			}
		}

	}

	private class muaHP implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == ca_chua) {
				if (tien_choi >= tien_mua_ca_chua) {
					tien_choi -= tien_mua_ca_chua;
					txt_money_play.setText("" + tien_choi);
					txt_xoa_ca_chua.setVisibility(View.INVISIBLE);
					txt_check_ca_chua.setVisibility(View.VISIBLE);
					Toast.makeText(getApplicationContext(),
							"Ban mua thanh cong HP ca chua!",
							Toast.LENGTH_SHORT).show();
					hsh.put(tien_mua_ca_chua, ca_chua);
					txt_xoa_ca_chua.setVisibility(View.VISIBLE);
					txt_check_ca_chua.setVisibility(View.INVISIBLE);
					kiemtra_mua = true;

				} else
					Toast.makeText(getApplicationContext(),
							"So tien ban khong du!\nVui long kiem tra lai!",
							Toast.LENGTH_SHORT).show();
				// kiemtra_mua=false;
			} else if (v == cam) {
				if (tien_choi >= tien_mua_cam) {
					tien_choi -= tien_mua_cam;
					txt_money_play.setText("" + tien_choi);
					txt_xoa_cam.setVisibility(View.INVISIBLE);
					txt_check_cam.setVisibility(View.VISIBLE);
					Toast.makeText(getApplicationContext(),
							"Ban mua thanh cong HP cam!", Toast.LENGTH_SHORT)
							.show();
					hsh.put(tien_mua_cam, cam);
					txt_xoa_cam.setVisibility(View.VISIBLE);
					txt_check_cam.setVisibility(View.INVISIBLE);
					kiemtra_mua = true;
				} else {

					Toast.makeText(getApplicationContext(),
							"So tien ban khong du!\nVui long kiem tra lai!",
							Toast.LENGTH_SHORT).show();
					// kiemtra_mua=false;
				}
			} else if (v == dau) {
				if (tien_choi >= tien_mua_dau) {
					tien_choi -= tien_mua_dau;
					txt_money_play.setText("" + tien_choi);
					txt_xoa_dau.setVisibility(View.INVISIBLE);
					txt_check_dau.setVisibility(View.VISIBLE);
					Toast.makeText(getApplicationContext(),
							"Ban mua thanh cong HP dau!", Toast.LENGTH_SHORT)
							.show();
					hsh.put(tien_mua_dau, dau);
					txt_xoa_dau.setVisibility(View.VISIBLE);
					txt_check_dau.setVisibility(View.INVISIBLE);
					kiemtra_mua = true;

				} else {

					Toast.makeText(getApplicationContext(),
							"So tien ban khong du!\nVui long kiem tra lai!",
							Toast.LENGTH_SHORT).show();
					// kiemtra_mua=false;
				}
			} else if (v == tao) {
				if (tien_choi >= tien_mua_tao) {
					tien_choi -= tien_mua_tao;
					txt_money_play.setText("" + tien_choi);
					txt_xoa_tao.setVisibility(View.INVISIBLE);
					txt_check_tao.setVisibility(View.VISIBLE);
					Toast.makeText(getApplicationContext(),
							"Ban mua thanh cong HP tao!", Toast.LENGTH_SHORT)
							.show();
					hsh.put(tien_mua_tao, tao);
					txt_xoa_tao.setVisibility(View.VISIBLE);
					txt_check_tao.setVisibility(View.INVISIBLE);
					kiemtra_mua = true;
				} else {

					Toast.makeText(getApplicationContext(),
							"So tien ban khong du!\nVui long kiem tra lai!",
							Toast.LENGTH_SHORT).show();
					// kiemtra_mua=false;
				}
			} else if (v == chuoi) {
				if (tien_choi >= tien_mua_chuoi) {
					tien_choi -= tien_mua_chuoi;
					txt_money_play.setText("" + tien_choi);
					txt_xoa_chuoi.setVisibility(View.INVISIBLE);
					txt_check_chuoi.setVisibility(View.VISIBLE);
					Toast.makeText(getApplicationContext(),
							"Ban mua thanh cong HP chuoi!", Toast.LENGTH_SHORT)
							.show();
					hsh.put(tien_mua_chuoi, chuoi);
					txt_xoa_chuoi.setVisibility(View.VISIBLE);
					txt_check_chuoi.setVisibility(View.INVISIBLE);
					kiemtra_mua = true;
				} else {

					Toast.makeText(getApplicationContext(),
							"So tien ban khong du!\nVui long kiem tra lai!",
							Toast.LENGTH_SHORT).show();
					// kiemtra_mua=false;
				}
			} else if (v == sao) {
				if (tien_choi >= tien_mua_sao) {
					tien_choi -= tien_mua_sao;
					txt_money_play.setText("" + tien_choi);
					txt_xoa_sao.setVisibility(View.INVISIBLE);
					txt_check_sao.setVisibility(View.VISIBLE);
					Toast.makeText(getApplicationContext(),
							"Ban mua thanh cong HP sao!", Toast.LENGTH_SHORT)
							.show();
					hsh.put(tien_mua_sao, sao);
					txt_xoa_sao.setVisibility(View.VISIBLE);
					txt_check_sao.setVisibility(View.INVISIBLE);
					kiemtra_mua = true;
				} else {

					Toast.makeText(getApplicationContext(),
							"So tien ban khong du!\nVui long kiem tra lai!",
							Toast.LENGTH_SHORT).show();
					// kiemtra_mua=false;
				}
			} else if (v == tim) {
				if (tien_choi >= tien_mua_tim) {
					tien_choi -= tien_mua_tim;
					txt_money_play.setText("" + tien_choi);
					txt_xoa_tim.setVisibility(View.INVISIBLE);
					txt_check_tim.setVisibility(View.VISIBLE);
					Toast.makeText(getApplicationContext(),
							"Ban mua thanh cong HP tim!", Toast.LENGTH_SHORT)
							.show();
					hsh.put(tien_mua_tim, tim);
					txt_xoa_tim.setVisibility(View.VISIBLE);
					txt_check_tim.setVisibility(View.INVISIBLE);
					kiemtra_mua = true;
				} else {

					Toast.makeText(getApplicationContext(),
							"So tien ban khong du!\nVui long kiem tra lai!",
							Toast.LENGTH_SHORT).show();
					// kiemtra_mua=false;
				}
			}
		}

	}

	private class muaBoom implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == boom_black) {
				if (tien_choi >= tien_mua_black) {
					tien_choi -= tien_mua_black;
					txt_money_play.setText("" + tien_choi);
					txt_xoa_black.setVisibility(View.INVISIBLE);
					txt_check_black.setVisibility(View.VISIBLE);
					Toast.makeText(getApplicationContext(),
							"Ban mua thanh cong boom xam!", Toast.LENGTH_SHORT)
							.show();
					hsh.put(tien_mua_black, boom_black);
					txt_xoa_black.setVisibility(View.VISIBLE);
					txt_check_black.setVisibility(View.INVISIBLE);
					kiemtra_mua = true;
				} else {

					Toast.makeText(getApplicationContext(),
							"So tien ban khong du!\nVui long kiem tra lai!",
							Toast.LENGTH_SHORT).show();
					// kiemtra_mua=false;
				}
			} else if (v == boom_yellow) {
				// Xu ly boom yellow
				if (tien_choi >= tien_mua_yellow) {
					tien_choi -= tien_mua_yellow;
					txt_money_play.setText("" + tien_choi);
					txt_xoa_yellow.setVisibility(View.INVISIBLE);
					txt_check_yellow.setVisibility(View.VISIBLE);
					Toast.makeText(getApplicationContext(),
							"Ban mua thanh cong boom vang!", Toast.LENGTH_SHORT)
							.show();
					hsh.put(tien_mua_yellow, boom_yellow);
					txt_xoa_yellow.setVisibility(View.VISIBLE);
					txt_check_yellow.setVisibility(View.INVISIBLE);
					kiemtra_mua = true;
				} else {

					Toast.makeText(getApplicationContext(),
							"So tien ban khong du!\nVui long kiem tra lai!",
							Toast.LENGTH_SHORT).show();
					// kiemtra_mua=false;
				}

			} else if (v == boom_red) {
				// Xu ly boom red
				if (tien_choi >= tien_mua_red) {
					tien_choi = tien_choi - tien_mua_red;
					txt_money_play.setText("" + tien_choi);
					txt_xoa_yellow.setVisibility(View.INVISIBLE);
					txt_check_yellow.setVisibility(View.VISIBLE);
					Toast.makeText(getApplicationContext(),
							"Ban mua thanh cong boom do!", Toast.LENGTH_SHORT)
							.show();
					hsh.put(tien_mua_red, boom_red);
					txt_xoa_yellow.setVisibility(View.VISIBLE);
					txt_check_yellow.setVisibility(View.INVISIBLE);
					kiemtra_mua = true;
				} else {

					Toast.makeText(getApplicationContext(),
							"So tien ban khong du!\nVui long kiem tra lai!",
							Toast.LENGTH_SHORT).show();
					// kiemtra_mua=false;
				}
			} else if (v == boom_blue) {
				// Xu ly boom blue
				if (tien_choi >= tien_mua_blue) {
					tien_choi -= tien_mua_blue;
					txt_money_play.setText("" + tien_choi);
					txt_xoa_yellow.setVisibility(View.INVISIBLE);
					txt_check_yellow.setVisibility(View.VISIBLE);
					Toast.makeText(getApplicationContext(),
							"Ban mua thanh cong boom xanh duong!",
							Toast.LENGTH_SHORT).show();
					hsh.put(tien_mua_blue, boom_blue);
					txt_xoa_yellow.setVisibility(View.VISIBLE);
					txt_check_yellow.setVisibility(View.INVISIBLE);
					kiemtra_mua = true;
				} else {

					Toast.makeText(getApplicationContext(),
							"So tien ban khong du!\nVui long kiem tra lai!",
							Toast.LENGTH_SHORT).show();
					// kiemtra_mua=false;
				}
			} else if (v == boom_green) {
				// Xu ly boom green
				if (tien_choi >= tien_mua_green) {
					tien_choi -= tien_mua_green;
					txt_money_play.setText("" + tien_choi);
					txt_xoa_yellow.setVisibility(View.INVISIBLE);
					txt_check_yellow.setVisibility(View.VISIBLE);
					Toast.makeText(getApplicationContext(),
							"Ban mua thanh cong boom xanh la cay!",
							Toast.LENGTH_SHORT).show();
					hsh.put(tien_mua_green, boom_green);
					txt_xoa_yellow.setVisibility(View.VISIBLE);
					txt_check_yellow.setVisibility(View.INVISIBLE);
					kiemtra_mua = true;
				} else {

					Toast.makeText(getApplicationContext(),
							"So tien ban khong du!\nVui long kiem tra lai!",
							Toast.LENGTH_SHORT).show();
					// kiemtra_mua=false;
				}
			}
		}

	}

	private class ListenerItem implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == img_item_next) {
				count++;
				switch (count) {
				case 1:
					txt_people2.setVisibility(View.VISIBLE);
					txt_people1.setVisibility(View.INVISIBLE);
					txt_money_mua2.setVisibility(View.VISIBLE);
					txt_close_key2.setVisibility(View.VISIBLE);
					txt_open_key1.setVisibility(View.INVISIBLE);
					img_unlock1.setVisibility(View.INVISIBLE);
					img_unlock2.setVisibility(View.VISIBLE);
					txt_boom.setText("3");
					txt_sao.setText("10");
					txt_lua.setText("2");
					txt_mau.setText("2");
					txt_money_mua1.setVisibility(View.INVISIBLE);
					if (tien_choi >= tien_mua2) {
						img_lock2.setVisibility(View.VISIBLE);
						img_unlock2.setVisibility(View.INVISIBLE);
						txt_open_key2.setVisibility(View.VISIBLE);
						txt_close_key2.setVisibility(View.INVISIBLE);
						txt_open_key1.setVisibility(View.INVISIBLE);
						img_lock2.setOnClickListener(new ListenerLock2());

					}
					break;

				case 2:
					txt_people3.setVisibility(View.VISIBLE);
					txt_people2.setVisibility(View.INVISIBLE);
					txt_money_mua3.setVisibility(View.VISIBLE);
					txt_money_mua2.setVisibility(View.INVISIBLE);
					txt_close_key2.setVisibility(View.INVISIBLE);
					txt_open_key2.setVisibility(View.INVISIBLE);
					txt_close_key2.setVisibility(View.INVISIBLE);
					txt_close_key3.setVisibility(View.VISIBLE);
					img_lock2.setVisibility(View.INVISIBLE);
					img_unlock2.setVisibility(View.INVISIBLE);
					img_unlock3.setVisibility(View.VISIBLE);
					txt_sao.setText("25");
					txt_boom.setText("5");
					txt_lua.setText("3");
					txt_mau.setText("2");
					if (tien_choi >= tien_mua3) {
						img_lock3.setVisibility(View.VISIBLE);
						img_unlock3.setVisibility(View.INVISIBLE);
						txt_open_key3.setVisibility(View.VISIBLE);
						txt_close_key3.setVisibility(View.INVISIBLE);
						txt_open_key2.setVisibility(View.INVISIBLE);
						txt_close_key2.setVisibility(View.INVISIBLE);
						img_lock3.setOnClickListener(new ListenerLock3());

					}
					break;
				case 3:
					txt_people4.setVisibility(View.VISIBLE);
					txt_people3.setVisibility(View.INVISIBLE);
					txt_money_mua4.setVisibility(View.VISIBLE);
					txt_money_mua3.setVisibility(View.INVISIBLE);
					txt_open_key3.setVisibility(View.INVISIBLE);
					txt_close_key3.setVisibility(View.INVISIBLE);
					txt_close_key4.setVisibility(View.VISIBLE);
					img_lock3.setVisibility(View.INVISIBLE);
					img_unlock3.setVisibility(View.INVISIBLE);
					img_unlock4.setVisibility(View.VISIBLE);
					txt_sao.setText("50");
					txt_boom.setText("10");
					txt_lua.setText("5");
					txt_mau.setText("3");
					if (tien_choi >= tien_mua4) {
						img_lock4.setVisibility(View.VISIBLE);
						img_unlock4.setVisibility(View.INVISIBLE);
						txt_open_key4.setVisibility(View.VISIBLE);
						txt_close_key4.setVisibility(View.INVISIBLE);
						txt_open_key3.setVisibility(View.INVISIBLE);
						txt_close_key3.setVisibility(View.INVISIBLE);
						img_lock4.setOnClickListener(new ListenerLock4());

					}
					break;
				}
				if (count == 4) {
					count = 0;
					txt_people4.setVisibility(View.INVISIBLE);
					txt_people1.setVisibility(View.VISIBLE);
					txt_open_key4.setVisibility(View.INVISIBLE);
					txt_close_key4.setVisibility(View.INVISIBLE);
					img_lock4.setVisibility(View.INVISIBLE);
					img_unlock4.setVisibility(View.INVISIBLE);
					img_unlock1.setVisibility(View.VISIBLE);
					txt_sao.setText("5");
					txt_boom.setText("1");
					txt_lua.setText("1");
					txt_mau.setText("1");
					txt_money_mua4.setVisibility(View.INVISIBLE);
					txt_money_mua1.setVisibility(View.VISIBLE);
					txt_open_key1.setVisibility(View.VISIBLE);
				}
			} else if (v == img_item_back) {
				count--;
				switch (count) {
				case 3:
					txt_people4.setVisibility(View.VISIBLE);
					txt_people1.setVisibility(View.INVISIBLE);
					txt_money_mua4.setVisibility(View.VISIBLE);
					txt_money_mua1.setVisibility(View.INVISIBLE);
					txt_open_key1.setVisibility(View.INVISIBLE);
					txt_close_key4.setVisibility(View.VISIBLE);
					img_unlock1.setVisibility(View.INVISIBLE);
					img_unlock4.setVisibility(View.VISIBLE);
					txt_sao.setText("50");
					txt_boom.setText("10");
					txt_lua.setText("5");
					txt_mau.setText("3");
					if (tien_choi >= tien_mua4) {
						img_lock4.setVisibility(View.VISIBLE);
						img_unlock4.setVisibility(View.INVISIBLE);
						txt_open_key4.setVisibility(View.VISIBLE);
						txt_close_key4.setVisibility(View.INVISIBLE);
						txt_open_key1.setVisibility(View.INVISIBLE);
						img_lock4.setOnClickListener(new ListenerLock4());

					}
					break;

				case 2:
					txt_people4.setVisibility(View.INVISIBLE);
					txt_people3.setVisibility(View.VISIBLE);
					txt_money_mua3.setVisibility(View.VISIBLE);
					txt_money_mua4.setVisibility(View.INVISIBLE);
					txt_open_key4.setVisibility(View.INVISIBLE);
					txt_close_key4.setVisibility(View.INVISIBLE);
					txt_close_key3.setVisibility(View.VISIBLE);
					img_lock4.setVisibility(View.INVISIBLE);
					img_unlock4.setVisibility(View.INVISIBLE);
					img_unlock3.setVisibility(View.VISIBLE);
					txt_sao.setText("25");
					txt_boom.setText("5");
					txt_lua.setText("3");
					txt_mau.setText("2");
					if (tien_choi >= tien_mua3) {
						img_lock3.setVisibility(View.VISIBLE);
						img_unlock3.setVisibility(View.INVISIBLE);
						txt_open_key3.setVisibility(View.VISIBLE);
						txt_close_key3.setVisibility(View.INVISIBLE);
						txt_open_key4.setVisibility(View.INVISIBLE);
						txt_close_key4.setVisibility(View.INVISIBLE);
						img_lock3.setOnClickListener(new ListenerLock3());
					}
					break;
				case 1:
					txt_people2.setVisibility(View.VISIBLE);
					txt_people3.setVisibility(View.INVISIBLE);
					txt_money_mua2.setVisibility(View.VISIBLE);
					txt_money_mua3.setVisibility(View.INVISIBLE);
					txt_open_key3.setVisibility(View.INVISIBLE);
					txt_close_key3.setVisibility(View.INVISIBLE);
					txt_close_key2.setVisibility(View.VISIBLE);
					img_lock3.setVisibility(View.INVISIBLE);
					img_unlock3.setVisibility(View.INVISIBLE);
					img_unlock2.setVisibility(View.VISIBLE);
					txt_boom.setText("3");
					txt_sao.setText("10");
					txt_lua.setText("2");
					txt_mau.setText("2");
					if (tien_choi >= tien_mua2) {
						img_lock2.setVisibility(View.VISIBLE);
						img_unlock2.setVisibility(View.INVISIBLE);
						txt_open_key2.setVisibility(View.VISIBLE);
						txt_close_key2.setVisibility(View.INVISIBLE);
						txt_open_key3.setVisibility(View.INVISIBLE);
						txt_close_key3.setVisibility(View.INVISIBLE);
						img_lock2.setOnClickListener(new ListenerLock2());
					}
					break;
				}
				if (count == 0) {
					count = 4;
					txt_people2.setVisibility(View.INVISIBLE);
					txt_people1.setVisibility(View.VISIBLE);
					txt_open_key2.setVisibility(View.INVISIBLE);
					txt_close_key2.setVisibility(View.INVISIBLE);
					img_lock2.setVisibility(View.INVISIBLE);
					img_unlock2.setVisibility(View.INVISIBLE);
					img_unlock1.setVisibility(View.VISIBLE);
					txt_sao.setText("5");
					txt_boom.setText("1");
					txt_lua.setText("1");
					txt_mau.setText("1");
					txt_money_mua1.setVisibility(View.VISIBLE);
					txt_money_mua2.setVisibility(View.INVISIBLE);
					txt_open_key1.setVisibility(View.VISIBLE);
				}

			}
		}

	}

	private class ListenerLock2 implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == img_lock2) {
				final AlertDialog.Builder dia = new AlertDialog.Builder(
						MainActivity_Item.this);
				dia.setTitle("Question");
				dia.setIcon(R.drawable.item_question);
				dia.setMessage("Ban co chac mua nhan vat nay khong?");
				dia.setPositiveButton("No",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
							}
						});
				dia.setNegativeButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								tien_choi -= tien_mua2;
								txt_money_play.setText("" + tien_choi);
								dem++;
								Toast.makeText(getApplicationContext(),
										"Ban da mua thanh cong?",
										Toast.LENGTH_SHORT).show();
								hsh.put(tien_mua2, img_lock2);
								// ds.add_people(people2);
								txt_close_key2.setVisibility(View.VISIBLE);
								img_unlock2.setVisibility(View.VISIBLE);
							}
						});
				dia.create().show();
			}
		}
	}

	private class ListenerLock3 implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == img_lock3) {
				final AlertDialog.Builder dia = new AlertDialog.Builder(
						MainActivity_Item.this);
				dia.setTitle("Question");
				dia.setIcon(R.drawable.item_question);
				dia.setMessage("Ban co chac mua nhan vat nay khong?");
				dia.setPositiveButton("No",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
							}
						});
				dia.setNegativeButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								tien_choi -= tien_mua3;
								txt_money_play.setText("" + tien_choi);
								dem++;
								Toast.makeText(getApplicationContext(),
										"Ban da mua thanh cong?",
										Toast.LENGTH_SHORT).show();
								hsh.put(tien_mua3, img_lock3);
								// ds.add_people(people3);
								txt_close_key3.setVisibility(View.VISIBLE);
								img_unlock3.setVisibility(View.VISIBLE);
							}
						});
				dia.create().show();
			}
		}
	}

	private class ListenerLock4 implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v == img_lock4) {
				final AlertDialog.Builder dia = new AlertDialog.Builder(
						MainActivity_Item.this);
				dia.setTitle("Question");
				dia.setIcon(R.drawable.item_question);
				dia.setMessage("Ban co chac mua nhan vat nay khong?");
				dia.setPositiveButton("No",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
							}
						});
				dia.setNegativeButton("Yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								tien_choi = tien_choi - tien_mua4;
								txt_money_play.setText("" + tien_choi);
								dem++;
								Toast.makeText(getApplicationContext(),
										"Ban da mua thanh cong?",
										Toast.LENGTH_SHORT).show();
								hsh.put(tien_mua4, img_lock4);
								// ds.add_people(people4);
								txt_close_key4.setVisibility(View.VISIBLE);
								img_unlock4.setVisibility(View.VISIBLE);
							}
						});
				dia.create().show();
			}
		}

	}
}
