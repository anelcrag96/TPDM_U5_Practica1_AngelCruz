package mx.edu.ittepic.anelcruzag.tpdm_u5_practica1_angelcruz;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_RECIEVER_SMS = 0;
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    static Base basedatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basedatos = new Base(this, "TPDMU5P1", null, 1);

        if(!checkPermission(Manifest.permission.SEND_SMS)){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_REQUEST_CODE);
        }//if

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED){
            if(!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECEIVE_SMS)){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},MY_PERMISSIONS_REQUEST_RECIEVER_SMS);
            }//if
        }//if
    }// onCreate

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECIEVER_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permiso concedido", Toast.LENGTH_LONG).show();
                }//if
                else {
                    Toast.makeText(this, "Sin permiso", Toast.LENGTH_LONG).show();
                }//else
            }//case
        }//switch
    }//onRequestPermissionsResult

    private boolean checkPermission(String permission) {
        int check=ContextCompat.checkSelfPermission(this,permission);
        return (check==PackageManager.PERMISSION_GRANTED);
    }//checkPermission

    private static String consultar(int Id, Context context) {
        String motivacion = "No encontrada";
        try {
            SQLiteDatabase select = basedatos.getWritableDatabase();
            Cursor c=select.rawQuery("SELECT*FROM MOTIVACION WHERE IDMOTIVACION="+Id,null);

            if(c.moveToFirst()){
                do{
                    motivacion=c.getString(1);
                }//do
                while (c.moveToNext());
                select.close();
            }//if
            else{
                Toast.makeText(context,"No hay datos que mostrar",Toast.LENGTH_SHORT).show();
            }//else
        }//try
        catch (SQLException e) {
            Toast.makeText(context,"Error de consulta",Toast.LENGTH_LONG).show();
        }//catch
        return motivacion;
    }//consultar

    public static void mandarMotivacion(String telefono, String[] partes, Context context){
        if(partes[0].equals("MOTIVACION")&& partes[1].equals("5050")){
            int id=(int)(Math.random()*10)+1;

            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(telefono,null,consultar(id,context),null,null);

            Toast.makeText(context,"Mensaje enviado al "+telefono,Toast.LENGTH_SHORT).show();
        }//if
        else{
            Toast.makeText(context,"Mensaje erroneo",Toast.LENGTH_SHORT).show();
        }//else
    }//mandarMotivacion
}//class
