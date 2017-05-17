package app.dev.simpleweather.presenter;

import app.dev.simpleweather.view.WeatherView;
import rx.Subscription;

/**
 * Created by vaik00 on 17.05.2017.
 */

public class WeatherPresenter extends BasePresenter {
    private WeatherView mWeatherView;

    public WeatherPresenter(WeatherView view) {
        mWeatherView = view;
    }

    public void loadData(int cityId) {
        mWeatherView.showLoader();
        Subscription weatherByCityIdSubscription = dataRepository.getWeatherByCityId(cityId).subscribe(
                result -> {
                    mWeatherView.showWeather(result);
                    mWeatherView.hideLoader();
                }, error -> {
                    mWeatherView.showError(error.getLocalizedMessage());
                    mWeatherView.hideLoader();
                }
        );
        addSubscription(weatherByCityIdSubscription);
    }
}
