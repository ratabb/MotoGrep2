package org.illegaler.ratabb.motogrep.lollipop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utils {

	public static int getDensity() {
		try {
			return Integer.valueOf(getProp("ro.sf.lcd_density"));
		} catch (NumberFormatException nfe) {
			return 320;// default
		}

	}

	public static String getProp(String prop) {

		Process p = null;
		String ret = "";

		try {
			p = new ProcessBuilder(new String[] { "/system/bin/getprop", prop })
					.redirectErrorStream(true).start();
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				ret = line;
			}
			p.destroy();
		} catch (IOException ioe) {
			// TODO: handle exception
		}

		return ret;
	}

	public static String join(String[] stringArray, String separator) {
		String buf = "";
		for (String s : stringArray) {
			if (!buf.isEmpty())
				buf += separator;
			buf += s;
		}
		return buf;
	}

}
