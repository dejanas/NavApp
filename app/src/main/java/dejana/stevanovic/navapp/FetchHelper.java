package dejana.stevanovic.navapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

class FetchHelper {

     static void fetchData(final Context context, final FinanceOfficeListViewModel listViewModel) {

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Initialize a new JsonArrayRequest instance
        ArrayRequest jsonArrayRequest = new ArrayRequest(
                Request.Method.GET,
                "https://service.bmf.gv.at/Finanzamtsliste.json",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        String responseString = response.toString();
                        saveToDatabase(responseString, listViewModel);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                    }
                }
        );
        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);
    }

    static void saveToDatabase(String jsonString, FinanceOfficeListViewModel listViewModel) {

        //listViewModel.deleteAll();
        try {
            if (!(jsonString == null || jsonString.isEmpty())) {

                JSONArray jsonArray = new JSONArray(jsonString);
                List officeList = new ArrayList<FinanceOffice>();

                // Loop through the array elements
                for (int i = 0; i < jsonArray.length(); i++) {
                    FinanceOffice officeModel =
                            Convertor.fromJsonObjectToFinanceOffice(jsonArray.getJSONObject(i));

                    officeList.add(officeModel);
                }

                listViewModel.insertAll(officeList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

