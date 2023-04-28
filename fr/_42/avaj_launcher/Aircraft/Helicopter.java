package fr._42.avaj_launcher.Aircraft;

import fr._42.avaj_launcher.Coordinates;
import fr._42.avaj_launcher.WeatherTower;
import fr._42.avaj_launcher.Aircraft.AircraftFactory.AircraftType;
import fr._42.avaj_launcher.WeatherProvider.WeatherType;

public class Helicopter extends Aircraft {
        public Helicopter(long p_id, String p_name, Coordinates p_coordinates) {
                super(p_id, p_name, p_coordinates);
                type = AircraftType.HELICOPTER;

                messages.put(WeatherType.RAIN, "Rotor starting to freeze.");
                messages.put(WeatherType.FOG, "Visibility severely reduced.");
                messages.put(WeatherType.SUN, "Enjoying the sunny skies.");
                messages.put(WeatherType.SNOW, "Turbulent flight due to snow.");

                changeMap.put(WeatherType.RAIN, new int[] { 0, 5, 0 });
                changeMap.put(WeatherType.FOG, new int[] { 0, 1, 0 });
                changeMap.put(WeatherType.SUN, new int[] { 0, 10, 2 });
                changeMap.put(WeatherType.SNOW, new int[] { 0, 0, -12 });
        }

        @Override
        public void updateConditions() {
                super.updateConditions();
        }

        @Override
        public void registerTower(WeatherTower p_tower) {
                super.registerTower(p_tower);
        }
}
