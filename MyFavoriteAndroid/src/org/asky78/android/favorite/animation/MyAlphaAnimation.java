package org.asky78.android.favorite.animation;

import org.asky78.android.favorite.*;

import android.app.*;
import android.os.*;
import android.view.animation.*;
import android.widget.*;

public class MyAlphaAnimation extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_common_layout);
		setTitle("이미지의 투명도가 증가");
		
		ImageView image = (ImageView)findViewById(R.id.image_animation_common);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_increase);
		image.startAnimation(animation);
	}
}
