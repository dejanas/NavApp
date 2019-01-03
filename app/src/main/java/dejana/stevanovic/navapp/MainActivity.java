package dejana.stevanovic.navapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static android.view.View.GONE;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ProgressBar progressBar;
    private RecyclerViewAdapter recyclerViewAdapter;
    private Context mContext;

    public void HideProgressBar() {
        progressBar.setVisibility(GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        progressBar = findViewById(R.id.progressBar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setActionBar(toolbar);



        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerViewAdapter = new RecyclerViewAdapter(new ArrayList<FinanceOffice>(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);

        FinanceOfficeListViewModel officeListViewModel = ViewModelProviders.of(this).get(FinanceOfficeListViewModel.class);

        officeListViewModel.getOfficeList().observe(this, new Observer<List<FinanceOffice>>() {
            @Override
            public void onChanged(@Nullable List<FinanceOffice> officeList) {

                Collections.sort(officeList, new Comparator<FinanceOffice>() {
                    @Override
                    public int compare(FinanceOffice lhs, FinanceOffice rhs) {
                        return lhs.getZip().compareTo(rhs.getZip());
                    }
                });

                recyclerViewAdapter.addItems(officeList);
                HideProgressBar();

                if(officeList.size() == 0 && !FetchHelper.isNetworkAvailable(mContext)){
                    Toast.makeText(mContext, "No internet connection.",  Toast.LENGTH_LONG).show();
                }
            }
        });

        FetchHelper.fetchData(this, officeListViewModel);
    }

    @Override
    public void onClick(View v) {
        try {
            FinanceOffice officeItem = (FinanceOffice) v.findViewById(R.id.itemView).getTag();
            if(officeItem != null){
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("office_string", officeItem.toString());
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

