package app.dev.simpleweather.view;

import java.util.List;

import app.dev.simpleweather.model.data.City;

/**
 * Created by vaik00 on 17.05.2017.
 */

public interface SettingsView {
    void showCityList(List<City> cityList);
    void saveResult(City city);
}
