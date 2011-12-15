package org.asky78.testprog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;

public class RectTest extends Activity {

	private SensorManager sm;
	private Sensor accSensor;
	float AX;
	float AY;
	float AZ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		XView view = new XView(this);
		setContentView(view, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));

		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		accSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); // 가속도 센서
	}

	@Override
	protected void onResume() {
		super.onResume();
		sm.registerListener(accelerationListener, accSensor, SensorManager.SENSOR_DELAY_GAME); // 가속도 센서 리스너 오브젝트를 등록
	}

	@Override
	protected void onPause() {
		super.onPause();
		sm.unregisterListener(accelerationListener); // unregister acceleration listener
	}

	/** 가속도 센서 */
	private SensorEventListener accelerationListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent event) {
			AX = event.values[0];
			AY = event.values[1];
			AZ = event.values[2];
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};

	/** Surface View */
	public class XView extends SurfaceView implements SurfaceHolder.Callback {

		private static final int RADIUS = 32;
		private Context context;
		private XThread thread;
		private int maxWidth;
		private int maxHeight;
		private Paint paint;
		private int cx = 320;
		private int cy = 600;

		public XView(Context context) {
			super(context);
			this.context = context;
			getHolder().addCallback(this);
//			getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			thread = new XThread(getHolder(), this);
			paint = new Paint();
			paint.setAntiAlias(true);
			paint.setColor(0xFFFFFFFF);
			paint.setStyle(Style.FILL_AND_STROKE);
			paint.setStrokeWidth(3);
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			maxWidth = MeasureSpec.getSize(widthMeasureSpec);
			maxHeight = MeasureSpec.getSize(heightMeasureSpec);
			Log.i("kensin", "" + maxHeight);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			canvas.drawColor(0xFF000000);
			canvas.drawCircle(cx, cy, RADIUS, paint);
			canvas.drawLine(360, 590, cx, cy, paint);
			super.onDraw(canvas);
		}
		
		float G_VALUE = 0.98f;
		int x_speed = 0;
		int y_speed = 0;
		public void updatePhysics() {
			//가속도 계산
			x_speed = (int)((G_VALUE / 2) * Math.pow(AX, 2));
			y_speed = (int)((G_VALUE / 2) * Math.pow(AY, 2));
			
			//방향성 계산
			if(AY > 0) down(y_speed);
			else if(AY < 0) up(y_speed);
			
			if(AX > 0) right(x_speed);
			else if(AX < 0) left(x_speed);
			
			//위 계산값에 따른 위치값 계산
			if(cx < RADIUS) cx = RADIUS;
			else if(cx > maxWidth - RADIUS) cx = maxWidth - RADIUS;
			if(cy < RADIUS) cy = RADIUS;
			else if(cy > maxHeight - RADIUS) cy = maxHeight - RADIUS;
		}
		public void right(int speed){
			cx = cx - speed;
		}
		public void left(int speed){
			cx = cx + speed;
		}
		public void up(int speed){
			cy = cy - speed;
		}
		public void down(int speed){
			cy = cy + speed;
		}
		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			thread.setRunning(true);
			thread.start();
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			thread.setRunning(false);
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/** Thread */
	class XThread extends Thread {

		private XView xView;
		private SurfaceHolder holder;
		private boolean run = false;

		public XThread(SurfaceHolder holder, XView xView) {
			this.holder = holder;
			this.xView = xView;
		}

		public void setRunning(boolean run) {
			this.run = run;
		}

		@Override
		public void run() {
			Canvas c;
			while (run) {
				c = null;
				try {
					c = holder.lockCanvas(null);
					synchronized (holder) {
						xView.updatePhysics();
						xView.onDraw(c);
					}
				} finally {
					if (c != null) {
						holder.unlockCanvasAndPost(c);
					}
				}
			}
		}
	}
}