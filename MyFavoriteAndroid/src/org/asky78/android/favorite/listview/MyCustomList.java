package org.asky78.android.favorite.listview;

import java.util.*;

import org.asky78.android.favorite.*;
import org.asky78.android.utils.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class MyCustomList extends ListActivity {
	protected static final int NOTHING = 100;
	protected static final int ITEM_ADD = 101;
	protected static final int ITEM_REMOVE = 102;
	protected int itemMode = NOTHING;

	private ArrayAdapter<String> adapter;
	private ListView listview;
	private String[] colorNames;
	private String[] colorValues;
	private ArrayList<String> colorNameList;
	private ArrayList<String> colorValueList;
	private int SELECTED = 0xFF808000;
	private int SELECTED_NOT = 0xFF808080;
	private Toaster toast;
	private Buttons buttons;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_custom_list_main);

		colorNames = getResources().getStringArray(R.array.color_array_list);
		colorValues = getResources().getStringArray(R.array.color_array_list_integer);

		colorNameList = new ArrayList<String>();
		colorValueList = new ArrayList<String>();

		Collections.addAll(colorNameList, colorNames);
		Collections.addAll(colorValueList, colorValues);

		adapter = new MyCustomAdapter(this, 0, colorNameList);

		listview = (ListView) findViewById(android.R.id.list);
		listview.setAdapter(adapter);
		listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		listview.setOnItemClickListener(listItemClickListener);

		buttons = new Buttons(this)//
				.add(R.id.layout_custom_list_main_bt1)//
				.add(R.id.layout_custom_list_main_bt2)//
				.add(R.id.layout_custom_list_main_bt3)//
				.onClick(buttonListener);

		toast = new Toaster(this);
	}

	private AdapterView.OnItemClickListener listItemClickListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			changeList(position);
			toast.show("Selected item = " + colorNameList.get(position));
		}
	};

	private View.OnClickListener buttonListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.layout_custom_list_main_bt1:
				itemMode = ITEM_ADD;
				buttons.setBackgroundColor(R.id.layout_custom_list_main_bt1, SELECTED, SELECTED_NOT);
				break;
			case R.id.layout_custom_list_main_bt2:
				itemMode = ITEM_REMOVE;
				buttons.setBackgroundColor(R.id.layout_custom_list_main_bt2, SELECTED, SELECTED_NOT);
				break;
			case R.id.layout_custom_list_main_bt3:
				itemMode = NOTHING;
				buttons.setBackgroundColor(R.id.layout_custom_list_main_bt3, SELECTED, SELECTED_NOT);
			}
		}
	};

	protected void changeList(int position) {
		try{
		switch (itemMode) {
		case ITEM_ADD:
			colorNameList.add(colorNameList.get(position));
			colorValueList.add(colorValueList.get(position));
			refreshList();
			break;
		case ITEM_REMOVE:
			colorNameList.remove(position);
			colorValueList.remove(position);
			refreshList();
			break;
		case NOTHING:
			break;
		}
		}catch (Exception e) {
			toast.show(e.getMessage());
		}
	}

	private void refreshList() {
		adapter.notifyDataSetChanged();
	}

	/**
	 * EachRow 클래스 리스트의 각 행에 매치되는 객체
	 * 
	 * @author kimjaeseung
	 */
	public class EachRow {

	}

	/**
	 * MyCustomAdapter 아답터 클래스
	 * 
	 * @author kimjaeseung
	 */
	class MyCustomAdapter extends ArrayAdapter<String> {

		private Context context;

		public MyCustomAdapter(Context context, int resourceId, ArrayList<String> list) {
			super(context, resourceId, list);
			this.context = context;
		}

		public MyCustomAdapter(Context context, int resourceId, String[] objs) {
			super(context, resourceId, objs);
			this.context = context;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			ViewWrapper viewWrapper;

			if (row == null) {
				// 새로운 뷰 생성
				LayoutInflater inflator = ((Activity) context).getLayoutInflater();
				row = inflator.inflate(R.layout.layout_simple_list_each_row, null);
				// Wrapper 초기화
				viewWrapper = new ViewWrapper(row);
				row.setTag(viewWrapper);

			} else {
				// 처음이 아닐때 convertView 재활용
				viewWrapper = (ViewWrapper) row.getTag();
			}

			String colorName = colorNameList.get(position);
			String colorValue = colorValueList.get(position);
			// 배경색설정 : String key 를 입력, int value 로 컬러값을 받아 글상자에 설정.
			// viewWrapper.getColorView().setBackgroundColor(Integer.parseInt(colorValue, 16));
			viewWrapper.getColorView().setBackgroundColor(Integer.decode("0x" + colorValue));
			viewWrapper.getColorName().setText(colorName);
			viewWrapper.getColorValue().setText(colorValue);
			return row;
		}
	}

	/**
	 * ViewWrapper 클래스
	 */
	class ViewWrapper {
		View base;
		TextView colorView = null;
		TextView colorName = null;
		TextView colorValue = null;

		public ViewWrapper(View base) {
			this.base = base;
		}

		TextView getColorView() {
			if (colorView == null) {
				colorView = (TextView) base.findViewById(R.id.my_simple_array_each_row_text1);
			}
			return colorView;
		}

		TextView getColorName() {
			if (colorName == null) {
				colorName = (TextView) base.findViewById(R.id.my_simple_array_each_row_text2);
			}
			return colorName;
		}

		TextView getColorValue() {
			if (colorValue == null) {
				colorValue = (TextView) base.findViewById(R.id.my_simple_array_each_row_text3);
			}
			return colorValue;
		}
	}
}
