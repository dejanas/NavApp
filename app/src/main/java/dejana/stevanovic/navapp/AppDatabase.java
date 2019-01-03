package dejana.stevanovic.navapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {FinanceOffice.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FinanceOfficeDao financeOfficeDao();
}
