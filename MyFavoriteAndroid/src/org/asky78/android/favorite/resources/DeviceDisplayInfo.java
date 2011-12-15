package org.asky78.android.favorite.resources;

import org.asky78.android.favorite.*;

import android.app.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class DeviceDisplayInfo extends Activity {
	private TextView tv;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_only_textview_full);
		
		tv = (TextView)findViewById(R.id.txt_common_only_textview_full);
	}
	
	@Override protected void onResume() {
		super.onResume();
		StringBuilder sb = new StringBuilder();
		DisplayMetrics metrics = new DisplayMetrics(); 
		getWindowManager().getDefaultDisplay().getMetrics(metrics); 
		sb.append("density=" + metrics.density).append("\n");
		sb.append("densityDpi=" + metrics.densityDpi).append("\n");
		sb.append("scaledDensity=" + metrics.scaledDensity).append("\n"); 
		sb.append("widthPixels=" + metrics.widthPixels).append("\n");
		sb.append("heightPixels=" + metrics.heightPixels).append("\n");
		sb.append("xDpi=" + metrics.xdpi).append("\n");
		sb.append("yDpi=" + metrics.ydpi).append("\n");;
		sb.append("\n");
		
		Display display = getWindowManager().getDefaultDisplay();
		sb.append("w:" + display.getWidth()).append("\n");
		sb.append("h:" + display.getHeight());
		tv.setText(sb.toString());
	}
}
