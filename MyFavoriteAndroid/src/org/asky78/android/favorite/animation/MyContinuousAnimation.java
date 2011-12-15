package org.asky78.android.favorite.animation;

import org.asky78.android.favorite.*;
import android.app.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.ImageView.ScaleType;

public class MyContinuousAnimation extends Activity {

	private AnimationDrawable imageAnimation;
	private boolean isShowAnimation = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_common_layout);
		setTitle("연속 이미지가 변경되는 애니메이션");
		
		//일반적인 예제에 나오는 소스
//		ImageView image = (ImageView)findViewById(R.id.image_animation_common);
//		image.setBackgroundResource(R.drawable.continuous_image_list);
//		imageAnimation = (AnimationDrawable)image.getBackground();
	
		//내가 변경한 소스
		ImageView image = (ImageView)findViewById(R.id.image_animation_common);
		image.setScaleType(ScaleType.CENTER);
		imageAnimation = (AnimationDrawable)getResources().getDrawable(R.drawable.continuous_image_list); 
		image.setImageDrawable(imageAnimation);
		//이유는 나중에 알아봐야 겠지만 이곳에서 start를 하면 애니메이션이 시작되지 않는다.resume이후에만 가능한걸까..
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(isShowAnimation){
				imageAnimation.stop();
			}else{
				imageAnimation.start();
			}
			toggle(isShowAnimation);
			break;
		}
		return super.onTouchEvent(event);
	}

	private void toggle(boolean isShowAnimation) {
		this.isShowAnimation = !isShowAnimation;
	}
}
