package HomeFragmentActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Collections;

import Adapters.CountryAdapter;
import classes.CountryModelClass;

public class Global extends AppCompatActivity {

    EditText searchEdittext;

    TextView confirmedTotal, confirmedIncreasing, confirmedRecovered, confirmedRecoveredIncreasing, confirmedDeath, confirmedDeathIncreasing,
    confirmedActive;

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private ArrayList<CountryModelClass> CountryList;
    private CountryAdapter adapter;
    private ProgressBar p1;
    public static final String TAG = "WORLD_LOG";

    private int globalCounter = 0;
    public Global() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);

        searchEdittext = findViewById(R.id.editTextSearch);
        confirmedTotal = findViewById(R.id.world_confirmed);
        confirmedIncreasing = findViewById(R.id.world_confirmed_increasing);
        confirmedRecovered = findViewById(R.id.world_recovered);
        confirmedRecoveredIncreasing = findViewById(R.id.world_recovered_increasing);
        confirmedDeath = findViewById(R.id.world_death);
        confirmedDeathIncreasing =  findViewById(R.id.world_death_increasing);
        confirmedActive = findViewById(R.id.confirmedActiveCases);

        recyclerView = findViewById(R.id.countries_recycler1);
        p1 = findViewById(R.id.progressBar3);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Global.this));
        requestQueue = Volley.newRequestQueue(Global.this);
        CountryList = new ArrayList<>();

        parseJSON();

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



                            //confirmedRecoveredIncreasing.setText("[+" + (world.getInt("NewConfirmed")) + "]");

                          /*  Co.setText(Integer.toString(world.getInt("TotalConfirmed")));
                            nRe.setText("[+" + (world.getInt("NewRecovered")) + "]");
                            Re.setText(Integer.toString(world.getInt("TotalRecovered")));
                            nDe.setText("[+" + (world.getInt("NewDeaths")) + "]");
                            De.setText(Integer.toString(world.getInt("TotalDeaths")));*/




                     /*       int totalConfirmedCases = world.getInt("TotalConfimed");
                            int newConfirmedCases = world.getInt("NewConfirmed");
                            int newDeaths = world.getInt("NewDeaths");
                            int totalDeaths = world.getInt("TotalDeaths");
                            int newRecovered = world.getInt("NewRecovered");
                            int totalRecovered = world.getInt("TotalRecovered");

                            int total_active_cases = totalConfirmedCases - (totalDeaths + totalRecovered);



                            confirmedTotal.setText(totalConfirmedCases+"");
                            confirmedIncreasing .setText(newConfirmedCases+"");
                            confirmedDeathIncreasing .setText(newDeaths+"");
                            confirmedDeath .setText(totalDeaths+"");
                            confirmedRecoveredIncreasing .setText(newRecovered+"");
                            confirmedRecovered.setText(totalRecovered+"");
                            confirmedActive.setText(total_active_cases+"");*/



                            for (int i = 0; i < Countries.length(); i++) {
                                JSONObject country = Countries.getJSONObject(i);
                                String Cname = country.getString("Country").trim();
                                int Con = country.getInt("TotalConfirmed");
                                int Rec = country.getInt("TotalRecovered");
                                int Dea = country.getInt("TotalDeaths");
                                CountryList.add(new CountryModelClass(Cname, Con, Dea, Rec));
                            }

                            Collections.sort(CountryList);
                            adapter = new CountryAdapter(Global.this, CountryList);
                            recyclerView.setAdapter(adapter);

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
                    //Toast.makeText(MainActivity.this, "Too Many Requests!", Toast.LENGTH_SHORT).show();
                    //Log.d(TAG,"Too Many Requests!");
                    globalCounter++;
                    if (globalCounter >= 3) {
                        Log.d(TAG, "Failed...");
                        Toast.makeText(Global.this, "Something Went wrong ! Try Again later", Toast.LENGTH_SHORT).show();
                    } else {
                        parseJSON();
                    }
                } else if (isNetworkConnected())
                    Toast.makeText(Global.this, "Something Went wrong ! Try Again later", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Global.this, "No Internet Connectivity", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) Global.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
