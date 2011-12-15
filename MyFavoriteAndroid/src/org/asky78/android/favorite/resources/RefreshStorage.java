package org.asky78.android.favorite.resources;

import org.asky78.android.favorite.*;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;

public class RefreshStorage extends Activity {
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_sample);
		sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED), Uri.parse("file://" + Environment.getExternalStorageDirectory()).toString());
	}
}
