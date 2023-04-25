package fr._42.avaj_launcher.Aircraft;

import fr._42.avaj_launcher.Coordinates;

public class Aircraft extends Flyable {
        protected long id;
        protected String name;
        protected Coordinates coordinates;

        protected Aircraft(long p_id, String p_name, Coordinates p_coordinates) {
                id = p_id;
                name = p_name;
                coordinates = p_coordinates;
        }
        
        public void updateConditions() {
                String weather = p_tower.getCurrentWeather(p_coordinates);
                switch (weather) {
                        case RAIN:
                                break;
                        case FOG:
                                break;
                        case SUN:
                                break;
                        case SNOW:
                                break;
                }
        }
}
