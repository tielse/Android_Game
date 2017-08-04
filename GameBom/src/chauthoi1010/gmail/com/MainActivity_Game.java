package chauthoi1010.gmail.com;

import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.DigitalOnScreenControl;
import org.anddev.andengine.engine.camera.hud.controls.BaseOnScreenControl.IOnScreenControlListener;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.handler.timer.ITimerCallback;
import org.anddev.andengine.engine.handler.timer.TimerHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXProperties;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTileProperty;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTiledMap;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.Scene.IOnSceneTouchListener;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.menu.MenuScene;
import org.anddev.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.anddev.andengine.entity.scene.menu.item.IMenuItem;
import org.anddev.andengine.entity.scene.menu.item.TextMenuItem;
import org.anddev.andengine.entity.scene.menu.item.decorator.ColorMenuItemDecorator;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.font.FontFactory;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.Toast;
import chauthoi1010.gmail.com.BaseEngine.MultiTouch;
import chauthoi1010.gmail.com.BaseEngine.MultiTouchController;
import chauthoi1010.gmail.com.Boom.QuaBoom;
import chauthoi1010.gmail.com.Controler.Controler;
import chauthoi1010.gmail.com.Controler.Screen;
import chauthoi1010.gmail.com.Database.Database;
import chauthoi1010.gmail.com.Dialog.MainActivity_DialogExit;
import chauthoi1010.gmail.com.Map.Map;
import chauthoi1010.gmail.com.Other.Money;
import chauthoi1010.gmail.com.Player.Player;
import chauthoi1010.gmail.com.Player.StatusPlayer;
import chauthoi1010.gmail.com.Quaivat.Quai;
import chauthoi1010.gmail.com.Sound.Sound;

