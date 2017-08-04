package chauthoi1010.gmail.com.BaseEngine;

import org.anddev.andengine.util.SystemUtils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

public class MultiTouch {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private static Boolean SUPPORTED = null;
	@SuppressWarnings("unused")
	private static Boolean SUPPORTED_DISTINCT = null;

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================
	public static boolean isSupportedByAndroidVersion() {
		return SystemUtils.isAndroidVersionOrHigher(Build.VERSION_CODES.ECLAIR);
	}

	public static boolean isSupported(final Context pContext) {
		if (SUPPORTED == null)
			SUPPORTED = SystemUtils
					.isAndroidVersionOrHigher(Build.VERSION_CODES.ECLAIR)
					&& SystemUtils.hasSystemFeature(pContext,
							PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH);
		return SUPPORTED;
	}

	public static boolean isSupportDistinct(final Context pContext) {
		if (SUPPORTED == null)
			SUPPORTED_DISTINCT = SystemUtils
					.isAndroidVersionOrHigher(Build.VERSION_CODES.ECLAIR_MR1)
					&& SystemUtils
							.hasSystemFeature(
									pContext,
									PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT);
		return SUPPORTED;
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

}
