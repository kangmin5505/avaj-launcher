package fr._42.avaj_launcher.Aircraft;


public enum AircraftType {
        JETPLANE("JetPlane"),
        HELICOPTER("Helicopter"),
        BALOON("Baloon");

        private final String type;
        private AircraftType(String p_type) {
                this.type = p_type;
        }

        @Override
        public String toString() {
                return this.type;
        }
}
