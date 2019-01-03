package dejana.stevanovic.navapp;

import android.app.Application;
import androidx.room.Room;

public class NavApplication extends Application{

    public static AppDatabase DB;


    @Override
    public void onCreate() {
        try {
            if (DB == null) {
                DB = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "navapp-db").build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        super.onCreate();
    }
}
