package Checker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.emmocodeworks.healthcarefrontline.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import classes.AccessToken;
import classes.HealthDiagnosis;
import classes.HelthItem;
import classes.retro;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SymptomSearch extends AppCompatActivity {

   // ConstraintLayout covid;

    Button addButton;
    EditText symptomSearch;

    String isRedFlag="";
    private Button clearInput;
    private Button btn_getDignosis;
    private List<HelthItem> symptoms;
    private Button btn;
    private String computedHashString = "";
    private String token;

    private Retrofit.Builder builder;
    private Retrofit retrofit;
    private AutoCompleteTextView sympotm_seletion;
    List<HealthDiagnosis> diagnosesResponse;
    String a[] = {"indis","afgan","paki"};
    HashMap<String,Integer> sympsHm = new HashMap<>();
    HashMap<String ,Integer> proposedHm = new HashMap<>();
    HashMap<String ,Integer> issues = new HashMap<>();
    ArrayAdapter<String> arrayAdapter;
    List<Integer> sympIds = new ArrayList<>();
    ListView display_selected_sympoms;
    List<String> proposeSYm;
    List<String> display = new ArrayList<>();
    ListView displayProposedSymptoms;
    //Adapter_display adapter;
    ArrayAdapter adapter1;
    ProgressBar overallProgress;
    LinearLayout progressLayout;
    TextView progressText;
    Spinner gender;
    Spinner yob;
    String gen;
    int year_of_birth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_search);

      //  covid = findViewById(R.id.covid19Constraint);
        addButton = findViewById(R.id.addSymptomBtn);
        overallProgress = findViewById(R.id.progress1);
        progressLayout = findViewById(R.id.progressDisplay1);
        progressText = findViewById(R.id.progressText1);
        sympotm_seletion = findViewById(R.id.Select_symptoms1);

        //http logging
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);


        final InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
       // btn_getDignosis =(Button) findViewById(R.id.get_dignosis);
        //clearInput = findViewById(R.id.clearInput);
        builder = new Retrofit.Builder()
                .baseUrl("https://healthservice.priaid.ch/")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
       // symptomSearch = findViewById(R.id.editTextSearchForSymptom);


        try {
            LoadToken("vr_karthik","XEQtVNyeUmdM5E8j","https://authservice.priaid.ch/login");
        } catch (Exception e) {
            e.printStackTrace();
        }
        gettoken();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String selsy = sympotm_seletion.getText().toString();

                            if (!selsy.equals("") && sympsHm.containsKey(selsy)) {
                                sympIds.add(sympsHm.get(selsy));
                                display.add(selsy);
                                sympotm_seletion.setText(selsy);
                                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                               // adapter.notifyDataSetChanged();
                              //  propossedSymptoms(gen, year_of_birth);
                               // IsRedFlagSet(sympsHm.get(selsy));

                                Intent i = new Intent(SymptomSearch.this, SymptomCheckingActivity.class);
                               // i.putExtra("message", selsy);
                                startActivity(i);
                            } else
                                Toast.makeText(SymptomSearch.this, "Please enter valid symptoms(Search for related symptoms).", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }




