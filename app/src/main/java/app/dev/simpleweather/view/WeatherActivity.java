package app.dev.simpleweather.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import app.dev.simpleweather.AppDelegate;
import app.dev.simpleweather.Constants;
import app.dev.simpleweather.R;
import app.dev.simpleweather.model.data.City;
import app.dev.simpleweather.model.data.Weather;
import app.dev.simpleweather.presenter.BasePresenter;
import app.dev.simpleweather.presenter.WeatherPresenter;
import app.dev.simpleweather.utils.JsonHelper;
import app.dev.simpleweather.utils.SnackbarHelper;
import app.dev.simpleweather.utils.TemperatureHelper;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vaik00 on 17.05.2017.
 */

public class WeatherActivity extends BaseActivity implements WeatherView {
    @Bind(R.id.city) TextView cityTitle;
    @Bind(R.id.current_temperature) TextView currentTemperature;
    @Bind(R.id.min_temperature) TextView minTemperature;
    @Bind(R.id.max_temperature) TextView maxTemperature;
    @Bind(R.id.description) TextView description;
    @Bind(R.id.weather_image) ImageView weatherImage;
    @Bind(R.id.swipeContainer) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;

    private WeatherPresenter mPresenter;
    private static final int REQUEST_CODE = 200;
    private Handler handler = new Handler();
    private List<City> cities = new JsonHelper().getCityList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                R.color.colorPrimaryDark);
        mPresenter = new WeatherPresenter(this);
        swipeRefreshLayout.setOnRefreshListener(() -> mPresenter.loadData(cities.get(0).getId()));
        startFetchData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            City city = data.getParcelableExtra(Constants.EXTRA_CITY);
            mPresenter.loadData(city.getId());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                final Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivityForResult(settingsIntent, REQUEST_CODE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weather;
    }

    @Override
    public void showWeather(Weather data) {
        cityTitle.setText(data.getName());
        setTemperature(currentTemperature, data.getWeatherShort().getTemp());
        setTemperature(minTemperature, data.getWeatherShort().getTempMin());
        setTemperature(maxTemperature, data.getWeatherShort().getTempMax());
        description.setText(data.getWeatherDetail().getDescription());
        Picasso
                .with(this)
                .load(Constants.BASE_IMAGE_URL + data.getWeatherDetail().getIcon() + ".png")
                .into(weatherImage);
    }

    private void setTemperature(TextView tv, float value) {
        tv.setText(String.format(Locale.getDefault(),
                "%.2f%s",
                TemperatureHelper.getDegreesFromKelvin(value),
                "\u00B0"));
    }

    private void startFetchData(){
        handler.post(runnableCode);
    }

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            if (!AppDelegate.isNetworkAvailable()) {
                showError("Проверьте соединение интернет");
            }else{
                mPresenter.loadData(cities.get(0).getId());
            }
            handler.postDelayed(runnableCode, TimeUnit.MINUTES.toMillis(30));

        }
    };

    @Override
    public void showLoader() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoader() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String errorMessage) {
        SnackbarHelper.showErrorSnackbar(this, errorMessage);
    }
}
