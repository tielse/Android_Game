package chauthoi1010.gmail.com.Dialog;

import chauthoi1010.gmail.com.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity_DialogInfor extends Dialog {
	private Button close;

	public MainActivity_DialogInfor(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.infor_activity);
		data();
	}

	private void data() {
		close = (Button) findViewById(R.id.button_thongtin);
		close.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
	}
}
