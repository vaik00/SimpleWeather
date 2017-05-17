package app.dev.simpleweather.view;

import app.dev.simpleweather.model.data.Weather;

/**
 * Created by vaik00 on 17.05.2017.
 */

public interface WeatherView {
    void showWeather(Weather data);
    void showLoader();
    void hideLoader();
    void showError(String errorMessage);
}
