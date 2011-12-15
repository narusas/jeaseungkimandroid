package org.asky78.android.favorite.sms;

import java.util.*;

import org.asky78.android.favorite.*;
import org.asky78.android.utils.*;
import android.app.*;
import android.database.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

/**
 * 필요 퍼미션 <uses-permission android:name="android.permission.READ_SMS" /> <uses-permission
 * android:name="android.permission.WRITE_SMS" />
 * 
 * @author kimjaeseung
 */
public class MySMSMessageListActivity extends Activity {
	private static final String URI_CONTENT_SMS = "content://sms/";
	private static final String publicKey = "KEY";
	private static final String publicValue = "VALUE";
	
	private MyContentObserver observer;

	private ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_layout_common_list1);

		new Buttons(this)//
				.add(R.id.my_layout_common_list1_bt1)//
				.add(R.id.my_layout_common_list1_bt2)//
				.add(R.id.my_layout_common_list1_bt3)//
				.onClick(buttonListener);
		
		listview = (ListView)findViewById(R.id.my_layout_common_list1_listview);
	}

	private OnClickListener buttonListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.my_layout_common_list1_bt1:
				showAllSMSMessage();
				break;
			case R.id.my_layout_common_list1_bt2:				break;
			case R.id.my_layout_common_list1_bt3:				break;
			}
		}
	};

	
	private ArrayList<String> smsTitle;
	private ArrayList<String> smsBody;
	
	public void showAllSMSMessage(){
		smsTitle = new ArrayList<String>();
		smsBody = new ArrayList<String>();
		Uri allMessage = Uri.parse("content://sms/");
		Cursor c = getContentResolver().query(allMessage, null, null, null, null);
		smsTitle.add("기본 제목");
		smsBody.add("기본 내용");
		Log.i("kensin", "list make count is = " + c.getCount());
		while  (c.moveToNext()) {
		   smsTitle.add(c.getString(10));
		   smsBody.add(c.getString(11));
		}
		
		listview.setAdapter(new SimpleAdapter(this//
				, getListData()//
				, android.R.layout.simple_list_item_2//
				, new String[]{publicKey, publicValue}//
				, new int[]{android.R.id.text1, android.R.id.text2}));
	}
	
	public ArrayList<Map<String, String>> getListData(){
		ArrayList<Map<String, String>> arrlist = new ArrayList<Map<String, String>>();
		for (int i = 0; i < smsTitle.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(publicKey, smsTitle.get(i));
			map.put(publicValue, smsBody.get(i));
			arrlist.add(map);
		}
		return arrlist;
	}
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};

	protected void onResume() {
		Uri uri = Uri.parse(URI_CONTENT_SMS);
		observer = new MyContentObserver(handler);
		getContentResolver().registerContentObserver(uri, true, observer);
		Log.i("kensin", "regist Observer");
		super.onResume();
	}

	@Override
	protected void onPause() {
		getContentResolver().unregisterContentObserver(observer);
		Log.i("kensin", "unregist Observer");
		super.onPause();
	}

	class MyContentObserver extends ContentObserver{

		public MyContentObserver(Handler handler) {
			super(handler);
		}
		@Override
		public boolean deliverSelfNotifications() {
			return false;
		}
		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			
			Log.i("kensin", "Notification on SMS Observer");
			
			Message msg = new Message();
			msg.obj = "xxxxxxxxxx";
			
			handler.sendMessage(msg);
			
			Uri uriSMSURI = Uri.parse(URI_CONTENT_SMS);
			Cursor cur = getContentResolver().query(uriSMSURI, null, null, null, null);
			cur.moveToNext();
			
			String protocol = cur.getString(cur.getColumnIndex("protocol"));
			if(protocol == null){
				Log.i("kensin", "SMS SEND");
				int threadId = cur.getInt(cur.getColumnIndex("thread_id"));
				
				Log.i("kensin", "SMS SEND ID = " + threadId);
				Cursor c = getContentResolver().query(Uri.parse("content://sms/outbox/" + threadId), null, null, null, null);
				c.moveToNext();
				
				int p = cur.getInt(cur.getColumnIndex("person"));
				Log.i("kensin", "SMS SEND person = " + p);
				//getContentResolver().delete(Uri.parse("content://sms/conversations/" + threadId), null, null);
			}
			else{
				Log.i("kensin", "SMS RECIEVE");  
				int threadIdIn = cur.getInt(cur.getColumnIndex("thread_id"));
				showInfo(cur);
				String body = cur.getString(cur.getColumnIndex("body"));
				if(body.startsWith("mysecret")){
					getContentResolver().delete(Uri.parse("content://sms/conversations/" + threadIdIn), null, null);
				}
			}
		}
	}

	public void showInfo(Cursor cur) {
		Log.i("kensin", "===== SHOW CURSOR INFO =====");  
		Log.i("kensin", "cursor ColumnCount : " + cur.getColumnCount() + ", count : " + cur.getCount());
		String[] names = cur.getColumnNames();
		for(String name : names){
			int columnIndex = cur.getColumnIndex(name);
			Log.i("kensin", "cursor column " + name + "'s index is " + columnIndex + ", value is " + cur.getString(columnIndex));
		}
		Log.i("kensin", "========= END INFO =========");  
	}
}
