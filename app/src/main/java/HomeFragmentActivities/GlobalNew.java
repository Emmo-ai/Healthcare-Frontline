package HomeFragmentActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.emmocodeworks.healthcarefrontline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import Adapters.CountryAdapter;
import classes.CountryModelClass;

public class GlobalNew extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private TextView nCo, Co, nRe, Re, nDe, De , confirmedactivecases;

    private ArrayList<CountryModelClass> CountryList;
    private CountryAdapter adapter;
    private ProgressBar p1;


    public static final String TAG = "WORLD_LOG";

    private int globalCounter = 0;

    EditText searchEDtsx;

    public GlobalNew() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_new);
        recyclerView = findViewById(R.id.countries_recycler);
        p1 = findViewById(R.id.progressBar3);

        searchEDtsx = findViewById(R.id.editTextSearch);

        nCo = findViewById(R.id.wconf_country);
        Co = findViewById(R.id.wdelta_conf_title);
        nRe = findViewById(R.id.wrec_country );
        Re = findViewById(R.id.wdelta_rec_title);
        nDe = findViewById(R.id.wdec_country);
        De = findViewById(R.id.wdelta_dec_title);
        confirmedactivecases = findViewById(R.id.confirmedActiveCases);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(GlobalNew.this));
        requestQueue = Volley.newRequestQueue(GlobalNew.this);
        CountryList = new ArrayList<>();

        parseJSON();










    }

    private void filter(String text) {
         ArrayList<CountryModelClass> filteredList = new ArrayList<>();

         for(CountryModelClass item : CountryList ){
             if (item.getCountryName().toLowerCase().contains(text.toLowerCase())){
                 filteredList.add(item);
             }
         }
         adapter.filterList(filteredList);
    }

    private void parseJSON() {
        String url = "https://api.covid19api.com/summary";
        Log.d(TAG, "FUNCTION_RUNNING" + globalCounter);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            p1.setVisibility(View.GONE);
                            //Log.d(TAG, "Total : " + response.getString("count"));

                            JSONArray Countries = response.getJSONArray("Countries");
                            JSONObject world = response.getJSONObject("Global");

                            nCo.setText("[+" + (world.getInt("NewConfirmed")) + "]");
                            Co.setText(Integer.toString(world.getInt("TotalConfirmed")));
                            nRe.setText("[+" + (world.getInt("NewRecovered")) + "]");
                            Re.setText(Integer.toString(world.getInt("TotalRecovered")));
                            nDe.setText("[+" + (world.getInt("NewDeaths")) + "]");
                            De.setText(Integer.toString(world.getInt("TotalDeaths")));


                            int newConfirmedCases = world.getInt("NewConfirmed");
                            int totalConfirmedCases = world.getInt("TotalConfirmed");
                            int newDeaths = world.getInt("NewDeaths");
                            int totalDeaths = world.getInt("TotalDeaths");
                            int newRecovered = world.getInt("NewRecovered");
                            int totalRecoveredd = world.getInt("TotalRecovered");

                            int total_active_cases = totalConfirmedCases - (totalDeaths + totalRecoveredd);
                            confirmedactivecases.setText(total_active_cases + "");










                            for (int i = 0; i < Countries.length(); i++) {
                                JSONObject country = Countries.getJSONObject(i);
                                String Cname = country.getString("Country").trim();
                                int Con = country.getInt("TotalConfirmed");
                                int Rec = country.getInt("TotalRecovered");
                                int Dea = country.getInt("TotalDeaths");
                                CountryList.add(new CountryModelClass(Cname, Con, Dea, Rec));
                            }

                            Collections.sort(CountryList);
                            adapter = new CountryAdapter(GlobalNew.this, CountryList);
                            recyclerView.setAdapter(adapter);


                            searchEDtsx.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {

                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    filter(s.toString());
                                }
                            });


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                p1.setVisibility(View.GONE);

                if (error.networkResponse.statusCode == 429) {
                    //Toast.makeText(GlobalNew.this, "Too Many Requests!", Toast.LENGTH_SHORT).show();
                    //Log.d(TAG,"Too Many Requests!");
                    globalCounter++;
                    if (globalCounter >= 3) {
                        Log.d(TAG, "Failed...");
                        Toast.makeText(GlobalNew.this, "Something Went wrong ! Try Again later", Toast.LENGTH_SHORT).show();
                    } else {
                        parseJSON();
                    }
                } else if (isNetworkConnected())
                    Toast.makeText(GlobalNew.this, "Something Went wrong ! Try Again later", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(GlobalNew.this, "No Internet Connectivity", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) GlobalNew.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
