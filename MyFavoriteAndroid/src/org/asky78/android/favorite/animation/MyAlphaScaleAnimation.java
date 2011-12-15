package org.asky78.android.favorite.animation;

import org.asky78.android.favorite.*;

import android.app.*;
import android.os.*;
import android.view.animation.*;
import android.widget.*;

public class MyAlphaScaleAnimation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_common_layout);
		setTitle("이미지가 나타난 후 확대");
		
		ImageView image = (ImageView)findViewById(R.id.image_animation_common);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_scale_change_order);
		image.startAnimation(animation);
	}
}
