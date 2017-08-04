package chauthoi1010.gmail.com.Player;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.content.Context;
import chauthoi1010.gmail.com.Boom.QuaBoom;
import chauthoi1010.gmail.com.InterfaceSprite.InterfaceSprite;

public class Player implements InterfaceSprite {
	/* Trang thai nhan vat */
	private int STATUS_PLAYER = StatusPlayer.Un_Move_Down;
	/* Tao hieu ung di chuyen */
	public AnimatedSprite player_AnimationSp;
	/* Load anh */
	private TiledTextureRegion player_TiledRegion;
	private BitmapTextureAtlas player_BitmapAtlas;
	/* HP */
	private int heart = 1;
	/* Vi tri player */
	private float pos_X = 0;
	private float pos_Y = 0;
	/* Tong so qua boom player co duoc 10 */
	private int max_boom = 10;
	/* Mac dinh so boom la 1 */
	public int min_boom = 1;
	/* Max cap do no cua lua la 5 */
	private int max_cap_boom = 5;
	/* Min cap do no cua lua la 1 */
	public int min_cap_boom = 1;
	/* Danh sach qua boom,Khai bao mang so luong toi da cua bom */
	public QuaBoom[] MyQBom;

	/*
	 * Phuong thuc khoi dung max_boom:Tang so qua bom ma player co duoc
	 * min_boom:So luong qua bom player hien co.Mac dinh la 1 max_cap_boom:Cap
	 * do lon nhat ma 1 qua boom co the dat duoc min_cap_boom:Cap do boom hien
	 * tai cua Player.Mac dinh la cap 1
	 */
	public Player(int max_boom, int min_boom, int max_cap_boom, int min_cap_boom) {
		super();
		this.max_boom = max_boom;
		this.min_boom = min_boom;
		this.max_cap_boom = max_cap_boom;
		this.min_cap_boom = min_cap_boom;
		MyQBom = new QuaBoom[max_boom];
	}

