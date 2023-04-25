package fr._42.avaj_launcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import fr._42.avaj_launcher.Aircraft.AircraftFactory;
import fr._42.avaj_launcher.Aircraft.AircraftType;

public class Simulator {
        private static final WeatherTower weatherTower = new WeatherTower();
        private int simulationTime;
        public static void main(String[] args) {
                Simulator simulator = new Simulator();
                String filename = simulator.validateInput(args);
                if (filename == null) {
                        System.exit(1);
                }

                simulator.registerAircraft(filename);
                simulator.run();
        }

        private void run() {
                while (simulationTime--) {
                        weatherTower.changeWeather();
                }
        }

        private void registerAircraft(String p_filename) {

                try (BufferedReader reader = new BufferedReader(new FileReader(p_filename))) {
                        String line = reader.readLine();
                        simulationTime = Integer.parseInt(line);

                        AircraftFactory aircraftFactory = AircraftFactory.getInstance();
                        while ((line = reader.readLine()) != null) {
                                String[] parts = line.split(" ");
                                String aircraftType = parts[0];
                                String name = parts[1];
                                int longitude = Integer.parseInt(parts[2]);
                                int latitude = Integer.parseInt(parts[3]);
                                int height = Integer.parseInt(parts[4]);
                                aircraftFactory.newAircraft(aircraftType, name, 
                                        new Coordinates(longitude, latitude, height)).registerTower(weather);                             
                        }
                } catch (IOException e) {
                        System.err.println("[Error] While registering aircrafts.");
                        return;
                }
        }

        private String validateInput(String[] args) {
                if (args.length != 1) {
                        System.err.println("[Error] Input only one argument.");
                        return null;
                }

                String filename = args[0];
                FileValidator validator = new FileValidator(filename);
                if (validator.isValid() == false) {
                        System.err.printf("[Error] '%s' file is not valid\n", filename);
                        return null;
                }
                
                return filename;
        }
}

class FileValidator {
        private String filename;

        public FileValidator(String filename) {
                this.filename = filename;
        }

        public boolean isValid() {
                boolean validFlag = true;
                try (BufferedReader reader = new BufferedReader(new FileReader(this.filename))) {
                        String line;
                        int lineNumber = 0;

                        while ((line = reader.readLine()) != null) {
                                lineNumber++;
                                if (lineNumber == 1) {
                                        int simulationTime = Integer.parseInt(line);
                                        if (isValidSimulationTime(simulationTime) == false) {
                                                System.err.println("Invalid simulation time: " + simulationTime);
                                                validFlag = false;
                                        }
                                } else {
                                        String[] parts = line.split(" ");
                                        if (parts.length != 5) {
                                                System.err.println("Invalid line format: line " + lineNumber);
                                                validFlag = false;
                                        }
                                        String aircraftType = parts[0];
                                        String name = parts[1];

                                        if (isValidAircraftType(aircraftType) == false) {
                                                System.err.println("Invalid aircraft type: line " + lineNumber);
                                                validFlag = false;
                                        }
                                        if (isValidName(name) == false) {
                                                System.err.println("Invalid name: line " + lineNumber);
                                                validFlag = false;
                                        }
                                        if (isInteger(parts[2]) == false || isInteger(parts[3]) == false) {
                                                System.err.println("Invalid aircraft coordinate(only Integer): line " + lineNumber);
                                                validFlag = false;                                                
                                        } else if (isValidCoordinate(Integer.parseInt(parts[2])) == false
                                                || isValidCoordinate(Integer.parseInt(parts[3])) == false) {
                                                System.err.println("Invalid aircraft coordinate(more than 0): line " + lineNumber);
                                                validFlag = false;                                                
                                        }

                                        if (isInteger(parts[4]) == false || isValidHeight(Integer.parseInt(parts[4])) == false) {
                                                System.err.println("Invalid height: line " + lineNumber);
                                                validFlag = false;
                                        }
                                }
                        }
                } catch (IOException e) {
                        System.err.println("[Error] While checking file content.");
                        return false;
                }

                return validFlag;
        }

        private boolean isInteger(String s) {
                try {
                        Integer.parseInt(s);
                        return true;
                } catch (NumberFormatException e) {
                        return false;
                }
        } 
        private boolean isValidSimulationTime(int p_simulationTime) {
                return p_simulationTime > 0;
        }
        private boolean isValidAircraftType(String p_type) {
                try {
                        switch (AircraftType.valueOf(p_type.toUpperCase())) {
                                case JETPLANE:
                                        return true;
                                case HELICOPTER:
                                        return true;
                                case BALOON:
                                        return true;
                                default:
                                        return true;
                        }
                } catch (IllegalArgumentException e) {
                        return false;
                }
        }
        private boolean isValidName(String p_name) {
                return 0 < p_name.length() && p_name.length() <= 255;
        }
        // coordinates are a few weired.
        // because longitude range is from -180 to 180,
        // latitude range is from -90 to 90.
        // But, this subject says that coordinates are positive number.
        private boolean isValidCoordinate(int p_coord) {
                return p_coord > 0;
        }
        private boolean isValidLongitude
        private boolean isValidHeight(int p_height) {
                return 0 <= p_height && p_height <= 100;
        }
}

