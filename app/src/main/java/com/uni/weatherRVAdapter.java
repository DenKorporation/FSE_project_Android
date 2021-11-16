package com.uni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import static com.uni.MainActivity.database;

public class weatherRVAdapter extends RecyclerView.Adapter<weatherRVAdapter.WeatherViewHolder>{

    private static int viewHolderCount;
    private int numberItems;
    public weatherRVAdapter(int numberofItems){
        numberItems = numberofItems;
        viewHolderCount = 0;
    }
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.weather_rv_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListItem, parent,false);
        WeatherViewHolder viewHolder = new WeatherViewHolder(view);
        viewHolderCount ++;
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
    holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView temp;
        TextView time;
        ImageView icon;
        TextView speed;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            temp = itemView.findViewById(R.id.idTVTemperature);
            time = itemView.findViewById(R.id.idTVTime);
            icon = itemView.findViewById(R.id.idIVCondition);
            speed = itemView.findViewById(R.id.idTVWindSpeed);

        }

        void bind(int listIndex) {
            temp.setText(Float.toString(database.getHourlyForecast()[listIndex].getTemp()));
            icon.setImageResource(MainActivity.map.get(MainActivity.database.getHourlyForecast()[listIndex].getIdIcon()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd MM\n HH:mm z"); // какой формат нужен, выбераем
            sdf.setTimeZone(TimeZone.getTimeZone("GMT+3")); // если нужно даем таймзон
            time.setText(sdf.format(database.getHourlyForecast()[listIndex].getTime()));
            speed.setText(Float.toString(database.getHourlyForecast()[listIndex].getWindSpeed()));
        }
    }
}