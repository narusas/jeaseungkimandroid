package org.asky78.android.favorite;

import java.util.*;

import org.asky78.android.favorite.listview.*;
import org.asky78.android.utils.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class ListSample extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_sample);

		ArrayList<Integer> btList = new ArrayList<Integer>();

		btList.add(R.id.go_array_list);
		btList.add(R.id.go_simple_array_list);
		btList.add(R.id.go_simpleexpandablelist_list);
		btList.add(R.id.go_custom_array_list);
		btList.add(R.id.go_dynamic_array_list);
		btList.add(R.id.go_group_list);

		setButtons(btList);
	}

	public void setButtons(List<Integer> buttonList) {
		for (int res : buttonList) {
			((Button) findViewById(res)).setOnClickListener(buttonListener);
		}
	}

	private OnClickListener buttonListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.go_array_list:
				IntentUtils.startActivity(ListSample.this,MyArrayList.class);
				break;
			case R.id.go_simple_array_list:
				IntentUtils.startActivity(ListSample.this,MySimpleArrayList.class);
				break;
			case R.id.go_simpleexpandablelist_list:
				IntentUtils.startActivity(ListSample.this,MyExpandableList.class);
				break;
			case R.id.go_custom_array_list:
				IntentUtils.startActivity(ListSample.this,MyCustomList.class);
				break;
			case R.id.go_dynamic_array_list:
				IntentUtils.startActivity(ListSample.this,MyDynamicList.class);
				break;
			case R.id.go_group_list:
				IntentUtils.startActivity(ListSample.this,MyGroupList.class);
				break;
			}
		}
	};
}
