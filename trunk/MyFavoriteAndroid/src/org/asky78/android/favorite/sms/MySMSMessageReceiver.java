package org.asky78.android.favorite.sms;

import android.content.*;
import android.os.*;
import android.telephony.*;
import android.util.*;
import android.widget.*;

public class MySMSMessageReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("kensin","Called SMS Receiver");
		Bundle bundle = intent.getExtras();
		SmsMessage[] msgs = null;
		StringBuilder str = new StringBuilder();

		if (bundle != null) {
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];

			for (int i = 0; i < msgs.length; i++) {
				msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				if(hasSpecialCode(msgs[i].getMessageBody().toString())){
					//abortBroadcast();
				}
				str.append("SMS from ")//
						.append(msgs[i].getOriginatingAddress())//
						.append(" : ")//
						.append(msgs[i].getMessageBody().toString())//
						.append("\n");
			}
			Toast.makeText(context, str.toString(), Toast.LENGTH_LONG).show();
		}
	}

	private boolean hasSpecialCode(String msg) {
		return msg.contains("some secret code fragment");
	}
}
