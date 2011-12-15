package org.asky78.android.favorite.animation;

import org.asky78.android.favorite.*;

import android.app.*;
import android.os.*;
import android.view.animation.*;
import android.widget.*;

public class MyAlphaScaleTransRotateAnimation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_common_layout);
		setTitle("복합 애니메이션(Animation Set)");
		
		ImageView image = (ImageView)findViewById(R.id.image_animation_common);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_scale_trans_rotate_complex_change);
		animation.setFillAfter(true);
		image.startAnimation(animation);
	}
}
