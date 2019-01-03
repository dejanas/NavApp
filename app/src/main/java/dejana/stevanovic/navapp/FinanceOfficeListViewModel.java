package dejana.stevanovic.navapp;

import android.os.AsyncTask;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class FinanceOfficeListViewModel extends ViewModel {

    private LiveData<List<FinanceOffice>> officeList;
    private AppDatabase appDatabase;

    public FinanceOfficeListViewModel() {
        super();

        this.appDatabase = NavApplication.DB;
        this.officeList = appDatabase.financeOfficeDao().getAll();
    }


    public LiveData<List<FinanceOffice>> getOfficeList() {
        return officeList;
    }


    public FinanceOffice getOfficeById(String someId) {

        for (FinanceOffice office : officeList.getValue()) {
            if (office.getId().equals(someId)) {
                return office;
            }
        }
        return null;
    }

    public void insertAll(List<FinanceOffice> officeList) {
        new insertAllAsyncTask(appDatabase).execute(officeList);
    }

    public void insertItem(FinanceOffice officeModel) {
        new insertAsyncTask(appDatabase).execute(officeModel);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(appDatabase).execute();
    }

    private static class insertAsyncTask extends AsyncTask<FinanceOffice, Void, Void> {

        private AppDatabase db;

        insertAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final FinanceOffice... params) {
            db.financeOfficeDao().insert(params[0]);
            return null;
        }
    }

    private static class insertAllAsyncTask extends AsyncTask<List<FinanceOffice>, Void, Void> {

        private AppDatabase db;

        insertAllAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(List<FinanceOffice>[] lists) {
            db.financeOfficeDao().insertAll(lists[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private AppDatabase db;

        deleteAllAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            db.financeOfficeDao().deleteAll();
            return null;
        }
    }
}
