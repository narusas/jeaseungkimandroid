package org.asky78.android.favorite.activity;

import org.asky78.android.favorite.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class Popup extends Activity{
	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND, WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
		
		setContentView(R.layout.popup);
		
		//화면이 잠겨있을때 보여주기
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
				//키잠금 해제하기
				| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
				//화면 켜기
				| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
		
		((Button)findViewById(R.id.btn_popup)).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				finishActivity();
			}
		});
	}

	protected void finishActivity() {
		this.finish();
	}
}
