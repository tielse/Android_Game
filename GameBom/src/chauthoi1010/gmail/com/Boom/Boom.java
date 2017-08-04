package chauthoi1010.gmail.com.Boom;

import javax.microedition.khronos.opengles.GL10;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.ITexture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import chauthoi1010.gmail.com.MainActivity_Item;
import chauthoi1010.gmail.com.InterfaceSprite.InterfaceSprite;

public class Boom implements InterfaceSprite {
	/* Vi tri cua boom */
	public float pX = -100;
	public float pY = -100;
	// int tmp;
	// int black = 0, yellow = 1, red = 2, blue = 3, green = 4;
	/* Animated Tao hieu ung */
	public AnimatedSprite Boom_AnimaSp;
	/* Bien load image vao bo nho */
	public TiledTextureRegion Boom_TiledRe;
	public BitmapTextureAtlas Boom_BitmapAt;

	/* Phuong thuc khoi tao */
	public Boom(float pX, float pY) {
		super();
		this.pX = pX;
		this.pY = pY;
	}

	@Override
	public void onLoadResources(Engine mEngine, Context mContext) {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Bom/");
		this.Boom_BitmapAt = new BitmapTextureAtlas(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.Boom_TiledRe = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.Boom_BitmapAt, mContext,
						"boomblack.png", 0, 0, 4, 1);
		mEngine.getTextureManager().loadTexture(this.Boom_BitmapAt);
		// switch (tmp) {
		// case 0:
		// Boom_TiledRe=BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(Boom_BitmapAt,
		// mContext, "boomblack.png", 0, 0, 4, 1);
		// break;
		// case 1:
		// Boom_TiledRe=BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(Boom_BitmapAt,
		// mContext, "boomorange.png", 0, 0, 4, 1);
		// break;
		// case 2:
		// Boom_TiledRe=BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(Boom_BitmapAt,
		// mContext, "boomred.png", 0, 0, 4, 1);
		// break;
		// case 3:
		// Boom_TiledRe=BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(Boom_BitmapAt,
		// mContext, "boomblue.png", 0, 0, 4, 1);
		// break;
		// case 4:
		// Boom_TiledRe=BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(Boom_BitmapAt,
		// mContext, "boomgreen.png", 0, 0, 4, 1);
		// break;
		// default:
		// break;
		// }
		// mEngine.getTextureManager().loadTexture(Boom_BitmapAt);
	}

	@Override
	public void onLoadScene(Scene mScene) {
		// TODO Auto-generated method stub
		Boom_AnimaSp = new AnimatedSprite(pX, pY, Boom_TiledRe);
		Boom_AnimaSp.animate(100);
		mScene.attachChild(Boom_AnimaSp);
	}

	/* Phuong thuc khoi tao vi tri moi khi dat boom */
	public void BoomNewXY(float X, float Y) {
		pX = X;
		pY = Y;
		Boom_AnimaSp.setPosition(X, Y);
		Boom_AnimaSp.setVisible(true);
	}
}
