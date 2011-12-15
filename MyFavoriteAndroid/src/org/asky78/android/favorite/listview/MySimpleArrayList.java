package org.asky78.android.favorite.listview;

import java.util.*;

import org.asky78.android.favorite.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
/**
 *	 
 * @author kimjaeseung
 */
public class MySimpleArrayList extends ListActivity{
	private static final String TEXT1 = "COLOR_NAME";
	private static final String TEXT2 = "COLOR_RGB";
	private static final String TEXT3 = "NONE";
	private String[] colorNames;
	private String[] colorRGB;
	private Toast toast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		colorNames = getResources().getStringArray(R.array.color_array_list);
		colorRGB = getResources().getStringArray(R.array.color_array_list_integer);
		
		toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		
		for(int i = 0 ; i < colorNames.length ; i++){
			Map<String, String> map = new HashMap<String, String>();
			map.put(TEXT1, colorNames[i]);
			map.put(TEXT2, colorRGB[i]);
			map.put(TEXT3, "Nothing");
			list.add(map);
		}
		/**
		 * list : List<Map<?,?>> 실제 데이터가 들어 있는 리스트
		 * 미리 작성한 레이아웃
		 * List안에 각 Map마다 호출할 Key값을 순서대로 나열한 String[]배열
		 * 호출된 key값을 순서대로 넣어줄 resource id 값.
		 * 
		 * 아래의 예시에서 R.layout.my_simple_array_each_row 내에 있는 R.id.my_simple_array_each_row_text1 에
		 * list.get(position) 으로 받은 Map.get(TEXT1) 의 Value를 입력한다.
		 */
		SimpleAdapter simpleAdapter = new SimpleAdapter( 
				this, 
				list,
				R.layout.layout_simple_list_each_row,
				new String[] { TEXT1, TEXT2, TEXT3 },
				new int[] { R.id.my_simple_array_each_row_text1, R.id.my_simple_array_each_row_text2, R.id.my_simple_array_each_row_text3 }  );
		setListAdapter(simpleAdapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		toast.setText(position + " : " + ((TextView)v.findViewById(R.id.my_simple_array_each_row_text1)).getText().toString()
				+ ((TextView)v.findViewById(R.id.my_simple_array_each_row_text2)).getText().toString());
		toast.show();
	}
}
