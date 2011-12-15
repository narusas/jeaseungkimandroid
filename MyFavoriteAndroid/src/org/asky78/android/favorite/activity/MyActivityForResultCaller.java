package org.asky78.android.favorite.activity;

import org.asky78.android.favorite.*;

import android.app.*;
import android.content.*;
import android.database.*;
import android.os.*;
import android.provider.ContactsContract.Contacts;
import android.view.*;
import android.widget.*;

public class MyActivityForResultCaller extends Activity {
	protected static final int PICK_CONTACT_REQUEST = 8282;
	private TextView resultTxt;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_activity_for_result_caller);
		
		resultTxt = (TextView)findViewById(R.id.txt_activity_for_result_caller_guide);
		button = (Button)findViewById(R.id.btn_activity_for_result_caller_start_button);
		
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
			    startActivityForResult(intent, PICK_CONTACT_REQUEST);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == Activity.RESULT_OK && requestCode == PICK_CONTACT_REQUEST){
			Cursor cursor = getContentResolver().query(data.getData(), new String[]{Contacts.DISPLAY_NAME}, null, null, null);
			
			if(cursor.moveToFirst()){
				int columnIndex = cursor.getColumnIndex(Contacts.DISPLAY_NAME);
				String name = cursor.getString(columnIndex);						
			}
		}
	}
}
