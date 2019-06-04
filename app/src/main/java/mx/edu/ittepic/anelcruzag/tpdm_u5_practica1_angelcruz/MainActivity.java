package mx.edu.ittepic.anelcruzag.tpdm_u5_practica1_angelcruz;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
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
    TextView opciones;
    private static final int MY_PERMISSIONS_REQUEST_RECIEVER_SMS = 0;
    private static final int PERMISSION_REQUEST_CODE = 1;
    Base dmbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dmbs = new Base(this, "TPDMU5P1", null, 1);
        opciones = findViewById(R.id.txtMensaje);
        opciones.setText("1. SUERTE signo\n2. AMOR signo\n3. RESULTADOS equipo\n4. CHISME");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS};
                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
            }//if
        }//if

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
            }//if
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, MY_PERMISSIONS_REQUEST_RECIEVER_SMS);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_RECIEVER_SMS);
            }//else
        }//if
        // insercion();
    }// onCreate


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_RECIEVER_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permiso concedido", Toast.LENGTH_LONG).show();
                }//if
                else {
                    Toast.makeText(this, "SIN PERMISO", Toast.LENGTH_LONG).show();
                }//else
            }//case
        }//switch
    }//onRequestPermissionsResult

    private void insercion() {
        String var = "chisme";
        try {
            SQLiteDatabase inser = dmbs.getWritableDatabase();
            String SQL = "INSERT INTO CHISME VALUES(NULL, 'Este chismee es otro ...')";
            inser.execSQL(SQL);
            inser.close();
            System.out.println("SE PUDO INSERTAR! "+var);
        }//try
        catch (SQLException e) {
            System.out.println("ERROR! " + e.getMessage());
        }//catch
    }//insercion
}
