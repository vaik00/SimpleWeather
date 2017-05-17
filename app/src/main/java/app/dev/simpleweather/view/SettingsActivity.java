package app.dev.simpleweather.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import app.dev.simpleweather.Constants;
import app.dev.simpleweather.R;
import app.dev.simpleweather.model.data.City;
import app.dev.simpleweather.presenter.BasePresenter;
import app.dev.simpleweather.presenter.SettingsPresenter;
import app.dev.simpleweather.view.adapter.CityAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vaik00 on 17.05.2017.
 */

public class SettingsActivity extends BaseActivity implements SettingsView {
    @Bind(R.id.city_recycler)
    RecyclerView cityRecycler;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private SettingsPresenter mPresenter;
    private CityAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPresenter = new SettingsPresenter(this);
        cityRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CityAdapter();
        cityRecycler.setAdapter(adapter);
        mPresenter.getCities();
        adapter.setOnItemClickListener(position -> mPresenter.getCityByPosition(position));
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    public void showCityList(List<City> cityList) {
        adapter.setData(cityList);
    }

    @Override
    public void saveResult(City city) {
        setResult(RESULT_OK, new Intent().putExtra(Constants.EXTRA_CITY, city));
        finish();
    }
}
