package app.dev.simpleweather.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vaik00 on 17.05.2017.
 */

public class Weather {
    @SerializedName("weather")
    @Expose
    private List<WeatherDetail> weatherDetail = null;
    @SerializedName("main")
    @Expose
    private WeatherShort weatherShort;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;


    //API response with only 1 element in array so... I know this is very bad=)
    public WeatherDetail getWeatherDetail() {
        return weatherDetail.get(0);
    }

    public WeatherShort getWeatherShort() {
        return weatherShort;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
