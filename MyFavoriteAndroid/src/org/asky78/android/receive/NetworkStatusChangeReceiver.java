package org.asky78.android.receive;

import org.asky78.android.utils.*;

import android.content.*;
import android.net.*;
import android.net.wifi.*;
import android.util.*;

public class NetworkStatusChangeReceiver extends BroadcastReceiver {

	private String action;
	private Toaster toaster;
	private boolean DEBUGING = true;
	
	public NetworkStatusChangeReceiver() {
		logcat("make receiver");
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		if(toaster==null) toaster = new Toaster(context);
		action = intent.getAction();
		logcat("Receiver's get : " + action);
		if(action.equals(ConnectivityManager.CONNECTIVITY_ACTION)){
			toaster.show(ConnectivityManager.CONNECTIVITY_ACTION);
			
		}else if(action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)){
			toaster.show(WifiManager.WIFI_STATE_CHANGED_ACTION);
			
		}else if(action.equals(WifiManager.NETWORK_IDS_CHANGED_ACTION)){
			toaster.show(WifiManager.NETWORK_IDS_CHANGED_ACTION);
			
		}else if(action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){
			toaster.show(WifiManager.NETWORK_STATE_CHANGED_ACTION);
			
		}
	}

	private void logcat(String log) {
		if(DEBUGING) Log.i("kensin", log);
	}
}
