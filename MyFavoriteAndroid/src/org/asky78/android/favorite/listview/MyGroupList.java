package org.asky78.android.favorite.listview;

import org.asky78.android.favorite.*;
import java.util.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class MyGroupList extends ListActivity {
	private String[] colorNames;
	private String[] colorValues;
	private CustomArrayAdapter groupAdapter;
	private ArrayList<EachRow> colorList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.layout_group_list_main);

		colorNames = getResources().getStringArray(R.array.color_array_list);
		colorValues = getResources().getStringArray(R.array.color_array_list_integer);

		colorList = new ArrayList<EachRow>();
		String prev = "";
		for (int i = 0; i < colorNames.length; i++) {
			String name = colorNames[i];
			String value = colorValues[i];
			EachRow row = new EachRow();
			if (name.substring(0, 1).toUpperCase().equals(prev) == false) {
				row.setAlpha(name.substring(0, 1).toUpperCase());
				row.setGroup(true);
			} else {
				row.setGroup(false);
			}
			row.setColorName(name);
			row.setColorValue(value);

			prev = name.substring(0, 1).toUpperCase();
			
			colorList.add(row);
		}

		groupAdapter = new CustomArrayAdapter(this, colorList);
		setListAdapter(groupAdapter);
	}

	public class EachRow {
		private boolean isGroup = false;
		private String colorName = "";
		private String colorValue = "";
		private String alpha = "";

		public boolean isGroup() {
			return isGroup;
		}

		public void setGroup(boolean isGroup) {
			this.isGroup = isGroup;
		}

		public String getColorName() {
			return colorName;
		}

		public void setColorName(String colorName) {
			this.colorName = colorName;
		}

		public String getAlpha() {
			return alpha;
		}

		public void setAlpha(String alpha) {
			this.alpha = alpha;
		}

		public String getColorValue() {
			return colorValue;
		}

		public void setColorValue(String colorValue) {
			this.colorValue = colorValue;
		}

	}

	public class CustomArrayAdapter extends ArrayAdapter<EachRow> {

		private Context context;

		public CustomArrayAdapter(Context context, List<EachRow> list) {
			super(context, 0, list);
			this.context = context;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			ViewWrapper viewWrapper;

			if (row == null) {
				row = ((Activity) context).getLayoutInflater().inflate(R.layout.layout_group_list_each_row, null);
				viewWrapper = new ViewWrapper(row);
				row.setTag(viewWrapper);

			} else {
				viewWrapper = (ViewWrapper) row.getTag();
			}

			EachRow eachRow = colorList.get(position);
			Log.i("yangdo", position + "");
			viewWrapper.getBodyText().setText(eachRow.getColorName() + " (" + eachRow.getColorValue() + ")");
			viewWrapper.getHeaderText().setText(eachRow.getAlpha());

			if (eachRow.isGroup()) {
				viewWrapper.getLinearHeader().setVisibility(View.VISIBLE);
			} else {
				viewWrapper.getLinearHeader().setVisibility(View.GONE);
			}

			return row;
		}
	}

	/**
	 * ViewWrapper 클래스
	 */
	class ViewWrapper {
		View base;
		private LinearLayout lineHeader;
		private TextView headerText;
		private TextView bodyText;
		private LinearLayout lineBody;

		public ViewWrapper(View base) {
			this.base = base;
		}

		LinearLayout getLinearHeader() {
			if (lineHeader == null) {
				lineHeader = (LinearLayout) base.findViewById(R.id.linear_group_list_each_row_header);
			}
			return lineHeader;
		}

		TextView getHeaderText() {
			if (headerText == null) {
				headerText = (TextView) base.findViewById(R.id.txt_group_list_each_row_header);
			}
			return headerText;
		}

		LinearLayout getLinearBody() {
			if (lineBody == null) {
				lineBody = (LinearLayout) base.findViewById(R.id.linear_group_list_each_row_body);
			}
			return lineBody;
		}

		TextView getBodyText() {
			if (bodyText == null) {
				bodyText = (TextView) base.findViewById(R.id.txt_group_list_each_row_body);
			}
			return bodyText;
		}
	}

	public <T> ArrayList<T> convertToArrayList(T[] t) {
		ArrayList<T> list = new ArrayList<T>();
		list.addAll(Arrays.asList(t));
		return list;
	}
}
