package dejana.stevanovic.navapp;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface FinanceOfficeDao {

    @Query("SELECT * FROM FinanceOffice")
    LiveData<List<FinanceOffice>> getAll();

    @Query("SELECT * FROM FinanceOffice WHERE DisKz LIKE :id")
    FinanceOffice findById(String id);

    @Insert
    void insert(FinanceOffice office);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<FinanceOffice> financeOffices);

    @Query("DELETE FROM FinanceOffice")
    void deleteAll();

}
