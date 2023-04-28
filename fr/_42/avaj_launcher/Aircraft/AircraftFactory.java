package fr._42.avaj_launcher.Aircraft;

import java.util.HashMap;
import fr._42.avaj_launcher.Coordinates;

public class AircraftFactory {
        public static enum AircraftType {
                JETPLANE("JetPlane"),
                HELICOPTER("Helicopter"),
                BALOON("Baloon");

                private String type;

                private AircraftType(String p_type) {
                        type = p_type;
                }

                @Override
                public String toString() {
                        return type;
                }
        };

        private static AircraftFactory aircraftFactory = new AircraftFactory();
        public static final HashMap<String, AircraftType> aircraftType = new HashMap<>();
        static {
                aircraftType.put("JETPLANE", AircraftType.JETPLANE);
                aircraftType.put("HELICOPTER", AircraftType.HELICOPTER);
                aircraftType.put("BALOON", AircraftType.BALOON);
        };
        private long id;

        private AircraftFactory() {
                this.id = 0;
        }

        public static AircraftFactory getInstance() {
                return aircraftFactory;
        }

        public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) {
                AircraftType type = aircraftType.get(p_type.toUpperCase());
                if (type == null) {
                        return null;
                }
                Flyable aircraft;
                switch (type) {
                        case JETPLANE:
                                aircraft = new JetPlane(getNextId(), p_name, p_coordinates);
                                break;
                        case HELICOPTER:
                                aircraft = new Helicopter(getNextId(), p_name, p_coordinates);
                                break;
                        case BALOON:
                                aircraft = new Baloon(getNextId(), p_name, p_coordinates);
                                break;
                        default:
                                aircraft = null;
                }
                return aircraft;
        }

        private long getNextId() {
                return this.id++;
        }
}
