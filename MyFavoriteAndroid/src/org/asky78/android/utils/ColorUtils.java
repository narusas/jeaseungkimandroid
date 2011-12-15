package org.asky78.android.utils;

import android.graphics.*;

public class ColorUtils {
	private static final int ZERO = 0;
	private static final int DECODE_TYPE = 16;

	public static int convertHexStringToInteger(String hex){
		if(hex == null)					return ZERO;
		
		if(hex.startsWith("0x"))		hex = hex.substring(2);
		
		if(hex.matches("[a-fA-F0-9]+") == false)		return ZERO;

		if((hex.length() == 6 || hex.length() == 8) == false)	return ZERO;
		
		hex = hex.toUpperCase();
		int alpha, red, green, blue;
		int subStrCount = 0;
		if(hex.length() == 6){
			alpha = Integer.parseInt("FF", DECODE_TYPE);
		}else{
			alpha = Integer.parseInt(hex.substring(0, 2), DECODE_TYPE);
			subStrCount = 2;
		}
		red = Integer.parseInt(hex.substring(subStrCount, subStrCount+2), DECODE_TYPE);
		green = Integer.parseInt(hex.substring(subStrCount+2, subStrCount+4), DECODE_TYPE);
		blue = Integer.parseInt(hex.substring(subStrCount+4, subStrCount+6), DECODE_TYPE);
		return Color.argb(alpha, red, green, blue);
	}
}
