package org.asky78.android.utils;

import android.os.*;

public class Messages {
	Message message;
	Bundle bundle;

	public Messages() {
		this.message = new Message();
		this.bundle = new Bundle();
	}

	public Messages putInt(String key, int value) {
		bundle.putInt(key, value);
		return this;
	}

	public Messages putString(String key, String value) {
		bundle.putString(key, value);
		return this;
	}

	public Message getMessage() {
		message.setData(bundle);
		return this.message;
	}
}
