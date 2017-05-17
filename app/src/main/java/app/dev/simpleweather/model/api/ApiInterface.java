package app.dev.simpleweather.model.api;

import app.dev.simpleweather.model.data.Weather;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by vaik00 on 17.05.2017.
 */

public interface ApiInterface {
    @GET("weather")
    Observable<Weather> getWeatherByCityId(@Query("id") int cityId, @Query("appid") String appId);
}
