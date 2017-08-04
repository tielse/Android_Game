package chauthoi1010.gmail.com.Sound;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class Sound {
	public MediaPlayer mPlayer;
	public boolean mPlaying = false;
	public boolean mLoop = false;
	public boolean complet = false;

	public Sound(Context c, int id) {
		super();
		// TODO Auto-generated constructor stub
		mPlayer = MediaPlayer.create(c, id);
		// Su kien
		mPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mPlaying = false;
				complet = true;
				System.out.println("setOnComplet");
				if (mLoop)
					mp.start();
			}
		});
	}

	public synchronized void play() {
		if (mPlaying)
			return;
		if (mPlayer != null)
			mPlaying = true;
		mPlayer.start();
	}

	public synchronized void replay() {
		if (mPlaying)
			return;
		if (mPlayer != null)
			mPlaying = false;
		mPlayer.pause();
	}

	public synchronized void stop() {
		try {
			mLoop = false;
			if (mPlaying) {
				mPlaying = false;
				mPlayer.pause();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public synchronized void loop() {
		mLoop = true;
		mPlaying = true;
		mPlayer.start();
	}

	public synchronized void replease() {
		if (mPlayer != null) {
			mPlayer.release();
			mPlayer = null;
		}
	}
}
