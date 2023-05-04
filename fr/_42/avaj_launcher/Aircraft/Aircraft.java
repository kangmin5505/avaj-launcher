package fr._42.avaj_launcher.Aircraft;

import java.util.HashMap;

import fr._42.avaj_launcher.Coordinates;
import fr._42.avaj_launcher.Print;
import fr._42.avaj_launcher.Aircraft.AircraftFactory.AircraftType;
import fr._42.avaj_launcher.WeatherProvider.WeatherType;

public class Aircraft extends Flyable {
        protected long id;
        protected String name;
        protected Coordinates coordinates;
        protected final HashMap<WeatherType, String> messages = new HashMap<>();
        protected HashMap<WeatherType, int[]> changeMap = new HashMap<>();
        protected WeatherType currWeather;
        protected AircraftType type;

        protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
                id = p_id;
                name = p_name;
                coordinates = p_coordinates;
        }

        public AircraftType getType() {
                return type;
        }

        public String getName() {
                return name;
        }

        public long getID() {
                return id;
        }

        private void landing() {
                weatherTower.unregister(this);
        }

        public void updateConditions() {
                String weather = weatherTower.getWeather(coordinates);
                currWeather = WeatherType.valueOf(weather);

                int[] change = changeMap.get(currWeather);
                updateConditions(change);

                if (coordinates.getHeight() <= 0) {
                        landing();
                } else {
                        Print.out(messages.get(currWeather), this);
                }
        }

        private void updateConditions(int[] changes) {
                coordinates.changeLatitude(changes[0]);
                coordinates.changeLongitude(changes[1]);
                coordinates.changeHeight(changes[2]);
        }
}
