package org.asky78.android.favorite;

import java.util.*;

import org.asky78.android.favorite.sms.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;

public class SMSMessageSample extends Activity {

	protected static final int SHOW_NORMAL_DIALOG = 1;
	protected static final int SHOW_PROGRESSIVE_DIALOG = 2;
	private ListView list;
	private List<String> menuText;
	private List<String> menuSummary;
	private String menuKey = "menukey";
	private String summaryKey = "summarykey";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_message_sample);

		menuText = new ArrayList<String>();
		menuText.add("SMS 보내기");//1
		menuText.add("다른 문자 전송 앱 호출");//2
		menuText.add("SMS 문자 리스트");//3
//		menuText.add("복합 애니메이션");//4
//		menuText.add("연속 애니메이션");//5

		menuSummary = new ArrayList<String>();
		menuSummary.add("SMS 문자를 보냅니다..");//1
		menuSummary.add("SMS 전송이 가능한  앱을 호출하여 메시지를 넘깁니다.");//2
		menuSummary.add("SMS 문자 리스트를 보여줍니다. ");//3
//		menuSummary.add("animation set 을 이용한 복합 애니메이션");//4
//		menuSummary.add("여러 이미지를 연속으로 보여주는 애니메이션");//5

		list = (ListView) findViewById(R.id.list_sms_samples);
		SimpleAdapter animationSampleAdapter = new SimpleAdapter(this//
				, getMenuDataList()//
				, android.R.layout.simple_list_item_2//
				, new String[] { menuKey, summaryKey }//
				, new int[] { android.R.id.text1, android.R.id.text2 });
		list.setAdapter(animationSampleAdapter);
		list.setOnItemClickListener(listItemClickListener);
	}

	/**
	 * List의 Item 클릭 이벤트 처리
	 */
	private OnItemClickListener listItemClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> l, View v, int position, long id) {
			switch (position) {
			case 0:
				showSample(MySMSSendMessageActivity.class);//send sms
				break;
			case 1:
				Intent sendIntent = new Intent(Intent.ACTION_VIEW);
		        sendIntent.putExtra("sms_body", "넘길 메시지 입력"); 
		        sendIntent.setType("vnd.android-dir/mms-sms");
		        startActivity(sendIntent);
				break;
			case 2:
				showSample(MySMSMessageListActivity.class);//get sms list
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			default:
				break;
			}
		}
	};

	protected void showSample(Class<?> nextClass) {
		Intent intent = new Intent(this, nextClass);
		startActivity(intent);
	}

	/**
	 * SimpleAdapter에 사용할 리스트를 생성해 반환합니다.
	 * 
	 * @return
	 */
	private ArrayList<Map<String, String>> getMenuDataList() {
		ArrayList<Map<String, String>> arrlist = new ArrayList<Map<String, String>>();
		for (int i = 0; i < menuText.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(menuKey, menuText.get(i));
			map.put(summaryKey, menuSummary.get(i));
			arrlist.add(map);
		}
		return arrlist;
	}

}
