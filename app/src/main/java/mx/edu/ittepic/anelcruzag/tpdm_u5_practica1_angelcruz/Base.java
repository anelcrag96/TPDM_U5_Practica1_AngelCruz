package mx.edu.ittepic.anelcruzag.tpdm_u5_practica1_angelcruz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLDataException;

public class Base extends SQLiteOpenHelper {
    public Base(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }//constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MOTIVACION (IDMOTIVACION INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "MOTIVACION VARCHAR(200))");

        db.execSQL("INSERT INTO MOTIVACION(MOTIVACION) VALUES ('Esta es una frase que garantiza a su portador suerte para el próximo examen. Válida para un sólo uso')," +
                                                             "('¿Ya te sabes todo?, seguro que mañana te va a ir muy bien en el examen, ya verás como apruebas y encima con buena nota')," +
                                                             "('Vas a aprobar, de eso estoy seguro, así que sólo paso para dedicarte mi apoyo y que estés tranquila por todo el esfuerzo que le pusiste al estudio')," +
                                                             "('Hoy es tu día, seguro vas a tener suerte en el examen, estarás entre los elegidos, vamos tú puedes')," +
                                                             "('Un mensaje más para que tengas una montaña de buenos deseos y de buena suerte')," +
                                                             "('Se que puedes lograrlo, así que no te preocupes, se que darás todo de ti, te deseo mucha suerte y ánimo que vas a hacer un buen examen')," +
                                                             "('Ha llegado el momento esperado, y aunque todo irá bien, te deseo mucha suerte')," +
                                                             "('Muchísima suerte para tu examen, ya verás como es más fácil de lo que parece y se pasará muy rápido, tu sólo has de estar tranquilo y verás como no cometes errores')," +
                                                             "('Si la alegría depende de una ilusión no dejes de vivirla nunca sabrás si era una ilusión')," +
                                                             "('No hay triunfo convertido en derrota pero puedes convertir una derrota en un gran triunfo')," +
                                                             "('Si ayudas a los demás a ser exitosos, tu éxito está garantizado')");
    }//onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }//onUpgrade
}//class
