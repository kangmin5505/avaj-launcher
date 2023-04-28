package fr._42.avaj_launcher.Aircraft;

import fr._42.avaj_launcher.Coordinates;
import fr._42.avaj_launcher.WeatherTower;
import fr._42.avaj_launcher.Aircraft.AircraftFactory.AircraftType;
import fr._42.avaj_launcher.WeatherProvider.WeatherType;

public class Baloon extends Aircraft {
        public Baloon(long p_id, String p_name, Coordinates p_coordinates) {
                super(p_id, p_name, p_coordinates);
                type = AircraftType.BALOON;

                messages.put(WeatherType.RAIN, "Not recommended to fly a balloon in the rain.");
                messages.put(WeatherType.FOG, "Not recommended to fly a balloon in the fog.");
                messages.put(WeatherType.SUN, "Enjoying the clear skies.");
                messages.put(WeatherType.SNOW, "Not recommended to fly a balloon in the snow.");

                changeMap.put(WeatherType.RAIN, new int[] { 0, 0, -5 });
                changeMap.put(WeatherType.FOG, new int[] { 0, 0, -3 });
                changeMap.put(WeatherType.SUN, new int[] { 0, 2, 4 });
                changeMap.put(WeatherType.SNOW, new int[] { 0, 0, -15 });
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