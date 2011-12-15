package org.asky78.android.favorite.listview;

import org.asky78.android.favorite.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MyArrayList extends ListActivity{
	private String[] colorNames;
	private Toast toast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		colorNames = getResources().getStringArray(R.array.color_array_list);
		toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				this,
				android.R.layout.simple_list_item_1, 
				colorNames);
		setListAdapter(arrayAdapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		toast.setText(position + " : " + ((TextView)v).getText().toString());
		toast.show();
	}
}