	/* Load Sound,Image.... */
	@Override
	public void onLoadResources(Engine mEngine, Context mContext) {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Player/");
		player_BitmapAtlas = new BitmapTextureAtlas(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		player_TiledRegion = BitmapTextureAtlasTextureRegionFactory
				.createTiledFromAsset(player_BitmapAtlas, mContext,
						"herored.png", 0, 0, 4, 1);
		mEngine.getTextureManager().loadTexture(player_BitmapAtlas);
		/* Thuc hien load toan bo qua boom */
		for (int i = 0; i < max_boom; i++) {
			MyQBom[i] = new QuaBoom();
			/* Ban dau ta load toan bo bom voi cap do la max */
			MyQBom[i].cap = max_cap_boom;
			MyQBom[i].onLoadResources(mEngine, mContext);
			/* Khi ta load xong thi ta dat lai cap bom */
			MyQBom[i].cap = min_cap_boom;
		}
	}

	/* Hien thi len monitor */
	@Override
	public void onLoadScene(Scene mScene) {
		// TODO Auto-generated method stub
		/* Dat vi tri ban dau cua Player */
		player_AnimationSp = new AnimatedSprite(pos_X, pos_Y,
				player_TiledRegion);
		/*
		 * Phuong thuc nay cho biet Player se hien thi lan dau tien voi trang
		 * thai gi
		 */
		showPlayerStatus();
		mScene.attachChild(player_AnimationSp);
		/* Ban dau load toan bo cho nam ngoai man hinh */
		for (int i = 0; i < max_boom; i++) {
			/* Dat toan bo qua bom ngoai man hinh */
			MyQBom[i].onLoadScene(mScene);
		}
	}

	public float getPos_X() {
		return pos_X;
	}

	public void setPos_X(float pos_X) {
		this.pos_X = pos_X;
	}

	public float getPos_Y() {
		return pos_Y;
	}

	public void setPos_Y(float pos_Y) {
		this.pos_Y = pos_Y;
	}

	public void setPos_XY(float posX, float posY) {
		pos_X = posX;
		pos_Y = posY;
	}

	public int getSTATUS_PLAYER() {
		return Player.this.STATUS_PLAYER;
	}

	public void setSTATUS_PLAYER(int sTATUS_PLAYER) {
		Player.this.STATUS_PLAYER = sTATUS_PLAYER;
		/*
		 * Neu setStatusPlayer thi ta se cap nhat xem player se hien thi
		 * Animation nao
		 */
		showPlayerStatus();
	}

	private void showPlayerStatus() {
		switch (STATUS_PLAYER) {
		case StatusPlayer.Move_Left: {
			player_AnimationSp.animate(new long[] { 100, 100, 100 }, new int[] {
					66, 28, 88 }, 1000);
		}
			break;
		case StatusPlayer.Move_right: {
			player_AnimationSp.animate(new long[] { 100, 100, 100 }, new int[] {
					1, 28, 22 }, 1000);
		}
			break;
		case StatusPlayer.Move_Up: {
			player_AnimationSp.animate(new long[] { 100, 100, 100 }, new int[] {
					22, 28, 44 }, 1000);
		}
			break;
		case StatusPlayer.Move_Down: {
			player_AnimationSp.animate(new long[] { 100, 100, 100 }, new int[] {
					44, 28, 66 }, 1000);
		}
			break;
		case StatusPlayer.Un_Move_Left: {
			player_AnimationSp.animate(new long[] { 100, 100 }, new int[] { 21,
					21 }, 2);
		}
			break;
		case StatusPlayer.Un_Move_right: {
			player_AnimationSp.animate(new long[] { 100, 100 }, new int[] { 4,
					4 }, 2);
		}
			break;
		case StatusPlayer.Un_Move_Up: {
			player_AnimationSp.animate(new long[] { 100, 100 }, new int[] { 3,
					3 }, 2);
		}
			break;
		case StatusPlayer.Un_Move_Down: {
			player_AnimationSp.animate(new long[] { 100, 100 }, new int[] { 14,
					14 }, 2);
		}
			break;
		}
	}

	/* Di chuyen toi vi tri X */
	public void moveX(float moveX) {
		pos_X = moveX;
		movePlayer();
	}

	/* Di chuyen toi vi tri Y */
	public void moveY(float moveY) {
		pos_Y = moveY;
		movePlayer();
	}

	/* Di chuyen toi vi tri XY */
	public void moveXY(float moveX, float moveY) {
		pos_X = moveX;
		pos_Y = moveY;
		movePlayer();
	}

	/* Di chuyen Player toi vi tri hien tai X */
	public void moveRelativeX(float moveRelativeX) {
		pos_X += moveRelativeX;
		movePlayer();
	}

	/* Di chuyen Player toi vi tri hien tai Y */
	public void moveRelativeY(float moveRelativeY) {
		pos_Y += moveRelativeY;
		movePlayer();
	}

	/* Di chuyen Player toi vi tri hien tai XY */
	public void moveRelativeXY(float moveRelativeX, float moveRelativeY) {
		pos_X += moveRelativeX;
		pos_Y += moveRelativeY;
		movePlayer();
	}

	/* Di chuyen Player */
	public void movePlayer() {
		player_AnimationSp.setPosition(pos_X, pos_Y);
	}

	public int getHeart() {
		return heart;
	}

	public void setHeart(int heart) {
		this.heart = heart;
	}

	public int getMin_boom() {
		return min_boom;
	}

	public void setMin_boom(int min_boom) {
		this.min_boom = min_boom;
	}

	public int getMin_cap_boom() {
		return min_cap_boom;
	}

	public void setMin_cap_boom(int min_cap_boom) {
		this.min_cap_boom = min_cap_boom;
		for (int i = 0; i < max_boom; i++) {
			MyQBom[i].cap = this.min_cap_boom;
		}
	}

	public AnimatedSprite getAnimatedSp() {
		return player_AnimationSp;
	}

}
