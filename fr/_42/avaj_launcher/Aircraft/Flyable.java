package fr._42.avaj_launcher.Aircraft;

import fr._42.avaj_launcher.WeatherTower;

public abstract class Flyable {
        protected WeatherTower weatherTower;

        public abstract void updateConditions();

        public void registerTower(WeatherTower p_tower) {
                weatherTower = p_tower;
                weatherTower.register(this);
        }
}