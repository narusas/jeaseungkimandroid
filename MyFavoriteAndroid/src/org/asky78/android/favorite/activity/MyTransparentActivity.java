package org.asky78.android.favorite.activity;

import org.asky78.android.favorite.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MyTransparentActivity extends Activity {
	private TextView txtview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_common_layout);

		txtview = (TextView) findViewById(R.id.txt_activity_common_layout);
		txtview.setText("THIS IS TRANSPARENT TEXT ACTIVITY");
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
	}
}
