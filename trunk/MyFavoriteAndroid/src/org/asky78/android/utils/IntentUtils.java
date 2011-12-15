package org.asky78.android.utils;

import android.content.*;

public class IntentUtils {
	public static void startActivity(Context context, Class<?> nextClass) {
		Intent intent = new Intent(context, nextClass);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(intent);
	}
}
