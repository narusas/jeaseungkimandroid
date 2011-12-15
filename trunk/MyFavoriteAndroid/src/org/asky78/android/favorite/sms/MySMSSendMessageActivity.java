package org.asky78.android.favorite.sms;

import org.asky78.android.favorite.*;
import org.asky78.android.utils.*;

import android.app.*;
import android.content.*;
import android.os.*;
import android.telephony.*;
import android.view.*;
import android.widget.*;

public class MySMSSendMessageActivity extends Activity {
	private static final String SENT = "SMS_SENT";
	private static final String DELIVERED = "SMS_DELIVERED";
	
	private EditText phoneNumber;
	private EditText smsMessage;
	private Button sendButton;
	private Toaster toaster;
	private Button sendMonitorButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_sms_send_message);
		
		toaster = new Toaster(this).setLongDuration();
		phoneNumber = (EditText)findViewById(R.id.edit_sms_send_message_phone_number);
		smsMessage = (EditText)findViewById(R.id.edit_sms_send_message_content);
		sendButton = (Button)findViewById(R.id.btn_sms_send_message_send_button);
		sendMonitorButton = (Button)findViewById(R.id.btn_sms_send_message_send_monitor_button);
		
		sendButton.setOnClickListener(buttonListener);
		sendMonitorButton.setOnClickListener(buttonListener);
	}
	View.OnClickListener buttonListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			String phoneNo = phoneNumber.getText().toString();
			String message = smsMessage.getText().toString();
			if(phoneNo.length() > 0 && message.length() > 0){
				switch (v.getId()) {
				case R.id.btn_sms_send_message_send_button:
					sendSMS(phoneNo, message);
					break;
				case R.id.btn_sms_send_message_send_monitor_button:
					sendSMS_Monitering(phoneNo, message);
					break;
				}
				toaster.show("문자를 발송 했습니다.");
			}else{
				toaster.show("전화번호와 내용을 입력하세요");
			}
			
		}
	};
	
	protected void sendSMS(String phoneNo, String message) {
		PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, MySMSSendMessageActivity.class), 0);
		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phoneNo, null, message, pi, null);
	}
	
	/**
	 * 문자 전송 과정을 모니터링
	 * @param phoneNo
	 * @param message
	 */
	private void sendSMS_Monitering(String phoneNo, String message){
		PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
		PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);
		
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(phoneNo, null, message, sentPI, deliveredPI);
	}
	

	@Override
	protected void onResume() {
		/** SMS 발송 시작 했을 때 */
		registerReceiver(smsSent_broadcastReceiver	,new IntentFilter(SENT));
		
		/** SMS 전송 끝났을 때 */
		registerReceiver(smsDelivery_broadcastReceiver,new IntentFilter(DELIVERED));
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		unregisterReceiver(smsSent_broadcastReceiver);
		unregisterReceiver(smsDelivery_broadcastReceiver);
		super.onPause();
	}
	
	BroadcastReceiver smsSent_broadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			switch (getResultCode()) {
			case Activity.RESULT_OK:
				toaster.show("문자 발송");
				break;
			case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
				toaster.show("일반적인 전송 실패");
				break;
			case SmsManager.RESULT_ERROR_NO_SERVICE:
				toaster.show("서비스 제공 상태가 아닙니다.");
				break;
			case SmsManager.RESULT_ERROR_NULL_PDU:
				toaster.show("Null PDU");
				break;
			case SmsManager.RESULT_ERROR_RADIO_OFF:
				toaster.show("RADIO OFF");
				break;
				
			default:
				break;
			}
		}
	};
	BroadcastReceiver smsDelivery_broadcastReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			switch (getResultCode())
			{
			case Activity.RESULT_OK:
				toaster.show("SMS delivered");
				break;
			case Activity.RESULT_CANCELED:
				toaster.show("SMS not delivered");
				break;                       
			}
		}
	};	
}
