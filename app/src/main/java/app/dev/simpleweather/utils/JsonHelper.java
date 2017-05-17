package app.dev.simpleweather.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import app.dev.simpleweather.AppDelegate;
import app.dev.simpleweather.R;
import app.dev.simpleweather.model.data.City;

/**
 * Created by vaik00 on 17.05.2017.
 */

public class JsonHelper {

    public JsonHelper() {

    }

    public List<City> getCityList() {
        String json = null;
        Gson gson = new Gson();
        InputStream is = AppDelegate.getContext().getResources().openRawResource(R.raw.cities);
        int size = 0;
        try {
            size = is.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[size];
        try {
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gson.fromJson(json, new TypeToken<List<City>>(){}.getType());
    }
}
