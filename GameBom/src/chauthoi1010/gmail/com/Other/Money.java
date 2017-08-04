package chauthoi1010.gmail.com.Other;

import java.io.IOException;

import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXProperties;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTileProperty;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTiledMap;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;

import android.content.Context;
import android.os.Debug;
import android.os.SystemClock;
import android.util.Log;
import chauthoi1010.gmail.com.Controler.Controler;
import chauthoi1010.gmail.com.InterfaceSprite.InterfaceSprite;
import chauthoi1010.gmail.com.Player.Player;
import chauthoi1010.gmail.com.Sound.Sound;
import chauthoi1010.gmail.com.Tools.Tools;

public class Money implements InterfaceSprite {

	private BitmapTextureAtlas Money_BitmapTextAt;
	private TextureRegion Money_TextRe;
	private Sprite Money_Sprite;

	private org.anddev.andengine.audio.sound.Sound sound_thuong;

	public Money() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onLoadResources(Engine mEngine, Context mContext) {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Image/");
		Money_BitmapTextAt = new BitmapTextureAtlas(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		Money_TextRe = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				Money_BitmapTextAt, mContext, "money.png", 0, 0);
		mEngine.getTextureManager().loadTexture(Money_BitmapTextAt);
		SoundFactory.setAssetBasePath("mfx/");
		try {
			sound_thuong = SoundFactory.createSoundFromAsset(
					mEngine.getSoundManager(), mContext, "thuong.wav");
		} catch (final IOException e) {
			// TODO: handle exception
			Log.e("Erro", "Loi" + e);
		}
	}

	@Override
	public void onLoadScene(Scene mScene) {
		// TODO Auto-generated method stub
		Money_Sprite = new Sprite(-100, -100, Money_TextRe);
		mScene.attachChild(Money_Sprite);
		mScene.registerTouchArea(Money_Sprite);
	}

	long time_radom = 0, time_start = 0;
	/* T/g hien thi tien va an tien */
	long time_start_t = 0;
	long time_end_t = 0;

	public void hienthi(TMXTiledMap mTMXTiledMap, TMXLayer VatCanTMXLayer) {
		if (time_radom == 0) {
			/* Xac dinh t/g cho de uat hien */
			/* Trong khoang 5-20s se hien thi tien */
			time_radom = Tools.getRandomIndex(5000, 20000);
			time_start = SystemClock.elapsedRealtime();
		} else {
			if (SystemClock.elapsedRealtime() - time_start > time_radom
					&& time_start != 0) {
				/* Hien thi tien,Tim vi tri */
				while (true && time_start_t == 0) {
					int x = Tools.getRandomIndex(192, 416);
					int y = Tools.getRandomIndex(64, 256);
					if (!collidesWith(mTMXTiledMap, VatCanTMXLayer, x
							+ Money_Sprite.getWidth() / 2,
							y + Money_Sprite.getY() / 2)) {
						/* Dc phep hien thi,Di chuyen toi vi tri */
						Money_Sprite.setPosition(x, y);
						/* Xac t/g hien thi money */
						time_start_t = SystemClock.elapsedRealtime();
						/* Hien thi trong vong 3s-10s */
						time_end_t = Tools.getRandomIndex(3000, 1000);
						break;
					}
				}
				Money_Sprite.setRotation(10);
				/* Bat dau tinh t/g hien thi */
				if (time_start_t != 0
						&& SystemClock.elapsedRealtime() - time_start_t > time_end_t) {
					/* Het t/g hien thi tien ta cho di chuyen=-100,-100 */
					Money_Sprite.setPosition(-100, -100);
					/* Tiep do ta x/d lai t/g cho */
					time_radom = Tools.getRandomIndex(5000, 20000);
					time_start = SystemClock.elapsedRealtime();
					time_start_t = 0;
					time_end_t = 0;
				}
			}
		}
	}

	/* Kiem tra su va cham giua tien va Player */
	public void collidesWith(Player player) {
		if (Money_Sprite.getX() > 0
				&& player.getAnimatedSp().collidesWith(Money_Sprite)) {
			if (Controler.SOUND)
				sound_thuong.play();
			/* Co su va cham */
			if (player.min_boom < 10)
				player.setMin_boom(player.getMin_boom() + 1);
			System.out.println("Them boom");
			Money_Sprite.setPosition(-100, -100);
			/* Xac ding lai t/g */
			time_radom = Tools.getRandomIndex(5000, 20000);
			time_start = SystemClock.elapsedRealtime();
		}
	}

	public boolean collidesWith(TMXTiledMap mTMXTileMap,
			TMXLayer VatCanTMXLayer, float pX, float pY) {
		TMXTile mTMXTile = VatCanTMXLayer.getTMXTileAt(pX, pY);
		try {
			if (mTMXTile == null) {

			} else {
				TMXProperties<TMXTileProperty> mTMXProties = mTMXTile
						.getTMXTileProperties(mTMXTileMap);
				TMXTileProperty mTMXTileProty = mTMXProties.get(0);
				if (mTMXTileProty.getName().equals("vatcan")) {

				}
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
