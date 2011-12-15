package org.asky78.layouttest;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.animation.*;
import android.widget.*;

public class SomethingTestActivity extends Activity {

	private TextView tv2;
	private LinearLayout li1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		tv2 = (TextView)findViewById(R.id.tv2);
		
		li1 = (LinearLayout)findViewById(R.id.line1);
		li1.setBackgroundResource(R.drawable.model1);
		
		Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		maxWidth = display.getWidth();
		maxHeight = display.getHeight();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		float px = event.getX();
		float py = event.getY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			moveTextView((int)px, (int)py);
			showText(String.format("X = %s\nY = %s \n maxwidth=%s", px, py, maxWidth));
			break;
		case MotionEvent.ACTION_UP:
			hideText();
			break;
			
		}
		
		return super.onTouchEvent(event);
	}

	private static final int GAP = 30;
	private int maxWidth;
	private int maxHeight;
	int top=0;
	int bottom=0;
	int left=0;
	int right=0;
	int width=0;
	int height=0;
	
	private void moveTextView(int px, int py) {
		getViewInfo(tv2);
		
		if(px < (width / 2)) left = 0;
		else if(px > maxWidth - (width / 2)) left = maxWidth - width;
		else left = px - (width / 2);
		
		if(px < (height + GAP)) top = 0;
		else top = py - (height + GAP);
		
		tv2.setPadding(left, top, right, bottom);
	}

	/**
	 * 
	 */
	private void getViewInfo(View v) {
		top = v.getPaddingTop();
		bottom = v.getPaddingBottom();
		left = v.getPaddingLeft();
		right = v.getPaddingRight();
		width = v.getWidth();
		height = v.getHeight();
	}

	public void showText(String msg){
		tv2.clearAnimation();
//		tv2.setBackgroundColor(0xAAEEEEEE);
		tv2.setTextColor(0xFFFFFFFF);
		tv2.setText(msg);
	}
	public void hideText(){
		//tv2.setAnimation();
//		Animation anim = AnimationUtils.makeOutAnimation(this, true);
		Animation anim = AnimationUtils.loadAnimation(this, R.anim.change_alpha);
		tv2.startAnimation(anim);
		//tv2.setTextColor(android.R.color.transparent);
	}
}