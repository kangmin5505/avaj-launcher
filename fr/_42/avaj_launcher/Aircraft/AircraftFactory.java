package fr._42.avaj_launcher.Aircraft;

import fr._42.avaj_launcher.Coordinates;

public class AircraftFactory {
        private static AircraftFactory aircraftFactory = new AircraftFactory();
        private long id;
        
        private AircraftFactory() {
                this.id = 0;
        }

        public static AircraftFactory getInstance() {
                return aircraftFactory;
        }

        public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
                switch (AircraftType.valueOf(p_type.toUpperCase())) {
                        case JETPLANE:
                                return new JetPlane(getNextId(), p_name, p_coordinates);
                        case HELICOPTER:
                                return new Helicopter(getNextId(), p_name, p_coordinates);
                        case BALOON:
                                return new Baloon(getNextId(), p_name, p_coordinates);
                        default:
                                return null;
                }
        }

        private long getNextId() {
                return this.id++;
        }
}
