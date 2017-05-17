package app.dev.simpleweather.presenter;

import app.dev.simpleweather.model.Model;
import app.dev.simpleweather.model.ModelImpl;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by vaik00 on 17.05.2017.
 */

public abstract class BasePresenter implements Presenter {
    protected Model dataRepository = new ModelImpl();
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onDestroy() {
        compositeSubscription.clear();
    }
}
