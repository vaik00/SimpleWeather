package app.dev.simpleweather.utils;

/**
 * Created by vaik00 on 17.05.2017.
 */

public class TemperatureHelper {
    public static float getDegreesFromKelvin(float kelvinValue) {
        return kelvinValue - 273.15F;
    }
}
