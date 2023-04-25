package fr._42.avaj_launcher.Aircraft;

import fr._42.avaj_launcher.Coordinates;
import fr._42.avaj_launcher.WeatherTower;

public class Helicopter extends Aircraft {
        public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
                super(p_id, p_name, p_coordinates);
        }

        public void updateConditions() {
        }

        @Override
        public void registerTower(WeatherTower p_tower) {
                super.registerTower(p_tower);
                System.out.println("Tower says: " + AircraftType.HELICOPTER + "#" + this.name + 
                        "(" + this.id + ") registered to weather tower.");
        }
}

