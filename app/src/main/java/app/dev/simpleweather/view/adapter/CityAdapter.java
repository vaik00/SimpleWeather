package app.dev.simpleweather.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.dev.simpleweather.R;
import app.dev.simpleweather.model.data.City;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vaik00 on 17.05.2017.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder>{
    private static OnItemClickListener sListener;
    private List<City> cityList;

    public CityAdapter(){
        cityList = new ArrayList<>();
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        sListener = listener;
    }

    public void setData(List<City> data){
        cityList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.cityTitle.setText(city.getName());
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.city_title)
        TextView cityTitle;
        public CityViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            cityTitle.setOnClickListener(view -> {
                if(sListener!=null)
                    sListener.onClick(getLayoutPosition());
            });
        }
    }
}
