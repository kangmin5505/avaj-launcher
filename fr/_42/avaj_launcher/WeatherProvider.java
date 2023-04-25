package fr._42.avaj_launcher;

import java.Math;



public class WeatherProvider {
        public enum WeatherType {
                RAIN("RAIN"),
                FOG("FOG"),
                SUN("SUN"),
                SNOW("SNOW");
        };

        private String[] weather = {WeatherType.RAIN, WeatherType.FOG, 
                                        WeatherType.SUN, WeatherType.SNOW};
        private static WeatherProvider weatherProvider = new WeatherProvider();

        private WeatherProvider() {}

        public static WeatherProvider getInstance() {
                return weatherProvider;
        }
        
        public String getCurrentWeather(Coordinates p_coordinates) {
                int value;
                try {
                        value = Math.addExact(p_coordinates.longitude + p_coordinates.latitude);
                        value += Math.addExact(value, p_coordinates.height);
                } catch (ArithmeticException e) {
                        value = 0;
                }
                return weather[value % weather.length];
        }
}
