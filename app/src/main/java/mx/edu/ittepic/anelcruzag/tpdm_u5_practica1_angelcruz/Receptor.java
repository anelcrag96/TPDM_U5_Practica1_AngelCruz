package mx.edu.ittepic.anelcruzag.tpdm_u5_practica1_angelcruz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.ArrayList;

public class Receptor extends BroadcastReceiver {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    String mensaje, telefono;

    //private static final String TAG = "SmsBroadCastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == SMS_RECEIVED) {
            Bundle bundle = intent.getExtras();

            if (bundle != null) {
                Object[] mypdu = (Object[]) bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[mypdu.length];

                for (int i = 0; i < mypdu.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = bundle.getString("format");

                        messages[i] = SmsMessage.createFromPdu((byte[]) mypdu[i], format);
                    }//if
                    else {
                        messages[i] = SmsMessage.createFromPdu((byte[]) mypdu[i]);
                    }//else

                    mensaje = messages[i].getMessageBody();
                    telefono = messages[i].getOriginatingAddress();
                }//for

                Toast.makeText(context, "Message: " + mensaje + " " + telefono, Toast.LENGTH_LONG).show();
                String[] partes = mensaje.toUpperCase().split(" ");
                MainActivity.mandarMotivacion(telefono, partes, context);
            }//if
        }//if
    }// onReceive
}//class
