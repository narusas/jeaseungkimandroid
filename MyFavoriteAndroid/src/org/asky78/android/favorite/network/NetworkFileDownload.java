package org.asky78.android.favorite.network;

import java.io.*;
import java.net.*;

import org.asky78.android.favorite.*;
import org.asky78.android.favorite.common.*;
import org.asky78.android.status.*;
import org.asky78.android.utils.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;

public class NetworkFileDownload extends Activity {
	private static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	protected static final int NETWORK_ENABLED_ERROR = 2;

	FileManager fileManager;
	private TextView txt_network_file_information;
	private EditText edit_network_file_download_url;
	private HttpURLConnection conn;
	private ProgressDialog progressDialog;
	private DeviceStatusChecker deviceStatusChecker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_network_file_download_from_url);

		fileManager = new FileManager();
		new Buttons(this).add(R.id.btn_network_file_download).onClick(buttonListener);

		edit_network_file_download_url = (EditText) findViewById(R.id.edit_network_file_download_url);
		txt_network_file_information = (TextView) findViewById(R.id.txt_network_file_information);
		deviceStatusChecker = new DeviceStatusChecker(this);
	}

	private OnClickListener buttonListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_network_file_download:
				String url = edit_network_file_download_url.getText().toString();
				try {
					if (deviceStatusChecker.isAnyNetWorkAvailable()) {
						Log.i(Constants.TAG, "get url=" + url);
						conn = (HttpURLConnection) new URL(url).openConnection();
						txt_network_file_information.setText(fileManager.showHeaderProperty(conn).toString());
						new DownloadFileAsync().execute(conn);
					} else {
						showDialog(NETWORK_ENABLED_ERROR);
					}
				} catch (Exception e) {
					Log.i(Constants.TAG, "URLConnecting error : " + e.getMessage());
				}
				break;
			}
		}
	};

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DIALOG_DOWNLOAD_PROGRESS:
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("Downloading file..");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setCancelable(true);
			progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
				@Override public void onCancel(DialogInterface dialog) {
					conn.disconnect();
				}
			});
			progressDialog.show();
			return progressDialog;
		case NETWORK_ENABLED_ERROR:
			return new AlertDialog.Builder(this)//
			.setMessage("네트워크를 사용할수 없습니다.")//
			.setCancelable(true)//
			.setPositiveButton("확인", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				};
			})//
			.create();
		default:
			return null;
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			// int percent = msg.getData().getInt(PERCENT);
			// Log.i(Constants.TAG, percent+"");
			// progressDialog.setProgress(percent);
			// tv.setText(msg.getData().getString(FILEINFO));
		};
	};

	/**
	 * 연속동작 스레드<value1, value2, value3> 호출시 new ? extends AsyncTask().execute(value1-1, value1-2,....); 이 별도 스레드 동작은 메인
	 * UI등에 접근할수 없다.(예외발생)
	 * 
	 * @author kimjaeseung
	 */
	class DownloadFileAsync extends AsyncTask<HttpURLConnection, Integer, Long> {
		/**
		 * 스레드가 시작될때 호출된다.
		 */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showDialog(DIALOG_DOWNLOAD_PROGRESS);
		}

		/**
		 * 진행할 동작. 쓰레드의 run부분이라고 할 수 있다. value1 의 배열을 파라미터로 받는다.
		 */
		@Override
		protected Long doInBackground(HttpURLConnection... conns) {
			HttpURLConnection conn;
			try {
				conn = conns[0];
				int fileSize = 0;
				fileSize = conn.getContentLength();
				Log.i(Constants.TAG, "File Size : " + fileSize);
				if (fileSize > 0) {
					showDialog(DIALOG_DOWNLOAD_PROGRESS);
					InputStream is = conn.getInputStream();
					int read = 0;
					int total = 0;
					byte[] buff = new byte[1024 * 32];
					while ((read = is.read(buff)) > 0) {
						total += read;
						// handler.handleMessage(new Messages().putInt(PERCENT,
						// (int)((total*100)/(float)fileSize)).getMessage());
						publishProgress((int) ((total * 100) / (float) fileSize));
					}
					is.close();
					conn.disconnect();
				}
			} catch (Exception e) {
				Log.i(Constants.TAG, e.getMessage());
			}
			return null;

		}

		protected void onProgressUpdate(Integer... progress) {
			progressDialog.setProgress(progress[0]);
		}

		/**
         * 
         */
		@Override
		protected void onPostExecute(Long unused) {
			dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
			Log.i(Constants.TAG, "File Download Complete!");
		}
	}
}
