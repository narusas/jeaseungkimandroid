package org.asky78.android.favorite.listview;

import java.util.*;

import org.asky78.android.favorite.*;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MyExpandableList extends ExpandableListActivity{
	private static final String INDEX = "index";
	private static final String STR_VAL = "str_value";
	
	private String[] alphaHeader;
	private String[] colorNames;
	private String[] colorRGB;

	private List<Map<String, String>> groupData;
	private List<List<Map<String, String>>> childData;
	
	ExpandableListAdapter simpleExpandableListAdapter;
	private Toast toast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		groupData = new ArrayList<Map<String, String>>();
		childData = new ArrayList<List<Map<String, String>>>();

		toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		
		alphaHeader = getResources().getStringArray(R.array.alphabet_header);
		colorNames = getResources().getStringArray(R.array.color_array_list);
		colorRGB = getResources().getStringArray(R.array.color_array_list_integer);
		
		for(int i = 0 ; i < alphaHeader.length ; i ++){
			String headerName = alphaHeader[i];
			Map<String, String> eachGrpMap = new HashMap<String, String>();
			eachGrpMap.put(INDEX, alphaHeader[i]);
			eachGrpMap.put(STR_VAL, headerName + " Group");
			groupData.add(eachGrpMap);
			
			List<Map<String, String>> childList = new ArrayList<Map<String,String>>();
			for(int j = 0 ; j < colorNames.length ; j++){
				String colorName = colorNames[j];
				if(colorName.toLowerCase().startsWith(headerName.toLowerCase())){
					Map<String, String> childMap = new HashMap<String, String>();
					childMap.put(INDEX, colorNames[j]);
					childMap.put(STR_VAL, colorRGB[j]);
					childList.add(childMap);
				}
			}
			childData.add(childList);
		}
		
/*		simpleExpandableListAdapter = new SimpleExpandableListAdapter(
			this,
			groupData,
			android.R.layout.simple_expandable_list_item_2,
			new String[]{INDEX, STR_VAL},
			new int[]{android.R.id.text1, android.R.id.text2},
			
			childData,
			android.R.layout.simple_list_item_2,
			new String[]{INDEX, STR_VAL},
			new int[]{android.R.id.text1, android.R.id.text2}
		);*/
		simpleExpandableListAdapter = new SimpleExpandableListAdapter(
			this,
			groupData,
			R.layout.layout_simple_expandable_list_custom_header,
			new String[]{INDEX, STR_VAL},
			new int[]{R.id.my_simple_expandable_grp_header_name, R.id.my_simple_expandable_grp_header_subject},
			
			childData,
			R.layout.layout_simple_expandable_list_custom_child,
			new String[]{INDEX, STR_VAL},
			new int[]{R.id.my_simple_expandable_grp_child_text1, R.id.my_simple_expandable_grp_child_text2}
		);
/*		simpleExpandableListAdapter = new SimpleExpandableListAdapter(
				this,
				groupData,
				android.R.layout.simple_expandable_list_item_1,
				new String[]{INDEX, STR_VAL},
				new int[]{android.R.id.text1},
				
				childData,
				android.R.layout.simple_list_item_1,
				new String[]{INDEX, STR_VAL},
				new int[]{android.R.id.text1}
			);*/		
		setListAdapter(simpleExpandableListAdapter);
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
		toast.setText(groupPosition + ", " + childPosition + " : "
			+ groupData.get(groupPosition).get(STR_VAL) + ", "
			+ childData.get(groupPosition).get(childPosition).get(INDEX) + ", "
			+ childData.get(groupPosition).get(childPosition).get(STR_VAL));
		toast.show();
		return true;
	}
}
