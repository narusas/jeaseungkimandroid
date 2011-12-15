package org.asky78.android.favorite.action;

import org.asky78.android.favorite.*;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.graphics.Paint.Style;
import android.os.*;
import android.view.*;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.*;
import android.widget.*;

public class HUDMoveAction extends Activity {

	private MyMovingView mv;
	private int maxWidth;
	
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.action_hud_move_layout);

		LinearLayout line = (LinearLayout)findViewById(R.id.layer_action_hud_move);
		mv = new MyMovingView(this);
		line.addView(mv, new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		
		Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		maxWidth = display.getWidth();
	}
	
	class MyMovingView extends View{

		private Context context;
		private Paint textPaint = new Paint();
		private Paint rectPaint = new Paint();
		int x = 0;
		int y = 0;
		int tx = 0;
		int ty = 0;
		
		public MyMovingView(Context context) {
			super(context);
			this.context = context;
			textPaint.setColor(0xFFFFFFFF);
			textPaint.setTextSize(40);
			textPaint.setAntiAlias(true);

			rectPaint.setColor(0xAACFA043);
			rectPaint.setStrokeWidth(5f);
			rectPaint.setAntiAlias(true);
			rectPaint.setStyle(Style.STROKE);
		}
		
		@Override protected void onDraw(Canvas canvas) {
			canvas.drawText(String.format("X = %s", x), tx, ty, textPaint);
			canvas.drawText(String.format("Y = %s", y), tx, ty+30, textPaint);
			canvas.drawRoundRect(new RectF(tx-6, ty-40, tx+148, ty+40), 12, 12, rectPaint);
			canvas.drawCircle(x, y, 12, rectPaint);
			super.onDraw(canvas);
		}
		
		@Override public boolean onTouchEvent(MotionEvent event) {
			int action = event.getAction();
			float px = event.getX();
			float py = event.getY();
			
			switch (action) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
				moveTextView((int)px, (int)py);
				break;
			case MotionEvent.ACTION_UP:
				hideText();
				break;
			}

			return true;
		}
		
		private static final int GAP = 150;
		private static final int TITLE_BAR = 50;
		int width = 140;
		int height = 80;
		
		private void moveTextView(int px, int py) {
			mv.clearAnimation();
			int x, y;
			
			if(px < (width / 2)) x = 10;
			else if(px > maxWidth - (width / 2)) x = maxWidth - width - 10;
			else x = px - (width / 2);
			
			if(py < (height + GAP + TITLE_BAR)) y = TITLE_BAR + 10;
			else y = py - (height + GAP);
			
			mv.setTX(x);
			mv.setTY(y);
			mv.setX(px);
			mv.setY(py);
			mv.invalidate();
		}
		public void hideText() {
			Animation anim = AnimationUtils.loadAnimation(context, R.anim.alpha_fade_out);
			mv.startAnimation(anim);
		}
		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}

		public void setTX(int tx) {
			this.tx = tx;
		}

		public void setTY(int ty) {
			this.ty = ty;
		}
	}
}
