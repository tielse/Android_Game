package chauthoi1010.gmail.com.InterfaceSprite;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;

import android.content.Context;

public interface InterfaceSprite {
	/* Load cac file nguon nhu sound,image... */
	public void onLoadResources(Engine mEngine, Context mContext);

	/* Phuong thuc hien thi len man hinh chinh */
	public void onLoadScene(Scene mScene);
}
