package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.emmocodeworks.healthcarefrontline.R;

import java.util.ArrayList;

import classes.CountryModelClass;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    Context context;
    ArrayList<CountryModelClass> CountryList;


    public CountryAdapter(Context context, ArrayList<CountryModelClass> countryList) {
        this.context = context;
        CountryList = countryList;


    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_list_item, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, final int position) {
        CountryModelClass country = CountryList.get(position);

        holder.country_name.setText(country.getCountryName());
        holder.confirmed.setText(country.getConf() + "");
        holder.recovered.setText(country.getRec() + "");
        holder.dead.setText(country.getDes() + "");

        holder.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, "#" + (position + 1), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return CountryList.size();
    }

    public void filterList(ArrayList<CountryModelClass> filteredList) {
        CountryList = filteredList;
        notifyDataSetChanged();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        public TextView country_name, confirmed, recovered, dead,increased_confirmed_number,increased_recoverednumber,increasedtotaldeath;
        public ConstraintLayout constraintLayout;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            country_name = itemView.findViewById(R.id.country_name);
            confirmed = itemView.findViewById(R.id.country_c);
            recovered = itemView.findViewById(R.id.country_r);
            dead = itemView.findViewById(R.id.country_d);
            constraintLayout = itemView.findViewById(R.id.County__item_constrain_layout);
        }
    }
}
