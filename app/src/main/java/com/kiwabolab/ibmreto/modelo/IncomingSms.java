package com.kiwabolab.ibmreto.modelo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import com.kiwabolab.ibmreto.vista.NotificacionSMS;


public class IncomingSms extends BroadcastReceiver {
	//----------------------------------------------------------------------------------------------
	//Variables
	// Get the object of SmsManager
	final SmsManager sms = SmsManager.getDefault();
	//----------------------------------------------------------------------------------------------
	//
	public void onReceive(Context context, Intent intent) {
	
		// Retrieves a map of extended data from the intent.
		final Bundle bundle = intent.getExtras();

		try {
			
			if (bundle != null) {
				
				final Object[] pdusObj = (Object[]) bundle.get("pdus");
				
				for (int i = 0; i < pdusObj.length; i++) {
					
					SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
					String phoneNumber = currentMessage.getDisplayOriginatingAddress();
					
			        String senderNum = phoneNumber;
			        String message = currentMessage.getDisplayMessageBody();

			        Log.i("SmsReceiver", "senderNum: "+ senderNum + "message: " + message);

			        if(senderNum.equals("852")){
						OpenActivity(context, message);
					}
					
				} // end for loop
              } // bundle is null

		} catch (Exception e) {
			Log.e("SmsReceiver", "Exception smsReceiver" +e);
			
		}
	}
	//----------------------------------------------------------------------------------------------
	//
	private void OpenActivity(Context context, String text){
		Intent intent1 = new Intent();
		intent1.setClassName(context.getPackageName(), NotificacionSMS.class.getName());
		intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent1.putExtra("valor",text);
		context.startActivity(intent1);
	}

	
	
}