package org.asky78.android.favorite.activity;

import org.asky78.android.favorite.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MyActivityForResultAnswer extends Activity {
	private TextView resultTxt;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_activity_for_result_answer);
		
		resultTxt = (TextView)findViewById(R.id.txt_activity_for_result_answer_show_result);
		button = (Button)findViewById(R.id.btn_activity_for_result_answer_start_button);
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
