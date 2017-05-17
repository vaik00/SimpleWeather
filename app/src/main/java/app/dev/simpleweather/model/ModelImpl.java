package app.dev.simpleweather.model;

import app.dev.simpleweather.BuildConfig;
import app.dev.simpleweather.model.api.ApiInterface;
import app.dev.simpleweather.model.api.ApiModule;
import app.dev.simpleweather.model.data.Weather;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vaik00 on 17.05.2017.
 */

public class ModelImpl implements Model {
    private final Observable.Transformer schedulersTransformer;
    private ApiInterface api = ApiModule.getService();

    public ModelImpl() {
        schedulersTransformer = o -> ((Observable) o).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Weather> getWeatherByCityId(int cityId) {
        return api
                .getWeatherByCityId(cityId, BuildConfig.API_APPID)
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }
}
