package HomeFragmentActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.HashMap;

public class CovidActivity extends AppCompatActivity {

    TextView global_txtview;
    private RequestQueue requestQueue;

    TextView totalRecovered,totaldeeaths,totalCases,totalActive , confirmed_Increased_Cases ,
            recovered_Increasing_Numbers ,death_Increased_Number ;
    private ProgressBar p1;

    TextView Countr_yname;
    private int globalCounter = 0;

    public static final String TAG = "WORLD_LOG";

    int id;
    String name;

    public CovidActivity() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid);

        p1 = findViewById(R.id.progressBar_covidActivity);

        totalRecovered = findViewById(R.id.total_recovered_numbers);
        totaldeeaths = findViewById(R.id.total_death_number);
        totalCases = findViewById(R.id.confirmed_numbers);
        totalActive = findViewById(R.id.active_cases);
         Countr_yname =findViewById(R.id.Countryname);
         confirmed_Increased_Cases = findViewById(R.id.confirmed_increased_cases);
         recovered_Increasing_Numbers = findViewById(R.id.recovered_increasing_numbers);
         death_Increased_Number = findViewById(R.id.death_increased_number);



        global_txtview = findViewById(R.id.global_cases);
        global_txtview.setOnClickListener(v -> {
            Intent i = new Intent(CovidActivity.this, GlobalNew.class);
            startActivity(i);
        });

        requestQueue = Volley.newRequestQueue(CovidActivity.this);
        parseJSON();

    }

    private void parseJSON() {
        String url = "https://api.covid19api.com/summary";
       // Log.d(TAG, "FUNCTION_RUNNING" + globalCounter);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            p1.setVisibility(View.GONE);
                            JSONArray Countries = response.getJSONArray("Countries");
                            JSONObject jsonObject = Countries.getJSONObject(64);

                            String Cname = jsonObject.getString("Country").trim();
                            int newConfirmedCases = jsonObject.getInt("NewConfirmed");
                            int totalConfirmedCases = jsonObject.getInt("TotalConfirmed");
                            int newDeaths = jsonObject.getInt("NewDeaths");
                            int totalDeaths = jsonObject.getInt("TotalDeaths");
                            int newRecovered = jsonObject.getInt("NewRecovered");
                            int totalRecoveredd = jsonObject.getInt("TotalRecovered");

                            int total_active_cases = totalConfirmedCases - (totalDeaths + totalRecoveredd);

                            Countr_yname.setText(Cname);
                            confirmed_Increased_Cases.setText(newConfirmedCases + "");
                            totalCases.setText(totalConfirmedCases + "");
                            death_Increased_Number.setText(newDeaths + "");
                            totaldeeaths.setText(totalDeaths + "");
                            recovered_Increasing_Numbers.setText(newRecovered+"");
                            totalRecovered.setText(totalRecoveredd+"");

                            totalActive.setText(total_active_cases + "");










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
                        Toast.makeText(CovidActivity.this, "Something Went wrong ! Try Again later", Toast.LENGTH_SHORT).show();
                    } else {
                        parseJSON();
                    }
                } else if (isNetworkConnected())
                    Toast.makeText(CovidActivity.this, "Something Went wrong ! Try Again later", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CovidActivity.this, "No Internet Connectivity", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) CovidActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
