package org.asky78.android.favorite;

import org.asky78.android.favorite.common.*;
import org.asky78.android.utils.*;
import android.app.Activity;
import android.os.Bundle;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;

public class MyFavoriteAndroidActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		new Buttons(this)
			.add(R.id.goList)
			.add(R.id.goNetwork)
			.add(R.id.goActionEvent)
			.add(R.id.goTweenAnimation)
			.add(R.id.goDialog)
			.add(R.id.goSMSMessage)
			.add(R.id.goMenubar)
			.add(R.id.goActivityIntent)
			.add(R.id.goExplorer)
			.add(R.id.goResource)
			.add(R.id.goNavigator)
			.add(R.id.goReceiver)
			.onClick(buttonListener);
	
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(Constants.TAG, "MyFavoriteAndroidActivity on Destroyed");
	}
	
	private OnClickListener buttonListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.goList:
				IntentUtils.startActivity(MyFavoriteAndroidActivity.this, ListSample.class);
				break;
			case R.id.goNetwork:
				IntentUtils.startActivity(MyFavoriteAndroidActivity.this, NetworkSample.class);
				break;
			case R.id.goTweenAnimation:
				IntentUtils.startActivity(MyFavoriteAndroidActivity.this, AnimationSample.class);
				break;
			case R.id.goActionEvent:
				IntentUtils.startActivity(MyFavoriteAndroidActivity.this, ActionSample.class);
				break;
			case R.id.goDialog:
				IntentUtils.startActivity(MyFavoriteAndroidActivity.this, DialogSample.class);
				break;
			case R.id.goSMSMessage:
				IntentUtils.startActivity(MyFavoriteAndroidActivity.this, SMSMessageSample.class);
				break;
			case R.id.goMenubar:
				break;
			case R.id.goActivityIntent:
				IntentUtils.startActivity(MyFavoriteAndroidActivity.this, ActivitySample.class);
				break;
			case R.id.goExplorer:
				break;
			case R.id.goResource:
				IntentUtils.startActivity(MyFavoriteAndroidActivity.this, ResourceManagement.class);
				break;
			case R.id.goNavigator:
			case R.id.goReceiver:
			}
		}
	};
}