package fr._42.avaj_launcher.Aircraft;

import fr._42.avaj_launcher.Coordinates;
import fr._42.avaj_launcher.WeatherTower;


public class JetPlane extends Aircraft {
        public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
                super(p_id, p_name, p_coordinates);
        }

        // TODO: longitude, latitude overflow check
        public void updateConditions() {
        }

        @Override
        public void registerTower(WeatherTower p_tower) {
                super.registerTower(p_tower);
                System.out.println("Tower says: " + AircraftType.JETPLANE + "#" + this.name + 
                        "(" + this.id + ") registered to weather tower.");
        }

}