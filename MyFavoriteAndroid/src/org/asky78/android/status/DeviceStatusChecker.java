package org.asky78.android.status;

import android.content.*;
import android.location.*;
import android.net.*;

public class DeviceStatusChecker {
	Context context;

	public DeviceStatusChecker(Context context) {
		this.context = context;
	}

	public boolean isWifiAvailable() {
		return isNetWorkAvailable(context, ConnectivityManager.TYPE_WIFI);
	}

	public boolean is3GAvailable() {
		return isNetWorkAvailable(context, ConnectivityManager.TYPE_MOBILE);
	}

	public boolean isAnyNetWorkAvailable() {
		return (isWifiAvailable() || is3GAvailable());
	}

	private boolean isNetWorkAvailable(Context context, int networkType) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		try {
			if (connectivityManager == null)
				return false;

			NetworkInfo info = connectivityManager.getNetworkInfo(networkType);
			// Log.i("yangdo", networkType + ":" + info.toString());
			// Log.i("yangdo", info.isAvailable() + "");
			// Log.i("yangdo", info.isConnected() + "");
			return (info.isAvailable() && info.isConnected());

		} catch (Exception e) {
			return false;
		}
	}

	public boolean isGPSAvailable(Context context) {
		LocationManager locManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			return false;
		}
		return true;
	}
}
