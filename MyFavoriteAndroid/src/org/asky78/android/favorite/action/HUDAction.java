package org.asky78.android.favorite.action;

import org.asky78.android.favorite.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;

public class HUDAction extends Activity {

	private TextView tv2;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.action_hud_layout);
		
		tv2 = (TextView) findViewById(R.id.txt_action_hud);
	}
	
	@Override public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			showText(String.format("X = %s\nY = %s", event.getX(), event.getY()));
			break;
		case MotionEvent.ACTION_UP:
			hideText();
			break;

		}

		return super.onTouchEvent(event);
	}

	public void showText(String msg) {
		tv2.clearAnimation();
		tv2.setTextColor(0xFFFFFFFF);
		tv2.setText(msg);
	}

	public void hideText() {
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha_fade_out);
		tv2.startAnimation(anim);
	}
}
