package app.dev.simpleweather.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.dev.simpleweather.presenter.BasePresenter;

/**
 * Created by vaik00 on 17.05.2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    protected abstract BasePresenter getPresenter();

    protected abstract int getLayoutId();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onDestroy();
        }
    }
}
