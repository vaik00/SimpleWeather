package app.dev.simpleweather.model;

import app.dev.simpleweather.model.data.Weather;
import rx.Observable;

/**
 * Created by vaik00 on 17.05.2017.
 */

public interface Model {
  Observable<Weather> getWeatherByCityId(int cityId);
}
