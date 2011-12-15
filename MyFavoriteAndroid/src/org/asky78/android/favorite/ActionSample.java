package org.asky78.android.favorite;

import java.util.*;

import org.asky78.android.favorite.action.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;

public class ActionSample extends Activity {
	private ListView list;
	private List<String> menuText;
	private List<String> menuSummary;
	private String menuKey = "menukey";
	private String summaryKey = "summarykey";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_sample);
		
		menuText = new ArrayList<String>();
		menuText.add("화면에 정보표시 - HUD style");//0
		menuText.add("터치를 따라다니는 정보 표시");//1

		menuSummary = new ArrayList<String>();
		menuSummary.add("FrameLayout과 Animation을 이용하여 화면에 HUD처럼 정보를 보여줍니다.");//0
		menuSummary.add("손가락을 따라 움직이는 View를 보여줍니다.");//1
		
		setList();
	}

	/**
	 * List의 Item 클릭 이벤트 처리
	 */
	private OnItemClickListener listItemClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> l, View v, int position, long id) {
			switch (position) {
			case 0:
				startActivity(HUDAction.class);//HUD(Heads up display)처럼 화면 표시
				break;
			case 1:
				startActivity(HUDMoveAction.class);//HUD 이동
				break;
			case 2:
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
	
	/** @param name 구동할 activity class 이름 */
	private void startActivity(Class<?> name){
		Intent intent = new Intent(this, name);
		startActivity(intent);
	}

	/**
	 * ListView에 목록을 출력하고 리스너등 설정.
	 */
	private void setList() {
		list = (ListView) findViewById(R.id.list_common_samples);
		SimpleAdapter listDialogSampleAdapter = new SimpleAdapter(this//
				, getMenuDataList()//
				, android.R.layout.simple_list_item_2//
				, new String[] { menuKey, summaryKey }//
				, new int[] { android.R.id.text1, android.R.id.text2 });
		list.setAdapter(listDialogSampleAdapter);
		list.setOnItemClickListener(listItemClickListener);
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
