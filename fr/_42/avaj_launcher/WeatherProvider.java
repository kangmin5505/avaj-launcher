package fr._42.avaj_launcher;

import java.util.HashMap;
import java.util.Random;

public class WeatherProvider {
        public enum WeatherType {
                RAIN,
                FOG,
                SUN,
                SNOW;
        };

        private static final HashMap<WeatherType, String> weather = new HashMap<>();
        static {
                weather.put(WeatherType.RAIN, "RAIN");
                weather.put(WeatherType.FOG, "FOG");
                weather.put(WeatherType.SUN, "SUN");
                weather.put(WeatherType.SNOW, "SNOW");
        };

        private static final WeatherProvider weatherProvider = new WeatherProvider();

        private WeatherProvider() {
        }

        public static WeatherProvider getInstance() {
                return weatherProvider;
        }

        public String getCurrentWeather(Coordinates p_coordinates) {
                int value;
                try {
                        value = Math.addExact(p_coordinates.getLongitude(), p_coordinates.getLatitude());
                        value += Math.addExact(value, p_coordinates.getHeight());
                        value += Math.addExact(value, new Random().nextInt(4));
                } catch (ArithmeticException e) {
                        value = 0;
                }
                WeatherType type = WeatherType.values()[value % weather.size()];
                return weather.get(type);
        }
}
