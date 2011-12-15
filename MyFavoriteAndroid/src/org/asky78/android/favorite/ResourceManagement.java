package org.asky78.android.favorite;

import java.util.*;

import org.asky78.android.favorite.resources.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;

public class ResourceManagement extends Activity {
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
		menuText.add("저장소 갱신");//0
		menuText.add("display 정보");//1
//		menuText.add("프로그레시브바 새창");//2
//		menuText.add("Radio 버튼 새창");//3
//		menuText.add("Simple Dialog");//4
//		menuText.add("Time Picker");//5
//		menuText.add("Date Picker");//6
//		menuText.add("다중선택 새창");//7
//		menuText.add("프로그레시브바 가로 막대 진행상태");//8
//		menuText.add("커스텀 프로그레시브(회전자)");//9
//		menuText.add("투명 Dialog");//10

		menuSummary = new ArrayList<String>();
		menuSummary.add("현재 저장소를 갱신합니다.");//0
		menuSummary.add("기기의 Display 정보를 화면에 보여줍니다.");
//		menuSummary.add("진행중을 나타내는 프로그레시브바 Alert 예제구현다.");
//		menuSummary.add("Radio 버튼이 들어간 새창 이며 선택하면 창이 닫힌다. ");
//		menuSummary.add("선택에 따른 로직구현을 위한 다이얼로그 창 구현");
//		menuSummary.add("Time Picker 시간선택 컨트롤을 다이얼로그에 구현");
//		menuSummary.add("Date Picker 날짜선택 컨트롤을 다이얼로그에 구현");
//		menuSummary.add("다중선택을 할수 있는 Alert 예제구현이다.");
//		menuSummary.add("진행상태를 수치상으로 알수 있게 가로막대 프로그레시브바로 표현한예제");
//		menuSummary.add("회전자를 커스텀 이미지로 바꾼 프로그레시브바 예제");
//		menuSummary.add("배경이 아무것도 없는 대화상자. 아이콘만 보이도록 세팅");//10
		
		((TextView)findViewById(R.id.txt_common_sample_subject)).setText("자원 관리 예제들");
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
	 * List의 Item 클릭 이벤트 처리
	 */
	private OnItemClickListener listItemClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> l, View v, int position, long id) {
			switch (position) {
			case 0:
				startActivity(RefreshStorage.class);//파일다운로드
				break;
			case 1:
				startActivity(DeviceDisplayInfo.class);//디스플레이 정보 보기
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
