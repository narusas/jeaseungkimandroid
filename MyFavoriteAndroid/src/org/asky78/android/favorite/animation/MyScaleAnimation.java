package org.asky78.android.favorite.animation;

import org.asky78.android.favorite.*;

import android.app.*;
import android.os.*;
import android.view.animation.*;
import android.widget.*;

public class MyScaleAnimation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_common_layout);
		setTitle("사이즈가 줄어드는 애니메이션");
		
		ImageView image = (ImageView)findViewById(R.id.image_animation_common);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_change_smaller);
		animation.setFillAfter(true);
		image.startAnimation(animation);
	}
}
