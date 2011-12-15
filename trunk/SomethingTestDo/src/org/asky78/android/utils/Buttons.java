package org.asky78.android.utils;

import java.util.*;
import android.app.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class Buttons {
	private List<Integer> buttonList;
	private Activity activity;

	public Buttons(Activity activity) {
		buttonList = new ArrayList<Integer>();
		this.activity = activity;
	}

	public Buttons addAll(List<Integer> buttonList) {
		this.buttonList.addAll(buttonList);
		return this;
	}

	public Buttons add(int res) {
		this.buttonList.add(res);
		return this;
	}
	/**
	 * @param res
	 * @return
	 */
	public Button findButton(int resId) {
		return (Button) activity.findViewById(resId);
	}

	public Buttons onClick(OnClickListener buttonListener) {
		for (int res : buttonList) {
			findButton(res).setOnClickListener(buttonListener);
		}
		return this;
	}

	public void setBackgroundColor(int buttonResId, int color, int defaultColor) {
		for (int res : buttonList) {
			findButton(res).setBackgroundColor(defaultColor);
			if(res == buttonResId) findButton(res).setBackgroundColor(color);
		}
	}

	public Buttons setText(int resId, CharSequence buttonText) {
		findButton(resId).setText(buttonText);
		return this;
	}
	public Buttons setText(CharSequence buttonText) {
		if(buttonList.size() == 0) return null;
		return setText(buttonList.get(buttonList.size()-1), buttonText);
	}
}
