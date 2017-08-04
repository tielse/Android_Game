package chauthoi1010.gmail.com.Boom;

import java.io.IOException;

import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXProperties;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXProperty;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTileProperty;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTiledMap;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;

import android.content.Context;
import android.os.Debug;
import android.os.SystemClock;
import android.util.Log;
import chauthoi1010.gmail.com.Controler.Controler;
import chauthoi1010.gmail.com.InterfaceSprite.InterfaceSprite;
import chauthoi1010.gmail.com.Sound.Sound;

public class QuaBoom implements InterfaceSprite {
	/* Ban dau load qua boom nam vi tri ngoai man hinh -100 -100 */
	public float pX = -100, pY = -100;
	/* Doi tuong boom */
	private Boom bom;
	/* Cap do boom */
	public int cap = 1;
	/* Array Object tao ra vu no khi qua boom dat xuong */
	public Bum[] no;
	/* Xac dinh thoi gian khi dat xuong */
	public long time = 0;
	/* Thoi gian x/d vu no */
	public long time_no = 0;
	/* Ket thuc t/g vu no */
	public boolean end_no = false;
	/* Kich hoat vu no */
	public boolean begin_no = false;
	/* Dung Map xac dinh vung duoc di chuyen hoac khong di chuyen */
	private TMXTiledMap mTMXTiMap;
	private TMXLayer mTMXLayer;
	/* Kich hoat sounds */
	private org.anddev.andengine.audio.sound.Sound sound_no;
	private boolean bool_sound_no = false;

	/* Phuong thuc khoi dung */
	public QuaBoom() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setmTMXTiMap(TMXTiledMap mTMXTiMap) {
		this.mTMXTiMap = mTMXTiMap;
	}

	public void setmTMXLayer(TMXLayer mTMXLayer) {
		this.mTMXLayer = mTMXLayer;
	}

	/*
	 * Kiem tra xem tai vi tri pX,pY co phai thuoc vung di chuyen neu di chuyen
	 * true con khong di chuyen false(Doi voi player quai vat cung vay)
	 */
	public boolean collidesWith(float pX, float pY) {
		TMXTile mTMXTile = mTMXLayer.getTMXTileAt(pX, pY);
		try {
			if (mTMXTile == null)
				System.out.println("Tile=null");
			else {
				TMXProperties<TMXTileProperty> mTMXProtis = mTMXTile
						.getTMXTileProperties(mTMXTiMap);
				TMXProperty mTMXProtys = mTMXProtis.get(0);
				if (mTMXProtys.getName().equals("vatcan")) {

				}
			}
			return true;// Va cham
		} catch (Exception e) {
			// TODO: handle exception
			return false;// Hk? va cham
		}
	}

	/* Khoi tao cac bien mac dinh ban dau vao game */
	public void init() {
		end_no = false;
		time = 0;
		time_no = 0;
		begin_no = false;
	}

	@Override
	public void onLoadResources(Engine mEngine, Context mContext) {
		// TODO Auto-generated method stub
		/* Moi cap co 4 doi tuong no khac nhau,1 Object se nam tai vi tri no */
		no = new Bum[(cap * 4) + 1];
		/* Load Bom,Cho biet vi tri dat boom */
		bom = new Boom(pX, pY);
		bom.onLoadResources(mEngine, mContext);
		/* Cho biet Object xay ra vu no */
		int j = 0;
		int lop = 1;
		for (int i = 0; i < no.length; i++) {
			if (j == 0) {
				/* Right */
				no[i] = new Bum(((bom.Boom_TiledRe.getWidth() / 4) * lop + pX),
						pY);
				no[i].onLoadResources(mEngine, mContext);
			} else if (j == 1) {
				/* Left */
				no[i] = new Bum((pY - (bom.Boom_TiledRe.getWidth() / 4) * lop),
						pY);
				no[i].onLoadResources(mEngine, mContext);
			} else if (j == 2) {
				/* Down */
				no[i] = new Bum(pX, pY - (bom.Boom_TiledRe.getHeight() * lop));
				no[i].onLoadResources(mEngine, mContext);
			} else if (j == 3) {
				no[i] = new Bum(pX, pY + (bom.Boom_TiledRe.getHeight() * lop));
				no[i].onLoadResources(mEngine, mContext);
				lop++;
			}
			j++;
			if (j >= 4)
				j = 0;
		}
		/* Doi tuong cuoi cung dc dat tai vi tri cua Qua Bom */
		no[no.length - 1] = new Bum(pX, pY);
		no[no.length - 1].onLoadResources(mEngine, mContext);

		SoundFactory.setAssetBasePath("mfx/");
		try {
			sound_no = SoundFactory.createSoundFromAsset(
					mEngine.getSoundManager(), mContext, "no1.wav");
		} catch (final IOException e) {
			// TODO: handle exception
			/* Class Debug */
			// Debug.e(e);
			Log.e("Erro", "Erro");
		}
	}

	@Override
	public void onLoadScene(Scene mScene) {
		// TODO Auto-generated method stub
		/* Ban dau hinh anh dieu o trang thai an,Boom */
		bom.onLoadScene(mScene);
		bom.Boom_AnimaSp.setVisible(false);
		/* Bom No xay ra */
		for (int i = 0; i < no.length; i++) {
			no[i].onLoadScene(mScene);
			no[i].Bum_AniSp.setVisible(false);
		}
	}

