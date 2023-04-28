package fr._42.avaj_launcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;

import fr._42.avaj_launcher.Aircraft.AircraftFactory;
import fr._42.avaj_launcher.Aircraft.AircraftFactory.AircraftType;

public class Simulator {
        public static class SimulationException extends Exception {
                public SimulationException(String message) {
                        super(message);
                }
        }

        private static final WeatherTower weatherTower = new WeatherTower();
        private int simulationTime;

        public static void main(String[] args) {
                try {
                        Simulator simulator = new Simulator();
                        String filename = simulator.validateInput(args);
                        simulator.registerAircraft(filename);
                        simulator.run();
                } catch (SimulationException e) {
                        System.err.println(e.getMessage());
                        System.exit(1);
                }
        }

        private void run() {
                while (simulationTime > 0) {
                        if (weatherTower.getRegisteredCount() == 0) {
                                break;
                        }
                        weatherTower.changeWeather();
                        --simulationTime;
                }
                System.out.println("Simulation is ended. Remained second is " + simulationTime + ".");
        }

        private void registerAircraft(String p_filename) throws SimulationException {
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
                                                new Coordinates(longitude, latitude, height))
                                                .registerTower(weatherTower);
                        }
                } catch (IOException e) {
                        throw new SimulationException("[Error] While registering aircrafts.");
                }
        }

        private String validateInput(String[] args) throws SimulationException {
                if (args.length != 1) {
                        throw new SimulationException("[Error] Input only one argument.");
                }

                String filename = args[0];
                FileValidator validator = new FileValidator(filename);
                if (validator.isValid() == false) {
                        String message = MessageFormat.format("[Error] {0} file is not valid", filename);
                        throw new SimulationException(message);
                }

                return filename;
        }
}

class FileValidator {
        private String filename;

        public FileValidator(String filename) {
                this.filename = filename;
        }

        public boolean isValid() throws Simulator.SimulationException {
                boolean validFlag = true;
                try (BufferedReader reader = new BufferedReader(new FileReader(this.filename))) {
                        int lineNumber = 1;
                        String line = reader.readLine();
                        if (isValidSimulationTime(line) == false) {
                                System.err.println("Invalid simulation time: " + line);
                                validFlag = false;
                        }
                        while ((line = reader.readLine()) != null) {
                                lineNumber++;
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
                                        System.err.println("Invalid aircraft coordinate(only Integer): line "
                                                        + lineNumber);
                                        validFlag = false;
                                } else if (isValidCoordinate(Integer.parseInt(parts[2])) == false
                                                || isValidCoordinate(Integer.parseInt(parts[3])) == false) {
                                        System.err.println(
                                                        "Invalid aircraft coordinate(more than 0): line " + lineNumber);
                                        validFlag = false;
                                }

                                if (isInteger(parts[4]) == false
                                                || isValidHeight(Integer.parseInt(parts[4])) == false) {
                                        System.err.println("Invalid height: line " + lineNumber);
                                        validFlag = false;
                                }
                        }
                } catch (IOException e) {
                        throw new Simulator.SimulationException("[Error] While checking file content.");
                }
                return validFlag;
        }

        private boolean isInteger(String s) {
                try {
                        Integer.parseInt(s);
                } catch (NumberFormatException e) {
                        return false;
                }
                return true;
        }

        private boolean isValidSimulationTime(String p_simulationTime) {
                int simulationTime;
                try {
                        simulationTime = Integer.parseInt(p_simulationTime);
                } catch (NumberFormatException e) {
                        return false;
                }
                return simulationTime > 0;
        }

        private boolean isValidAircraftType(String p_type) {
                AircraftType type = AircraftFactory.aircraftType.get(p_type.toUpperCase());
                if (type == null) {
                        return false;
                }
                return true;
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

        private boolean isValidHeight(int p_height) {
                return 0 <= p_height && p_height <= 100;
        }
}
