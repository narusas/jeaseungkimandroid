package org.asky78.android.utils;

import android.content.*;
import android.widget.*;

public class Toaster {
	private Context context;
	private long duration = Toast.LENGTH_SHORT;
	private Toast toast;
	public Toaster(Context context) {
		this.context = context;
	}
	
	public Toaster setShortDuration(){
		this.duration = Toast.LENGTH_SHORT;
		return this;
	}

	public Toaster setLongDuration() {
		this.duration = Toast.LENGTH_LONG;
		return this;
	}
	
	public void show(CharSequence text){
		if(toast == null){
			toast = Toast.makeText(context, text, (int) duration);
		}else{
			toast.setText(text);
		}
		toast.show();
	}
}