	/*
	 * Khi 1 qua bom duoc dat xuong thi no se cho trong 3s sau do phat no trong
	 * 1s.Khi no bat dau cho thi time!=0,khi no bat dau no thi time_no!=0 khi no
	 * ket thuc end_no=true.Khi bat dau no begin_no=true
	 */
	public void delayNo() {
		/* Khi bom da no thi hk? goi lai */
		if (end_no)
			return;
		/* Khi bom duoc dat xuong ta bat dau tinh timer */
		if (time == 0)
			time = SystemClock.elapsedRealtime();
		/* Cho du 3s ta bat dau cho no */
		if (SystemClock.elapsedRealtime() - time > 3000) {
			/* Doi voi cac Object thi khong cho hien thi */
			for (int i = 0; i < no.length - 1; i++) {
				if (i >= cap * 4)
					no[i].Bum_AniSp.setVisible(false);
				else {
					if (no[i].visible)
						no[i].Bum_AniSp.setVisible(true);
					else
						no[i].Bum_AniSp.setVisible(false);
				}
			}
			no[no.length - 1].Bum_AniSp.setVisible(true);
			/* Bat dau no */
			begin_no = true;
			if (!bool_sound_no && Controler.SOUND) {
				sound_no.play();
				bool_sound_no = true;
			}
			/* Bat dau tinh thoi gian */
			if (time_no == 0)
				time_no = SystemClock.elapsedRealtime();
			/* Cho phep no trong 1s */
			if (SystemClock.elapsedRealtime() - time_no > 1000) {
				/* Het 1s ta cho an toan bo va cho di chuyen den pX=-100,pY=-100 */
				for (int i = 0; i < no.length; i++) {
					no[i].Bum_AniSp.setVisible(false);
					no[i].BumNewXY(-100, -100);
				}
				bom.Boom_AnimaSp.setPosition(-100, -100);
				bom.Boom_AnimaSp.setVisible(false);
				init();
				end_no = true;
			}
		}
	}

	/* Di chuyen toan bo den vi tri pX,pY */
	public void AllNexXY(float pX, float pY) {
		/* Khoi tao lai cac bien mac dinh */
		init();
		this.pX = pX;
		this.pY = pY;
		/* Khi goi move thi boom se duoc hien thi */
		bom.BoomNewXY(pX, pY);
		int j = 0;
		int lop = 1;
		boolean left = true, right = true, up = true, down = true;
		float x = 0, y = 0;
		for (int i = 0; i < no.length - 1; i++) {
			if (j == 0) {
				/* Right */
				no[i].BumNewXY(((bom.Boom_TiledRe.getWidth() / 4) * lop + pX),
						pY);
				if (!right)
					/* Khong cho hien thi bom tren vat can */
					no[i].visible = false;
				else {
					x = (bom.Boom_TiledRe.getWidth() / 4) * lop + pX
							+ (bom.Boom_TiledRe.getWidth() / 4) / 2;
					y = (bom.Boom_TiledRe.getHeight() / 2) + pY;
					/* Khi goi move toan bo trang thai an */
					if (collidesWith(x, y)) {
						no[i].visible = false;
						right = false;
					} else
						no[i].visible = true;
				}
			} else if (j == 1) {
				/* Left */
				no[i].BumNewXY(pX - (bom.Boom_TiledRe.getWidth() / 4) * lop, pY);
				if (!left)
					no[i].visible = false;
				else {
					x = (pX - (bom.Boom_TiledRe.getWidth() / 4) * lop)
							- (bom.Boom_TiledRe.getWidth() / 4) / 2;
					y = pY + (bom.Boom_TiledRe.getHeight() / 2);
					if (collidesWith(x, y)) {
						no[i].visible = false;
						left = false;
					} else
						no[i].visible = true;
				}
			} else if (j == 2) {
				no[i].BumNewXY(pX, pY - (bom.Boom_TiledRe.getHeight() * lop));
				if (!up)
					no[i].visible = false;
				else {
					x = (bom.Boom_TiledRe.getWidth() / 4) / 2 + pX;
					y = pY - (bom.Boom_TiledRe.getHeight() * lop)
							- (bom.Boom_TiledRe.getHeight() / 2);
					if (collidesWith(x, y)) {
						no[i].visible = false;
						up = false;
					} else
						no[i].visible = true;
				}
			} else if (j == 3) {
				no[i].BumNewXY(pX, pY + (bom.Boom_TiledRe.getHeight() * lop));
				if (!down)
					no[i].visible = false;
				else {
					x = (bom.Boom_TiledRe.getWidth() / 4) / 2 + pX;
					y = pY + (bom.Boom_TiledRe.getHeight() * lop)
							+ (bom.Boom_TiledRe.getHeight() / 2);
					if (collidesWith(x, y)) {
						no[i].visible = false;
						down = false;
					} else
						no[i].visible = true;
				}
				lop++;
			}
			j++;
			if (j >= 4)
				j = 0;
		}
		no[no.length - 1].BumNewXY(pX, pY);
		bool_sound_no = false;
	}

	/*
	 * Kiem tra xem 1 animated co va cham voi no khong.Neu co va cham return
	 * true nguoc lai return false
	 */
	public boolean collidesWith(AnimatedSprite animaSp) {
		for (int i = 0; i < no.length; i++) {
			if (no[i].Bum_AniSp.isVisible()
					&& no[i].Bum_AniSp.collidesWith(animaSp))
				return true; // Xac dinh va cham
		}
		return false; // Khong va cham
	}
}
