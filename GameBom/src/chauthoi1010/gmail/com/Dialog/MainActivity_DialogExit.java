package chauthoi1010.gmail.com.Dialog;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import chauthoi1010.gmail.com.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.os.AsyncTask;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_DialogExit extends Dialog {
	@SuppressWarnings("unused")
	private boolean thoat = false;
	private Button yes, no;
	/*
	 * private TextView mess; private String code; private final static String
	 * TAG = "TestImageGetter";
	 */
	private final Activity activity;

	public MainActivity_DialogExit(Context context, Activity ac) {
		super(context);
		// TODO Auto-generated constructor stub
		activity = ac;
		setTitle(Html.fromHtml("<b>Questions</b>"));
		setContentView(R.layout.thoat_activity);
		// code = "<b>Questions</b>";
		data_show();
	}

	private void data_show() {
		// mess = (TextView) findViewById(R.id.txt_view);
		yes = (Button) findViewById(R.id.button_thoat_co);
		no = (Button) findViewById(R.id.button_thoat_khong);
		// Spanned sp = Html.fromHtml(code, this, null);
		// mess.setText(sp);
		// mess.setTextSize(12);
		yes.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				xuly_yes();
			}
		});
		no.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				xuly_no();
			}
		});
	}

	private void xuly_yes() {
		thoat = true;
		if (activity != null)
			activity.finish();
		dismiss();
	}

	private void xuly_no() {
		thoat = false;
		dismiss();
	}
	// // Interface ImageGetter
	// @SuppressLint("NewApi")
	// @Override
	// public Drawable getDrawable(String source) {
	// // TODO Auto-generated method stub
	// LevelListDrawable d = new LevelListDrawable();
	// Drawable empty = getResources().getDrawable(R.drawable.item_question);
	// d.addLevel(0, 0, empty);
	// d.setBounds(0, 0, empty.getIntrinsicWidth(), empty.getIntrinsicHeight());
	// new LoadImage().execute(source, d);
	// return d;
	//
	// }
	//
	// private Context getResources() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// class LoadImage extends AsyncTask<Object, Void, Bitmap> {
	//
	// private LevelListDrawable mDrawable;
	//
	// @Override
	// protected Bitmap doInBackground(Object... params) {
	// String source = (String) params[0];
	// mDrawable = (LevelListDrawable) params[1];
	// Log.d(TAG, "doInBackground " + source);
	// try {
	// InputStream is = new URL(source).openStream();
	// return BitmapFactory.decodeStream(is);
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// @Override
	// protected void onPostExecute(Bitmap bitmap) {
	// Log.d(TAG, "onPostExecute drawable " + mDrawable);
	// Log.d(TAG, "onPostExecute bitmap " + bitmap);
	// if (bitmap != null) {
	// @SuppressWarnings("deprecation")
	// BitmapDrawable d = new BitmapDrawable(bitmap);
	// mDrawable.addLevel(1, 1, d);
	// mDrawable
	// .setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
	// mDrawable.setLevel(1);
	// // i don't know yet a better way to refresh TextView
	// // mTv.invalidate() doesn't work as expected
	// CharSequence t = mess.getText();
	// mess.setText(t);
	// }
	// }
	// }
}
