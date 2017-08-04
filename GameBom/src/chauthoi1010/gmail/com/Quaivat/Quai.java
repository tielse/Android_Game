package chauthoi1010.gmail.com.Quaivat;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXProperties;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTileProperty;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTiledMap;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.content.Context;
import chauthoi1010.gmail.com.InterfaceSprite.InterfaceSprite;
import chauthoi1010.gmail.com.Tools.Tools;

public class Quai implements InterfaceSprite {
	/* Xac dinh toi da so quai hien co */
	public int max_quai = 10;
	/* Cap phat array chua so luong quai */
	public Quai_1[] quai_1;
	/* Cac bien luu va load anh */
	private TiledTextureRegion[] Quai_TiledRe;
	private BitmapTextureAtlas Quai_Bitmap_At;
	/* Map */
	private TMXTiledMap mTMXMap;
	private TMXLayer Vatcan_Layer;
	private Scene mScene;

	public Quai(int max_quai) {
		super();
		this.max_quai = max_quai;
		quai_1 = new Quai_1[max_quai];
		Quai_TiledRe = new TiledTextureRegion[max_quai];
	}

	@Override
	public void onLoadResources(Engine mEngine, Context mContext) {
		// TODO Auto-generated method stub
		Quai_Bitmap_At = new BitmapTextureAtlas(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Quaivat/");
		for (int i = 0; i < max_quai; i++) {
			Quai_TiledRe[i] = BitmapTextureAtlasTextureRegionFactory
					.createTiledFromAsset(Quai_Bitmap_At, mContext, "linh.png",
							0, 0, 4, 1);
			mEngine.getTextureManager().loadTexture(Quai_Bitmap_At);
		}
	}

	@Override
	public void onLoadScene(Scene mScene) {
		// TODO Auto-generated method stub
		this.mScene = mScene;
		for (int i = 0; i < max_quai; i++) {
			quai_1[i] = new Quai_1(this, mScene, -100, -100, Quai_TiledRe[i]);
		}
	}

	/* Khoi tao vi tri ngau nhien */
	public void reset() {
		for (int i = 0; i < max_quai; i++) {
			while (true) {
				int x = Tools.getRandomIndex(192, 416);
				int y = Tools.getRandomIndex(64, 256);
				if (!collidesWith(x, y)) {
					quai_1[i] = new Quai_1(this, mScene, x, y, Quai_TiledRe[i]);
					break;
				}
			}
		}
	}

	public void setmTMXMap(TMXTiledMap mTMXMap) {
		this.mTMXMap = mTMXMap;
	}

	public void setVatcan_Layer(TMXLayer vatcan_Layer) {
		Vatcan_Layer = vatcan_Layer;
	}

	/*
	 * Kiem tra vi tri vung pX,pY co phai vung khong duoc di chuyen.Neu vung
	 * khong thuoc di chuyen=true nguoc lai dichuyen=false
	 */
	public boolean collidesWith(float pX, float pY) {
		TMXTile mTMXTile = Vatcan_Layer.getTMXTileAt(pX, pY);
		try {
			if (mTMXTile == null)
				System.out.println("mTMXTile=null");
			else {
				TMXProperties<TMXTileProperty> mTMXProties = mTMXTile
						.getTMXTileProperties(mTMXMap);
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

	/* Khi quai chet thi cho trong khoang 5s se duoc thuc hien lai */
	public void reset(Quai_1 quai_vat_1) {
		quai_vat_1.Quai1_AnimationSp.setVisible(true);
		quai_vat_1.Quai1_AnimationSp.setPosition(416, 128);
	}
}
