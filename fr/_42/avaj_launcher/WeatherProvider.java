package fr._42.avaj_launcher;

public class WeatherProvider {
        private String[] weather;
        private static WeatherProvider weatherProvider = new WeatherProvider();

        private WeatherProvider() {}

        public static WeatherProvider getInstance() {
                return weatherProvider;
        }
        
        public String getCurrentWeather(Coordinates p_coordinates) {
                return "";
        }
}