public class MainActivity_Game extends BaseGameActivity implements
		IOnMenuItemClickListener {

	private Player mPlayer;
	public ArrayList<QuaBoom> Arr_QuaBom = new ArrayList<QuaBoom>();
	private Quai mQuai;
	private Money mMoney;
	/* Map */
	private TMXTiledMap mTMXMap;
	private TMXLayer mTMXLayer;
	/* Camera */
	private Camera mCamera;
	private Scene mScene;
	private Scene mSceneLoading;
	private DigitalOnScreenControl digitalControl;
	private int status_digitalControl = -1;
	private BitmapTextureAtlas mOnScreenCotrolTexture;
	private TextureRegion mOnScreenBaseTextRe;
	private TextureRegion mOnScreenKnobTextRe;
	private BitmapTextureAtlas IconBomBitTexAt;
	private TextureRegion IconBomTextRe;
	private Sprite IconBomSprite;
	private BitmapTextureAtlas CapBomBitmapTextAt;
	private TextureRegion CapBomTextRe;
	private Sprite CapBomSprite;
	private ChangeableText TextCapBom;
	private BitmapTextureAtlas TiepTucBitmapTextAt;
	private TextureRegion TiepTucTextRe;
	private Sprite TiepTucSprite;
	private BitmapTextureAtlas HeartBitmapTextAt;
	private TextureRegion HeartTextRe;
	private Sprite HeartSprite;
	private ChangeableText TextHeart;
	private BitmapTextureAtlas PauseTextAt;
	private TextureRegion PauseTextRe;
	private Sprite PauseSprite;
	private BitmapTextureAtlas SoudBitmapTextAt;
	private TextureRegion SoundOnTextRe;
	private TextureRegion SoundOffTextRe;
	private Sprite SoundOnSprite;
	private Sprite SoundOffSprite;
	private ChangeableText min_quabom_ChangeText;
	private Font mFont;
	private BitmapTextureAtlas mFonTextAt;
	private int DIEM = 0;
	private int diem1 = 0;
	private ChangeableText TextDiem;
	private ChangeableText TextLevel;
	private BitmapTextureAtlas ABBitmapTextAt;
	private TextureRegion ATextRe;
	private TextureRegion BTextRe;
	private Sprite A, B;
	protected static final int CONTINUE = 0;
	protected static final int NEWGAME = CONTINUE + 1;
	protected static final int MAINMENU = NEWGAME + 1;
	protected static final int QUIT = MAINMENU + 1;
	protected MenuScene mMenuScene;
	private Database db = new Database(this);
	private boolean OVERGAME = false;
	private boolean WIN = false;
	private boolean NEXT_LEVEL = false;
	org.anddev.andengine.audio.sound.Sound beep;
	private org.anddev.andengine.audio.sound.Sound outch;
	private org.anddev.andengine.audio.sound.Sound sound_resetplayer;
	private int LEVEL = 1;
	private int MAX_SO_QUAI = 5;
	private int SO_QUAI_CAN_TIEU_DIET = 10;
	private int DEM_SO_QUAI_TIEU_DIET = 0;
	/* MAP */
	private String TEN_MAPS = "";
	/* Player */
	private int MAX_QUABOM = 10;
	private int MIN_QUABOM = 1;
	private int MAX_CAP_QUABOM = 5;
	private int MIN_CAP_QUABOM = 1;
	private int HEART = 3;

	@Override
	public Engine onLoadEngine() {
		// TODO Auto-generated method stub
		Bundle b = getIntent().getExtras();
		if (b != null) {
			LEVEL = b.getInt("LEVEL");
			MAX_SO_QUAI = b.getInt("MAX_SO_QUAI");
			SO_QUAI_CAN_TIEU_DIET = b.getInt("SO_QUAI_CAN_TIEU_DIET");
			TEN_MAPS = b.getString("TEN_MAPS");
			MIN_QUABOM = b.getInt("MIN_QUABOM");
			MIN_CAP_QUABOM = b.getInt("MIN_CAP_QUABOM");
			DIEM = b.getInt("DIEM");
			HEART = b.getInt("HEART");
		} else {
			LEVEL = 1;
			MAX_SO_QUAI = 5;
			SO_QUAI_CAN_TIEU_DIET = 5;
			TEN_MAPS = "maps_1.tmx";
		}
		DEM_SO_QUAI_TIEU_DIET = 0;
		mPlayer = new Player(MAX_QUABOM, MIN_QUABOM, MAX_CAP_QUABOM,
				MIN_CAP_QUABOM);
		mPlayer.setMin_boom(MIN_QUABOM);
		mPlayer.setHeart(HEART);
		mQuai = new Quai(MAX_SO_QUAI);
		mMoney = new Money();
		mCamera = new Camera(0, 0, Screen.WIDTH, Screen.HEIGTH);
		Engine engine = new Engine(new EngineOptions(true,
				ScreenOrientation.LANDSCAPE, new RatioResolutionPolicy(
						Screen.WIDTH, Screen.HEIGTH), mCamera).setNeedsSound(
				true).setNeedsMusic(true));
		try {
			if (MultiTouch.isSupported(this)) {
				engine.setTouchController(new MultiTouchController());
				if (MultiTouch.isSupportDistinct(this)) {

				} else {

				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return engine;
	}

	/* Xu Ly Phan Loading */
	private BitmapTextureAtlas BoomLoadingTextAt;
	private TextureRegion BoomLoadingTextRe;
	private Sprite BoomLoadingSprite;

	@Override
	public void onLoadResources() {
		// TODO Auto-generated method stub
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Bom/");
		this.BoomLoadingTextAt = new BitmapTextureAtlas(512, 512,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.BoomLoadingTextRe = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.BoomLoadingTextAt, this,
						"nen_loading.png", 0, 0);
		this.mEngine.getTextureManager().loadTextures(this.BoomLoadingTextAt);
	}

	@Override
	public Scene onLoadScene() {
		// TODO Auto-generated method stub
		mSceneLoading = new Scene();
		mSceneLoading.setBackgroundEnabled(false);
		BoomLoadingSprite = new Sprite(0, 0, BoomLoadingTextRe);
		mSceneLoading.attachChild(BoomLoadingSprite);
		return mSceneLoading;
	}

	@Override
	public void onLoadComplete() {
		// TODO Auto-generated method stub
		mEngine.registerUpdateHandler(new TimerHandler(0.01f,
				new ITimerCallback() {
					public void onTimePassed(TimerHandler pTimerHandler) {
						mEngine.unregisterUpdateHandler(pTimerHandler);
						loadResources();
						loadScenes();
						mEngine.setScene(mScene);
					}
				}));
	}

	public void loadResources() {
		/* Load Player */
		mPlayer.onLoadResources(MainActivity_Game.this.mEngine,
				MainActivity_Game.this);
		/* Load Quai */
		mQuai.onLoadResources(MainActivity_Game.this.mEngine,
				MainActivity_Game.this);
		/* Load Money */
		mMoney.onLoadResources(MainActivity_Game.this.mEngine,
				MainActivity_Game.this);
		/* Load phan Base+Knob+Click */
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Control/");
		mOnScreenCotrolTexture = new BitmapTextureAtlas(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		mOnScreenBaseTextRe = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(mOnScreenCotrolTexture, this,
						"control_base.png", 0, 0);
		mOnScreenKnobTextRe = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(mOnScreenCotrolTexture, this,
						"control_knob.png", 128, 0);
		mEngine.getTextureManager().loadTexture(mOnScreenCotrolTexture);
		/* Load Phim Click */
		IconBomBitTexAt = new BitmapTextureAtlas(64, 64,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		IconBomTextRe = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				IconBomBitTexAt, this, "control_put.png", 0, 0);
		mEngine.getTextureManager().loadTexture(IconBomBitTexAt);
		/* Load Pause */
		PauseTextAt = new BitmapTextureAtlas(32, 32,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		PauseTextRe = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				PauseTextAt, this, "pause.png", 0, 0);
		mEngine.getTextureManager().loadTexture(PauseTextAt);
		/* Load Sound */
		this.SoudBitmapTextAt = new BitmapTextureAtlas(64, 32,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		this.SoundOnTextRe = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.SoudBitmapTextAt, this, "sound_on.png",
						0, 0);
		this.SoundOffTextRe = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(this.SoudBitmapTextAt, this, "sound_off.png",
						32, 0);
		this.mEngine.getTextureManager().loadTextures(this.SoudBitmapTextAt);
		/* Load Heart */
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Item/");
		HeartBitmapTextAt = new BitmapTextureAtlas(32, 32,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		HeartTextRe = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				HeartBitmapTextAt, this, "cachua.png", 0, 0);
		mEngine.getTextureManager().loadTexture(HeartBitmapTextAt);
		/* Load Cap Boom */
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Image/");
		CapBomBitmapTextAt = new BitmapTextureAtlas(32, 32,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		CapBomTextRe = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				CapBomBitmapTextAt, this, "capbom.png", 0, 0);
		mEngine.getTextureManager().loadTexture(CapBomBitmapTextAt);
		/* Next */
		TiepTucBitmapTextAt = new BitmapTextureAtlas(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		TiepTucTextRe = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				TiepTucBitmapTextAt, this, "next.png", 0, 0);
		mEngine.getTextureManager().loadTexture(TiepTucBitmapTextAt);
		/* A&&B */
		ABBitmapTextAt = new BitmapTextureAtlas(32, 32,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		ATextRe = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				ABBitmapTextAt, this, "A.png", 0, 0);
		BTextRe = BitmapTextureAtlasTextureRegionFactory.createFromAsset(
				ABBitmapTextAt, this, "B.png", 16, 0);
		mEngine.getTextureManager().loadTexture(ABBitmapTextAt);
		/* Load Font */
		mFonTextAt = new BitmapTextureAtlas(256, 256,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		FontFactory.setAssetBasePath("font/");
		mFont = FontFactory.createFromAsset(mFonTextAt, this, "UVNHanViet.TTF",
				34, true, Color.RED);
		mEngine.getTextureManager().loadTexture(mFonTextAt);
		mEngine.getFontManager().loadFont(mFont);
		SoundFactory.setAssetBasePath("mfx/");
		try {
			beep = SoundFactory.createSoundFromAsset(mEngine.getSoundManager(),
					this, "iFLogInAlert.wav");
			outch = SoundFactory.createSoundFromAsset(
					mEngine.getSoundManager(), this, "outch.wav");
			sound_resetplayer = SoundFactory.createSoundFromAsset(
					mEngine.getSoundManager(), this, "resetplayer.wav");

		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Erro", "Debug " + e);
		}
	}

	@SuppressLint("ShowToast")
	public void loadScenes() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		mScene = new Scene();
		mScene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));
		mScene.setOnAreaTouchTraversalFrontToBack();
		mScene.setOnSceneTouchListener(SceneTouchLis);
		mScene.setTouchAreaBindingEnabled(true);
		mScene.registerUpdateHandler(UpdtHandler);
		/* Load Maps */
		mTMXMap = Map.getTMXTiledMap(mScene, mEngine, this, TEN_MAPS,this);
		ArrayList<TMXLayer> mapLayers = mTMXMap.getTMXLayers();
		for (TMXLayer layer : mapLayers) {
			if (layer.getName().equals("vatcan")) {
				mTMXLayer = layer;
				System.out.println("Dung phai vat can");
				continue;
			}
			mScene.attachChild(layer);
		}
		/* onLoadScene Player */
		/* Vi tri bat dau vao man choi */
		mPlayer.setPos_XY(64, 64);
		mPlayer.onLoadScene(mScene);
		mPlayer.setMin_cap_boom(MIN_CAP_QUABOM);
		for (int i = 0; i < mPlayer.MyQBom.length; i++) {
			mPlayer.MyQBom[i].setmTMXLayer(mTMXLayer);
			mPlayer.MyQBom[i].setmTMXTiMap(mTMXMap);
		}
		/* onLoadScene Quai */
		mQuai.onLoadScene(mScene);
		mQuai.setmTMXMap(mTMXMap);
		mQuai.setVatcan_Layer(mTMXLayer);
		mQuai.reset();
		mMoney.onLoadScene(mScene);
		/* Xu ly di chuyen nhan vat Player */
		digitalControl = new DigitalOnScreenControl(0, Screen.HEIGTH
				- mOnScreenBaseTextRe.getHeight(), mCamera,
				mOnScreenBaseTextRe, mOnScreenKnobTextRe, 0.1f,
				new IOnScreenControlListener() {

					float pX = 0, pY = 0;

					@Override
					public void onControlChange(
							BaseOnScreenControl pBaseOnScreenControl,
							float pValueX, float pValueY) {
						// TODO Auto-generated method stub
						if (pValueX > 0
								&& mPlayer.getSTATUS_PLAYER() != StatusPlayer.Move_right) {
							mPlayer.setSTATUS_PLAYER(StatusPlayer.Move_right);
							status_digitalControl = StatusPlayer.Move_right;
							pX = mPlayer.player_AnimationSp.getWidth()
									+ mPlayer.getPos_X();
							pY = mPlayer.getPos_Y()
									+ (mPlayer.player_AnimationSp.getHeight() / 2);
						} else if (pValueX < 0
								&& mPlayer.getSTATUS_PLAYER() != StatusPlayer.Move_Left) {
							mPlayer.setSTATUS_PLAYER(StatusPlayer.Move_Left);
							status_digitalControl = StatusPlayer.Move_Left;
							pX = mPlayer.getPos_X();
							pY = mPlayer.getPos_Y()
									+ (mPlayer.player_AnimationSp.getHeight() / 2);
						} else if (pValueY > 0
								&& mPlayer.getSTATUS_PLAYER() != StatusPlayer.Move_Down) {
							mPlayer.setSTATUS_PLAYER(StatusPlayer.Move_Down);
							status_digitalControl = StatusPlayer.Move_Down;
							pX = mPlayer.getPos_X()
									+ (mPlayer.player_AnimationSp.getWidth() / 2);
							pY = mPlayer.getPos_Y()
									+ mPlayer.player_AnimationSp.getHeight();
						} else if (pValueY < 0
								&& mPlayer.getSTATUS_PLAYER() != StatusPlayer.Move_Up) {
							mPlayer.setSTATUS_PLAYER(StatusPlayer.Move_Up);
							status_digitalControl = StatusPlayer.Move_Up;
							pX = mPlayer.getPos_X()
									+ (mPlayer.player_AnimationSp.getWidth() / 2);
							pY = mPlayer.getPos_Y();
						} else {
							switch (status_digitalControl) {
							case StatusPlayer.Move_right:
								mPlayer.setSTATUS_PLAYER(StatusPlayer.Move_right);
								break;
							case StatusPlayer.Move_Left:
								mPlayer.setSTATUS_PLAYER(StatusPlayer.Move_Left);
								break;
							case StatusPlayer.Move_Up:
								mPlayer.setSTATUS_PLAYER(StatusPlayer.Move_Up);
								break;
							case StatusPlayer.Move_Down:
								mPlayer.setSTATUS_PLAYER(StatusPlayer.Move_Down);
								break;

							default:
								break;
							}
						}
						if (pValueX != 0 || pValueY != 0) {
							TMXTile mTMXTile = mTMXLayer.getTMXTileAt(pX
									+ pValueX * 7, pY + pValueY * 7);
							try {
								if (mTMXTile == null) {

								} else {
									TMXProperties<TMXTileProperty> mTMXProties = mTMXTile
											.getTMXTileProperties(mTMXMap);
									TMXTileProperty mTMXProty = mTMXProties
											.get(0);
									if (mTMXProty.getName().equals("vatcan")) {

									}
								}
							} catch (Exception e) {
								// TODO: handle exception
								mPlayer.moveRelativeXY(pValueX * 7, pValueY * 7);
							}
						}
					}
				});
		digitalControl.getControlBase().setBlendFunction(GL10.GL_SRC_ALPHA,
				GL10.GL_ONE_MINUS_SRC_ALPHA);
		digitalControl.getControlBase().setAlpha(0.7f);
		digitalControl.getControlBase().setScaleCenter(0, 128);
		digitalControl.getControlBase().setScale(0.8f);
		digitalControl.getControlKnob().setScale(0.8f);
		digitalControl.refreshControlKnobPosition();

		mScene.setChildScene(digitalControl);
		IconBomSprite = new Sprite(Screen.WIDTH - IconBomTextRe.getWidth(),
				Screen.HEIGTH - IconBomTextRe.getHeight(), this.IconBomTextRe) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					if (mPlayer.min_boom - Arr_QuaBom.size() != 0
							&& Controler.SOUND)
						beep.play();
					if (Arr_QuaBom.size() >= mPlayer.MyQBom.length
							|| Arr_QuaBom.size() >= mPlayer.min_boom)
						return true;
					for (QuaBoom quabom : mPlayer.MyQBom) {
						if (quabom.time == 0) {
							quabom.AllNexXY(
									mPlayer.player_AnimationSp.getWidth(),
									mPlayer.player_AnimationSp.getHeight());
							Arr_QuaBom.add(quabom);
							break;
						}
					}
				}
				return true;
			}
		};
		IconBomSprite.setAlpha(0.5f);
		mScene.attachChild(IconBomSprite);
		mScene.registerTouchArea(IconBomSprite);
		CapBomSprite = new Sprite(Screen.WIDTH - CapBomTextRe.getWidth() - 5,
				Screen.HEIGTH - IconBomSprite.getHeight()
						- CapBomTextRe.getHeight(), CapBomTextRe);
		mScene.attachChild(CapBomSprite);
		mScene.registerTouchArea(CapBomSprite);
		TextCapBom = new ChangeableText(Screen.WIDTH - CapBomTextRe.getWidth()
				- 5, Screen.HEIGTH - IconBomSprite.getHeight()
				- CapBomTextRe.getHeight() - 5, mFont, String.valueOf(mPlayer
				.getMin_cap_boom()), 2);
		mScene.attachChild(TextCapBom);
		PauseSprite = new Sprite(Screen.WIDTH - 32, 1, PauseTextRe) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				if (Controler.SOUND) {
					outch.play();
					mScene.setChildScene(mMenuScene, false, true, true);
				}
				return true;
			}

		};
		PauseSprite.setAlpha(0.5f);
		mScene.attachChild(PauseSprite);
		mScene.registerTouchArea(PauseSprite);
		SoundOnSprite = new Sprite(Screen.WIDTH - 64, 1, SoundOnTextRe) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					if (Controler.SOUND)
						outch.play();
					Controler.SOUND = !Controler.SOUND;
					SoundOffSprite.setPosition(Screen.WIDTH - 64, 1);
					SoundOnSprite.setPosition(-100, -100);
				}

				return true;
			}
		};
		SoundOnSprite.setAlpha(0.5f);
		mScene.attachChild(SoundOnSprite);
		mScene.registerTouchArea(SoundOnSprite);

		SoundOffSprite = new Sprite(Screen.WIDTH - 64, 1, SoundOffTextRe) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					if (Controler.SOUND)
						outch.play();
					Controler.SOUND = !Controler.SOUND;
					SoundOnSprite.setPosition(Screen.WIDTH - 64, 1);
					SoundOffSprite.setPosition(-100, -100);
				}
				return true;
			}
		};
		SoundOffSprite.setAlpha(0.5f);
		mScene.attachChild(SoundOffSprite);
		mScene.registerTouchArea(SoundOffSprite);
		if (Controler.SOUND) {
			SoundOnSprite.setPosition(Screen.WIDTH - 64, 1);
			SoundOffSprite.setPosition(-100, -100);
		} else {
			SoundOffSprite.setPosition(Screen.WIDTH - 64, 1);
			SoundOnSprite.setPosition(-100, -100);
		}
		HeartSprite = new Sprite(Screen.WIDTH / 2 - HeartTextRe.getWidth() / 2,
				0, HeartTextRe);
		mScene.attachChild(HeartSprite);
		mScene.registerTouchArea(HeartSprite);
		TextHeart = new ChangeableText(HeartSprite.getX() - 20, -10, mFont,
				String.valueOf(mPlayer.getHeart()), 2);
		mScene.attachChild(TextHeart);
		min_quabom_ChangeText = new ChangeableText(IconBomSprite.getX()
				+ IconBomSprite.getWidth() / 2, IconBomSprite.getY()
				+ IconBomSprite.getHeight() / 2 - 10, mFont, "Text");
		mScene.attachChild(min_quabom_ChangeText);
		TextDiem = new ChangeableText(0, -10, mFont, String.valueOf(DIEM), 10);
		mScene.attachChild(TextDiem);
		TextLevel = new ChangeableText(Screen.WIDTH / 2 - 50,
				Screen.HEIGTH - 42, mFont, "Level " + String.valueOf(LEVEL), 10);
		mScene.attachChild(TextLevel);
		TiepTucSprite = new Sprite(Screen.WIDTH / 2 - TiepTucTextRe.getWidth()
				/ 2, Screen.HEIGTH / 2 - TiepTucTextRe.getHeight() / 2,
				TiepTucTextRe) {
			@Override
			public boolean onAreaTouched(TouchEvent pSceneTouchEvent,
					float pTouchAreaLocalX, float pTouchAreaLocalY) {
				// TODO Auto-generated method stub
				if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
					if (Controler.SOUND)
						outch.play();
					/* You Win */
					if (LEVEL == Controler.tong_level) {
						Intent i = new Intent(MainActivity_Game.this,
								MainActivity_YouWin.class);
						i.putExtra("diem", DIEM);
						startActivity(i);
						finish();
					} else
						nextLevel();
				}
				return true;
			}
		};
		TiepTucSprite.setVisible(false);
		mScene.attachChild(TiepTucSprite);
		mScene.registerTouchArea(TiepTucSprite);
		A = new Sprite(-100, -100, ATextRe);
		A.setVisible(false);
		mScene.attachChild(A);
		mScene.registerTouchArea(A);
		B = new Sprite(-100, -100, BTextRe);
		B.setVisible(false);
		mScene.attachChild(B);
		mScene.registerTouchArea(B);
		/* Menu */
		mMenuScene = createMenuScene();
	}

	/* Update Handler */
	ArrayList<QuaBoom> ArrayListRemoveQuaBom = new ArrayList<QuaBoom>();
	IUpdateHandler UpdtHandler = new IUpdateHandler() {

		@Override
		public void reset() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onUpdate(float pSecondsElapsed) {
			// TODO Auto-generated method stub
			if (!OVERGAME) {
				if (!WIN) {
					try {
						Thread.sleep(20);
						for (int i = 0; i < mQuai.max_quai; i++) {
							if (!mQuai.quai_1[i].Quai1_AnimationSp.isVisible())
								mQuai.quai_1[i].moveRandom();
							if (SO_QUAI_CAN_TIEU_DIET - DEM_SO_QUAI_TIEU_DIET >= MAX_SO_QUAI) {
								if (!mQuai.quai_1[i].Quai1_AnimationSp
										.isVisible())
									mQuai.quai_1[i].bool_reset = true;
							}
							if ((SO_QUAI_CAN_TIEU_DIET * 50) - diem1 >= (MAX_SO_QUAI * 50)) {
								if (!mQuai.quai_1[i].Quai1_AnimationSp
										.isVisible())
									mQuai.quai_1[i].bool_reset = true;
							}
							mQuai.quai_1[i].reset();
							if (vaCham(mQuai.quai_1[i].Quai1_AnimationSp,
									mPlayer.player_AnimationSp)
									&& mQuai.quai_1[i].Quai1_AnimationSp
											.isVisible()) {
								if (Controler.SOUND)
									sound_resetplayer.play();
								mPlayer.moveXY(32, 32);
								mPlayer.setHeart(mPlayer.getHeart() - 1);
								TextHeart.setText(String.valueOf(mPlayer
										.getHeart()));
							}
						}
						try {
							if (Arr_QuaBom.size() != 0) {
								for (QuaBoom quabom : Arr_QuaBom) {
									if (!quabom.end_no) {
										quabom.delayNo();
										if (quabom.begin_no) {
											for (int j = 0; j < mQuai.max_quai; j++) {
												if (vaCham(
														quabom,
														mQuai.quai_1[j].Quai1_AnimationSp)) {
													DIEM += 50;
													diem1 += 50;
													DEM_SO_QUAI_TIEU_DIET++;
													if (SO_QUAI_CAN_TIEU_DIET == DEM_SO_QUAI_TIEU_DIET) {
														System.out
																.println("You Win");
														WIN = true;
													}
													if (SO_QUAI_CAN_TIEU_DIET
															- DEM_SO_QUAI_TIEU_DIET >= MAX_SO_QUAI) {
														if (!mQuai.quai_1[j].Quai1_AnimationSp
																.isVisible())
															mQuai.quai_1[j].bool_reset = true;
													}
													if ((SO_QUAI_CAN_TIEU_DIET * 50)
															- diem1 >= (MAX_SO_QUAI * 50)) {
														if (!mQuai.quai_1[j].Quai1_AnimationSp
																.isVisible())
															mQuai.quai_1[j].bool_reset = true;
													}
												}
												mQuai.quai_1[j].Quai1_AnimationSp
														.setVisible(false);
												mQuai.quai_1[j].Quai1_AnimationSp
														.setPosition(-100, -100);
												TextDiem.setText(
														String.valueOf(DIEM),
														true);
											}
											if (vaCham(quabom,
													mPlayer.player_AnimationSp)) {
												if (Controler.SOUND)
													sound_resetplayer.play();
												mPlayer.moveXY(32, 32);
												mPlayer.setHeart(mPlayer
														.getHeart() - 1);
												if (mPlayer.getMin_cap_boom() - 1 != 0)
													mPlayer.setMin_cap_boom(mPlayer
															.getMin_cap_boom() - 1);
												if (mPlayer.getMin_boom() - 1 != 0)
													mPlayer.setMin_boom(mPlayer
															.getMin_boom() - 1);
											}
										}
									} else {
										ArrayListRemoveQuaBom.add(quabom);
									}
								}
							}
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println("Tmp1" + e.toString());
						}
						if (ArrayListRemoveQuaBom.size() != 0
								&& Arr_QuaBom.size() != 0) {
							for (QuaBoom quabom : Arr_QuaBom) {
								Arr_QuaBom.remove(quabom);
							}
							ArrayListRemoveQuaBom.removeAll(Arr_QuaBom);
						}
						/* Text */
						min_quabom_ChangeText.setText(String
								.valueOf(mPlayer.min_boom - Arr_QuaBom.size()));
						TextHeart.setText(String.valueOf(mPlayer.getHeart()));
						TextCapBom.setText(String.valueOf(mPlayer
								.getMin_cap_boom()));
						if (mPlayer.getHeart() <= 0) {
							System.out.println("GameOver");
							OVERGAME = true;
						}
						mMoney.hienthi(mTMXMap, mTMXLayer);
						mMoney.collidesWith(mPlayer);
						checkIsVisiable();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				} else {
					if (!NEXT_LEVEL) {
						TiepTucSprite.setVisible(true);
						float pX = TiepTucSprite.getX();
						if (pX > 180)
							pX = Screen.WIDTH / 2 - TiepTucSprite.getWidth()
									/ 2;
						TiepTucSprite.setPosition(pX, TiepTucSprite.getY());
					} else {
					}
				}
			} else {
				if (db.kt_luu(DIEM)) {
					Intent i = new Intent(MainActivity_Game.this, Luudiem.class);
					i.putExtra("diem", DIEM);
					startActivity(i);
					finish();
				} else {
					Intent i = new Intent(MainActivity_Game.this,
							MainActivity_GameOver.class);
					startActivity(i);
					finish();
				}
			}
		}
	};
	IOnSceneTouchListener SceneTouchLis = new IOnSceneTouchListener() {

		@Override
		public boolean onSceneTouchEvent(Scene pScene,
				TouchEvent pSceneTouchEvent) {
			// TODO Auto-generated method stub
			if (pSceneTouchEvent.getAction() == TouchEvent.ACTION_DOWN) {
				System.out.println("this");
				if (mPlayer.getSTATUS_PLAYER() + 1 > StatusPlayer.Sum)
					mPlayer.setSTATUS_PLAYER(0);
				else
					mPlayer.setSTATUS_PLAYER(mPlayer.getSTATUS_PLAYER() + 1);
			}
			return false;
		}
	};

	public void onResumeGame() {
		System.out.println("ResumeGame");
	};

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (beep != null)
			beep.release();
		if (outch != null)
			outch.release();
		finish();
		System.out.println("Finish");
		super.onDestroy();
	}

	public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
		return false;
	}

	protected MenuScene createMenuScene() {
		final MenuScene menuScene = new MenuScene(mCamera);

		final IMenuItem resetMenuItem = new ColorMenuItemDecorator(
				new TextMenuItem(CONTINUE, mFont, "Tiep Tuc"), 1.0f, 0.0f,
				0.0f, 255.0f, 255.0f, 255.0f);
		resetMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA,
				GL10.GL_ONE_MINUS_SRC_ALPHA);
		menuScene.addMenuItem(resetMenuItem);

		final IMenuItem NewGameMenuItem = new ColorMenuItemDecorator(
				new TextMenuItem(NEWGAME, mFont, "Choi Lai"), 1.0f, 0.0f, 0.0f,
				255.0f, 255.0f, 255.0f);
		NewGameMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA,
				GL10.GL_ONE_MINUS_SRC_ALPHA);
		menuScene.addMenuItem(NewGameMenuItem);

		final IMenuItem MainMenuMItem = new ColorMenuItemDecorator(
				new TextMenuItem(MAINMENU, mFont, "Giao Dien Chinh"), 1.0f,
				0.0f, 0.0f, 255.0f, 255.0f, 255.0f);
		MainMenuMItem.setBlendFunction(GL10.GL_SRC_ALPHA,
				GL10.GL_ONE_MINUS_SRC_ALPHA);
		menuScene.addMenuItem(MainMenuMItem);

		final IMenuItem quitMenuItem = new ColorMenuItemDecorator(
				new TextMenuItem(QUIT, mFont, "Thoat"), 1.0f, 0.0f, 0.0f,
				255.0f, 255.0f, 255.0f);
		quitMenuItem.setBlendFunction(GL10.GL_SRC_ALPHA,
				GL10.GL_ONE_MINUS_SRC_ALPHA);
		menuScene.addMenuItem(quitMenuItem);

		menuScene.buildAnimations();
		menuScene.setBackgroundEnabled(false);
		menuScene.setOnMenuItemClickListener(this);
		return menuScene;
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		// TODO Auto-generated method stub
		switch (pMenuItem.getID()) {
		case CONTINUE:
			if (Controler.SOUND)
				outch.play();
			mScene.back();
			mMenuScene.reset();
			mScene.setChildScene(digitalControl);
			return true;
		case NEWGAME:
			if (Controler.SOUND)
				outch.play();
			Intent i = new Intent(this, MainActivity_Game.class);
			startActivity(i);
			finish();
			return true;
		case MAINMENU:
			if (Controler.SOUND)
				outch.play();
			Intent itent_BomActivity = new Intent(this,
					MainActivity_Display.class);
			startActivity(itent_BomActivity);
			finish();
			return true;
		case QUIT:
			if (Controler.SOUND)
				outch.play();
			MainActivity_DialogExit dialogexit = new MainActivity_DialogExit(
					this, this);
			dialogexit.show();
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			MainActivity_DialogExit dialogexit = new MainActivity_DialogExit(
					this, this);
			dialogexit.show();
		}
		return false;
	}

	private void nextLevel() {
		Intent intent_BomActivity = new Intent(this, MainActivity_Game.class);

		if (LEVEL + 1 < Controler.tong_level)
			LEVEL++;
		dinhNghiaCacBienThayDoi();

		MIN_CAP_QUABOM = mPlayer.getMin_cap_boom();
		MIN_QUABOM = mPlayer.getMin_boom();
		HEART = mPlayer.getHeart();

		intent_BomActivity.putExtra("Min_Cap_QuaBom", MIN_CAP_QUABOM);
		intent_BomActivity.putExtra("Min_QuaBom", MIN_QUABOM);
		intent_BomActivity.putExtra("DIEM", DIEM);
		intent_BomActivity.putExtra("HEARTY", HEART + 1);

		intent_BomActivity.putExtra("LEVEL", LEVEL);
		intent_BomActivity.putExtra("MAX_SO_QUAI", MAX_SO_QUAI);
		intent_BomActivity.putExtra("SO_QUAI_CAN_TIEU_DIET",
				SO_QUAI_CAN_TIEU_DIET);
		intent_BomActivity.putExtra("TEN_MAPS", TEN_MAPS + ".tmx");

		startActivity(intent_BomActivity);
		finish();
	}
	/*LEVEL cap do*/
	public void dinhNghiaCacBienThayDoi() {
		switch (LEVEL) {
		case 1:
			MAX_SO_QUAI = 5;
			SO_QUAI_CAN_TIEU_DIET = 5;
			TEN_MAPS = "maps_" + LEVEL;
			break;
		case 2:
			MAX_SO_QUAI = 5;
			SO_QUAI_CAN_TIEU_DIET = 10;
			TEN_MAPS = "maps_" + LEVEL;
			break;
		case 3:
			MAX_SO_QUAI = 5;
			SO_QUAI_CAN_TIEU_DIET = 15;
			TEN_MAPS = "maps_" + LEVEL;
			break;
		case 4:
			MAX_SO_QUAI = 5;
			SO_QUAI_CAN_TIEU_DIET = 20;
			TEN_MAPS = "maps_" + LEVEL;
			break;
		case 5:
			MAX_SO_QUAI = 5;
			SO_QUAI_CAN_TIEU_DIET = 25;
			TEN_MAPS = "maps_" + LEVEL;
			break;
		}
	}
	/*Va cham giua quai && tuong*/
	public boolean vaCham(AnimatedSprite a, AnimatedSprite b) {
		A.setPosition(a.getX() + 8, a.getY() + 8);
		B.setPosition(b.getX() + 8, b.getY() + 8);
		if (A.collidesWith(B))
			return true;
		return false;
	}

	public boolean vaCham(QuaBoom quabom, AnimatedSprite b) {
		for (int i = 0; i < quabom.no.length; i++) {
			if (quabom.no[i].Bum_AniSp.isVisible()) {
				A.setPosition(quabom.no[i].Bum_AniSp.getX() + 8,
						quabom.no[i].Bum_AniSp.getY() + 8);
				B.setPosition(b.getX() + 8, b.getY() + 8);
				if (A.collidesWith(B))
					return true;
			}
		}
		return false;
	}

	long time_checkIsVisiable = 0;

	public boolean checkIsVisiable() {
		try {
			if (time_checkIsVisiable == 0)
				time_checkIsVisiable = SystemClock.elapsedRealtime();
			if (SystemClock.elapsedRealtime() - time_checkIsVisiable > 15000) {
				time_checkIsVisiable = 0;
				if (diem1 != (SO_QUAI_CAN_TIEU_DIET * 50)) {
					for (int i = 0; i < mQuai.quai_1.length; i++) {
						if (mQuai.quai_1[i].Quai1_AnimationSp.isVisible())
							return true;
					}
					if (!mQuai.quai_1[0].Quai1_AnimationSp.isVisible())
						mQuai.quai_1[0].bool_reset = true;
					return false;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("e=" + e.toString());
		}
		return false;
	}
}
