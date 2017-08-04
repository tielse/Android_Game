package chauthoi1010.gmail.com.Quaivat;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import chauthoi1010.gmail.com.Tools.Tools;
import android.os.SystemClock;

public class Quai_1 {
	/* Bien tao hieu ung */
	public AnimatedSprite Quai1_AnimationSp;
	private Scene mScene;
	/* Toc do di chuyen cua quai */
	public int speed = 5;
	/* Doi tuong Quai */
	private Quai quai;

	public Quai_1(Quai quaivat, Scene mScene, float pX, float pY,
			TiledTextureRegion Quai_TiledRe) {
		super();
		// TODO Auto-generated constructor stub
		quai = quaivat;
		this.mScene = mScene;
		Quai1_AnimationSp = new AnimatedSprite(pX, pY, Quai_TiledRe);
		statusMoveLeft();
		mScene.attachChild(Quai1_AnimationSp);
	}

	public void statusMoveUp() {
		Quai1_AnimationSp.animate(new long[] { 100, 100, 100 }, 3, 15, true);
	}

	public void statusMoveRight() {
		Quai1_AnimationSp.animate(new long[] { 100, 100, 100 }, 15, 17, true);
	}

	public void statusMoveDown() {
		Quai1_AnimationSp.animate(new long[] { 100, 100, 100 }, 27, 29, true);
	}

	public void statusMoveLeft() {
		Quai1_AnimationSp.animate(new long[] { 100, 100, 100 }, 39, 41, true);
	}

	public void moveXY(float pX, float pY) {
		if (!quai.collidesWith(pX, pY))
			Quai1_AnimationSp.setPosition(pX, pY);
	}

	public void moveRelativeXY(float pX, float pY) {
		Quai1_AnimationSp.setPosition(Quai1_AnimationSp.getX() + pX,
				Quai1_AnimationSp.getY() + pY);
	}

	/* Di chuyen 1 cach ngau nhien tren man hinh */
	long time = SystemClock.elapsedRealtime();
	int huong = 0;

	public void moveRandom() {
		/* Cach 3 giay quai se tu x/d lai huong di */
		if (SystemClock.elapsedRealtime() - time > 3000) {
			huong = Tools.getRandomIndex(0, 3);
			time = SystemClock.elapsedRealtime();
			if (huong == 0)
				statusMoveLeft();
			else if (huong == 1)
				statusMoveRight();
			else if (huong == 2)
				statusMoveUp();
			else if (huong == 3)
				statusMoveDown();
		}
		if (huong == 0) {
			if (!quai.collidesWith(Quai1_AnimationSp.getX() - speed,
					Quai1_AnimationSp.getY()
							+ (Quai1_AnimationSp.getHeight() / 2)))
				moveRelativeXY(-speed, 0);
		} else if (huong == 1) {
			if (!quai.collidesWith(
					Quai1_AnimationSp.getX() + Quai1_AnimationSp.getWidth(),
					Quai1_AnimationSp.getY()
							+ (Quai1_AnimationSp.getHeight() / 2)))
				moveRelativeXY(speed, 0);
		} else if (huong == 2) {
			if (!quai.collidesWith(Quai1_AnimationSp.getX()
					+ (Quai1_AnimationSp.getWidth() / 2),
					(Quai1_AnimationSp.getY() - speed)))
				moveRelativeXY(0, -speed);
		} else if (huong == 3) {
			if (!quai.collidesWith(Quai1_AnimationSp.getX()
					+ (Quai1_AnimationSp.getWidth() / 2),
					Quai1_AnimationSp.getY() + Quai1_AnimationSp.getHeight()
							+ speed))
				moveRelativeXY(0, speed);
		}
	}

	/* Xoa bo quai */
	public void deleteQuai_1() {
		mScene.detachChild(Quai1_AnimationSp);
	}

	long time_reset_begin = 0;
	public boolean bool_reset = false;

	public void reset() {
		if (bool_reset) {
			if (!Quai1_AnimationSp.isVisible() && time_reset_begin == 0)
				time_reset_begin = SystemClock.elapsedRealtime();
			if (time_reset_begin != 0
					&& SystemClock.elapsedRealtime() - time_reset_begin > 5000) {
				/* Khoi tao lai quai vat */
				Quai1_AnimationSp.setPosition(416, 128);
				Quai1_AnimationSp.setVisible(true);
				time_reset_begin = 0;
				bool_reset = false;
			}
		}
	}
}
