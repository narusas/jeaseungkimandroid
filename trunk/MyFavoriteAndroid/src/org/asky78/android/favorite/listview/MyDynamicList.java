package org.asky78.android.favorite.listview;

import java.util.*;
import org.asky78.android.favorite.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class MyDynamicList extends ListActivity {
	private MyCustomAdapter adapter;
	private String[] colorNames;
	private String[] colorValues;
	private ArrayList<EachRow> aList;
	private boolean checkBox_visible_flag = false;
	private boolean image_visible_flag = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_custom_list_main);

		ArrayList<Integer> buttonList = new ArrayList<Integer>();
		buttonList.add(R.id.layout_custom_list_main_bt1);
		buttonList.add(R.id.layout_custom_list_main_bt2);
		buttonList.add(R.id.layout_custom_list_main_bt3);
		setButtons(buttonList);

		colorNames = getResources().getStringArray(R.array.color_array_list);
		colorValues = getResources().getStringArray(R.array.color_array_list_integer);

		aList = new ArrayList<EachRow>();

		for (int i = 0; i < colorNames.length; i++) {
			EachRow eachRow = new EachRow(colorNames[i], colorValues[i], false);
			aList.add(eachRow);
		}

		adapter = new MyCustomAdapter(this, aList);
		setListAdapter(adapter);
	}

	public void setButtons(List<Integer> buttonList) {
		for (int res : buttonList) {
			((Button) findViewById(res)).setOnClickListener(buttonListener);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		return;
		// super.onListItemClick(l, v, position, id);
	}

	private OnClickListener buttonListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.layout_custom_list_main_bt1:
				toggleCheckBox();
				break;
			case R.id.layout_custom_list_main_bt2:
				toggleImage();
				break;
			case R.id.layout_custom_list_main_bt3:
				break;
			}
		}
	};

	public void moveIntent(Class<?> nextClass) {
		Intent intent = new Intent(this, nextClass);
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
	}

	public void toggleImage() {
		this.image_visible_flag = !this.image_visible_flag;
		adapter.notifyDataSetChanged();
	}

	public void toggleCheckBox() {
		this.checkBox_visible_flag = !this.checkBox_visible_flag;
		adapter.notifyDataSetChanged();
	}

	/**
	 * MyCustomAdapter 아답터 클래스
	 * 
	 * @author kimjaeseung
	 */
	class MyCustomAdapter extends ArrayAdapter<EachRow> {

		private Context context;

		public MyCustomAdapter(Context context, ArrayList<EachRow> aList) {
			super(context, 0, aList);
			this.context = context;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			ViewWrapper viewWrapper;

			if (row == null) {
				row = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_custom_list_each_row, null);
				viewWrapper = new ViewWrapper(row);
				row.setTag(viewWrapper);
			} else {
				viewWrapper = (ViewWrapper) row.getTag();
			}

			final int idx = position;
			EachRow eachRow = aList.get(position);
			viewWrapper.getName().setText(eachRow.getName());
			viewWrapper.getValue().setText(eachRow.getValue());
			
			viewWrapper.getValue().setBackgroundColor(Integer.parseInt(eachRow.getValue(), 16));
			
			ImageView image = viewWrapper.getImage();
			CheckBox chkbox = viewWrapper.getChecked();

			chkbox.setChecked(eachRow.isChecked());
			chkbox.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					aList.get(idx).setChecked(((CheckBox) v).isChecked());
				}
			});

			if (image_visible_flag) {
				(image).setVisibility(View.VISIBLE);
			} else {
				(image).setVisibility(View.GONE);
			}

			if (checkBox_visible_flag) {
				(chkbox).setVisibility(View.VISIBLE);
			} else {
				(chkbox).setVisibility(View.GONE);
			}

			return row;
		}
	}

	/**
	 * ViewWrapper 클래스
	 */
	class ViewWrapper {
		View base;
		ImageView image = null;
		TextView name = null;
		TextView value = null;
		CheckBox checked = null;

		public ViewWrapper(View base) {
			this.base = base;
		}

		public ImageView getImage() {
			if (image == null) {
				image = (ImageView) base.findViewById(R.id.layout_custom_list_each_row_image);
			}
			return image;
		}

		public TextView getName() {
			if (name == null) {
				name = (TextView) base.findViewById(R.id.layout_custom_list_each_row_text_name);
			}
			return name;
		}

		public TextView getValue() {
			if (value == null) {
				value = (TextView) base.findViewById(R.id.layout_custom_list_each_row_text_value);
			}
			return value;
		}

		public CheckBox getChecked() {
			if (checked == null) {
				checked = (CheckBox) base.findViewById(R.id.layout_custom_list_each_row_chkbox);
			}
			return checked;
		}
	}

	/**
	 * EachRow 클래스 리스트의 각 행에 매치되는 객체
	 * 
	 * @author kimjaeseung
	 */
	public class EachRow {
		private int image;
		private String name;
		private String value;
		private boolean checked;

		public EachRow(String name, String value, boolean checked) {
			this.name = name;
			this.value = value;
			this.checked = checked;
		}

		public int getImage() {
			return image;
		}

		public void setImage(int image) {
			this.image = image;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}
	}
}
