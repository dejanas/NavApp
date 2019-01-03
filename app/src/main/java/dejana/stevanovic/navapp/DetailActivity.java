package dejana.stevanovic.navapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import androidx.fragment.app.FragmentActivity;

public class DetailActivity extends FragmentActivity {

    TextView txtName;
    ImageView img;
    TextView txtAddress;
    TextView txtHours;
    Button btnMap;
    private TextView txtPhone;
    private Context mContext;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mContext = this;

        txtName = findViewById(R.id.nameTV);
        img = findViewById(R.id.imageIV);
        txtAddress = findViewById(R.id.addressTV);
        txtHours = findViewById(R.id.hoursTV);
        txtPhone = findViewById(R.id.phoneTV);
        btnMap = findViewById(R.id.mapBtn);

        Toolbar toolbar = findViewById(R.id.toolbar);
        if(toolbar != null){
            setActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_white_24);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        String officeString = getIntent().getStringExtra("office_string");

        FinanceOffice office = null;

        if(officeString != null && !officeString.isEmpty()){

            office = Convertor.fromStringToFinanceOffice(officeString);
        }

        if(office != null){

            txtName.setText(office.getName());
            txtAddress.setText(office.getZip() + office.getCity() + ", " + office.getStreet());
            txtHours.setText(office.getOpeningHours());
            txtPhone.setText(office.getPhone());

            final FinanceOffice finalOffice = office;
            Picasso.get()
                    .load(Uri.parse(office.getImageUrl()))
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .fit()
                    .into(img, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {

                            Picasso.get()
                                    .load(finalOffice.getImageUrl())
                                    .fit()
                                    .error(R.drawable.twotone_error_outline_black_24)
                                    .fit()
                                    .into(img);

                            Picasso.get()
                                    .load(finalOffice.getImageUrl())
                                    .fetch();
                        }
                    });

            final String latitude = office.getLatitude();
            final String longitude = office.getLongitude();

            btnMap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent mapIntent = new Intent(mContext, MapActivity.class);
                    mapIntent.putExtra("latitude", latitude);
                    mapIntent.putExtra("longitude", longitude);
                    startActivity(mapIntent);
                }
            });
        }
    }
}
