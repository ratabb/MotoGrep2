/*
 * (c) dcsms Omzen
 */
package org.illegaler.ratabb.motogrep.lollipop.mod;

import java.io.File;

import de.robv.android.xposed.XSharedPreferences;
import android.content.res.XResources;
import android.content.res.XResources.DrawableLoader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import static org.illegaler.ratabb.motogrep.lollipop.Constant.*;

public class DrawUtils {
	public static DrawableLoader getIcon(final XSharedPreferences pref,
			final String stringPath, final String name) throws Throwable {
		final File pngFile = new File(stringPath + "/" + name + ".png");
		if (!isExisted(pngFile)) {
			return null;
		}

		return new DrawableLoader() {
			@Override
			public Drawable newDrawable(XResources res, int id)
					throws Throwable {
				BitmapDrawable bd = (BitmapDrawable) Drawable
						.createFromPath(pngFile.getAbsolutePath());
				pref.reload();
				int d = pref.getInt(PREF_KEY_DENSITY, 320);
				bd.setTargetDensity(d);

				return bd;
			}
		};

	}

	public static Drawable getDrawable(final XSharedPreferences pref,
			final String stringPath, final String name) throws Throwable {
		final File pngFile = new File(stringPath + "/" + name + ".png");
		if (!isExisted(pngFile)) {
			return null;
		}

		BitmapDrawable bd = (BitmapDrawable) Drawable.createFromPath(pngFile
				.getAbsolutePath());
		pref.reload();
		int d = pref.getInt(PREF_KEY_DENSITY, 320);
		bd.setTargetDensity(d);

		return bd;
	}

	public static Boolean isExisted(final File file) {
		return file.exists();
	}

}
