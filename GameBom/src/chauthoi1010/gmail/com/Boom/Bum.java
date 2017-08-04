package chauthoi1010.gmail.com.Boom;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.content.Context;
import chauthoi1010.gmail.com.InterfaceSprite.InterfaceSprite;

public class Bum implements InterfaceSprite {
	/* Su kien hien thi vu no */
	public boolean visible = true;
	/* Vi tri anh duoc dat */
	public float pX = 0, pY = 0;
	/* Tao ra hien ung vu no */
	public AnimatedSprite Bum_AniSp;
	/* Load anh */
	private TiledTextureRegion Bum_TilRe;
	private BitmapTextureAtlas Bum_TextAt;

	/* Phuong thuc khoi dung */
	public Bum(float pX, float pY) {
		super();
		this.pX = pX;
		this.pY = pY;
	}

	@Override
	public void onLoadResources(Engine mEngine, Context mContext) {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Bom/");
		this.Bum_TextAt = new BitmapTextureAtlas(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.Bum_TilRe = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(this.Bum_TextAt, mContext,
						"fireblack.png", 0, 0, 5, 4);
		mEngine.getTextureManager().loadTexture(this.Bum_TextAt);
	}

	@Override
	public void onLoadScene(Scene mScene) {
		// TODO Auto-generated method stub
		Bum_AniSp = new AnimatedSprite(pX, pY, Bum_TilRe);
		Bum_AniSp.animate(60);
		mScene.attachChild(Bum_AniSp);
	}

	/*
	 * Phuong thuc khoi tao khi Boom duoc dat vi tri moi thi hien thi vu no tai
	 * vi tri moi
	 */
	public void BumNewXY(float X, float Y) {
		pX = X;
		pY = Y;
		Bum_AniSp.setPosition(X, Y);
		Bum_AniSp.setVisible(false);
	}
}