/*    class Adapter_display extends ArrayAdapter{
        Context context;
        List<String> dis_string;

        public Adapter_display(@NonNull Context context, int resource, @NonNull List objects) {
            super(context, resource, objects);
            this.context = context;
            dis_string = objects;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            if (view == null)
                view = LayoutInflater.from(context).inflate(R.layout.display_item, parent, false);

            final String str = dis_string.get(position);

            TextView dis = view.findViewById(R.id.displ);
            dis.setText(str);
            Log.i("trigger","yer");

            Button btn = view.findViewById(R.id.remove);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    display.remove(position);
                    sympIds.remove(sympsHm.get(str));
                    remove(display_selected_sympoms.indexOfChild(view));
                    if(!sympIds.isEmpty())
                        propossedSymptoms(gen,year_of_birth);
                    else {
                        adapter1.clear();
                        adapter1.notifyDataSetChanged();
                    }
                }
            });
            return view;
        }
    }*/


    private void LoadToken(String username, String password, String url) throws Exception {

        SecretKeySpec keySpec = new SecretKeySpec(
                password.getBytes(),
                "HmacMD5");
        try {
            Mac mac = Mac.getInstance("HmacMD5");
            mac.init(keySpec);
            byte[] result = mac.doFinal(url.getBytes());
            computedHashString = android.util.Base64.encodeToString(result, android.util.Base64.URL_SAFE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception("Can not create token (NoSuchAlgorithmException)");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new Exception("Can not create token (InvalidKeyException)");
        }
    }

    public void gettoken(){
        progressLayout.setVisibility(View.VISIBLE);
        overallProgress.setVisibility(View.VISIBLE);
        progressText.setText("Loading Symptoms              Please wait...");
        sympotm_seletion.setCursorVisible(false);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://authservice.priaid.ch/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        retro userClient = retrofit.create(retro.class);

        String base= "Bearer vr_karthik:" + computedHashString;
        Log.i("bearer",base);
        String authHeader = base.trim();
        Call<AccessToken> call = userClient.getToken(authHeader);
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                token = response.body().getToken();
                loadsymp();
            }
            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                gettoken();
            }
        });
    }

    public void loadsymp(){
        String extraArgs = token;
        retro userClient = retrofit.create(retro.class);
        Call<List<HelthItem>> call = userClient.loadSymptoms(extraArgs,"en-gb","json");
        call.enqueue(new Callback<List<HelthItem>>() {
            @Override
            public void onResponse(Call<List<HelthItem>> call, Response<List<HelthItem>> response) {
                if (response.isSuccessful()) {
                    symptoms = response.body();
                    for (HelthItem a : symptoms) {
                        sympsHm.put(a.getName(), a.getID());
                    }
                    arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<String>(sympsHm.keySet()));
                    sympotm_seletion.setAdapter(arrayAdapter);
                    progressLayout.setVisibility(View.GONE);
                    sympotm_seletion.setCursorVisible(true);
                    Toast.makeText(SymptomSearch.this, "Symptoms Loaded successfully. You can start now...", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SymptomSearch.this, response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                    loadsymp();
                    progressLayout.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<List<HelthItem>> call, Throwable t) {
                loadsymp();
                progressLayout.setVisibility(View.GONE);
            }
        });
    }


  /*  public void propossedSymptoms(String gender,int yob){
        progressLayout.setVisibility(View.VISIBLE);
        progressText.setText("Loading Proposed symptoms....");
        if(adapter1!= null){
            adapter1.clear();
            proposeSYm.clear();
            adapter1.notifyDataSetChanged();
            proposedHm.clear();
        }
        retro userClient = retrofit.create(retro.class);
        String serialisedIds = null;
        try {
            serialisedIds = new ObjectMapper().writeValueAsString(sympIds);
            Log.i("serializd",serialisedIds);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Call<List<HelthItem>> call = userClient.proposedSymptoms(serialisedIds,gender,yob,token,"en-gb","json");
        call.enqueue(new Callback<List<HelthItem>>() {
            @Override
            public void onResponse(Call<List<HelthItem>> call, Response<List<HelthItem>> response) {
                if (response.isSuccessful() && !response.body().isEmpty()) {
                    Log.i("Frsfd", "Entere here");
                    for (HelthItem a : response.body()) {
                        proposedHm.put(a.getName(), a.getID());
                    }
                    proposeSYm = new ArrayList<String>(proposedHm.keySet());
                    adapter1 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, proposeSYm);
                    displayProposedSymptoms.setAdapter(adapter1);
                    displayProposedSymptoms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String key = adapterView.getItemAtPosition(i).toString();
                            display.add(key);
                            //adapter.notifyDataSetChanged();
                            sympIds.add(proposedHm.get(key));
                            propossedSymptoms(gen, year_of_birth);
                        }
                    });
                    progressLayout.setVisibility(View.GONE);
                } else if (response.body()!=null&&response.body().isEmpty()) {
                    Log.i("fERd", "nullas");
                    Toast.makeText(SymptomSearch.this, "symptoms you have selected are not related!", Toast.LENGTH_SHORT).show();
                    adapter1.clear();
                    adapter1.notifyDataSetChanged();
                    progressLayout.setVisibility(View.GONE);
                } else {
                    Log.i("Infere", response.errorBody().toString());
                    progressLayout.setVisibility(View.GONE);
                    gettoken();
                    propossedSymptoms(gen,year_of_birth);
                }
            }
            @Override
            public void onFailure(Call<List<HelthItem>> call, Throwable t) {
                Log.i("failret",t.getMessage());
                Toast.makeText(SymptomSearch.this, "Please check Your Internet connection.", Toast.LENGTH_SHORT).show();
                progressLayout.setVisibility(View.GONE);
            }
        });
    }
*/

}