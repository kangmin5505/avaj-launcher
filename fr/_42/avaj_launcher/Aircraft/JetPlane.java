package fr._42.avaj_launcher.Aircraft;

import fr._42.avaj_launcher.Coordinates;
import fr._42.avaj_launcher.WeatherTower;
import fr._42.avaj_launcher.Aircraft.AircraftFactory.AircraftType;
import fr._42.avaj_launcher.WeatherProvider.WeatherType;

public class JetPlane extends Aircraft {
        public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
                super(p_id, p_name, p_coordinates);
                type = AircraftType.JETPLANE;

                messages.put(WeatherType.RAIN, "Reduced visibility. Using instruments to navigate.");
                messages.put(WeatherType.FOG, "Visibility severely reduced. Using instruments to navigate.");
                messages.put(WeatherType.SUN, "Smooth flight so far. Enjoying the clear skies.");
                messages.put(WeatherType.SNOW, "Turbulent flight due to snow.");

                changeMap.put(WeatherType.RAIN, new int[] { 5, 0, 0 });
                changeMap.put(WeatherType.FOG, new int[] { 1, 0, 0 });
                changeMap.put(WeatherType.SUN, new int[] { 10, 0, 2 });
                changeMap.put(WeatherType.SNOW, new int[] { 0, 0, -7 });
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