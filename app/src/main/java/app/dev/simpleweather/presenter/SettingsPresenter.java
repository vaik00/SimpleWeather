package app.dev.simpleweather.presenter;

import java.util.List;

import app.dev.simpleweather.model.data.City;
import app.dev.simpleweather.utils.JsonHelper;
import app.dev.simpleweather.view.SettingsView;

/**
 * Created by vaik00 on 17.05.2017.
 */

public class SettingsPresenter extends BasePresenter {
    private SettingsView mSettingsView;
    private List<City> mCities;

    public SettingsPresenter(SettingsView view){
        mSettingsView = view;
    }

    public void getCities(){
        mCities = new JsonHelper().getCityList();
        mSettingsView.showCityList(mCities);
    }

    public void getCityByPosition(int position){
        mSettingsView.saveResult(mCities.get(position));

    }
}
