package fr._42.avaj_launcher;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.text.MessageFormat;

import fr._42.avaj_launcher.Aircraft.Aircraft;
import fr._42.avaj_launcher.Simulator.SimulationException;

public class Print {
    private static PrintStream printOut = null;

    public static void setOutputFile(String filename) throws SimulationException {
        try {
            printOut = new PrintStream(new File(filename));
        } catch (IOException e) {
            throw new SimulationException(e.getMessage());
        }
    }

    public static void error(String message) {
        System.err.println("[Error] " + message);
    }

    public static void out(String message, Tower tower) {
        out("Tower says: " + message);
    }

    public static void out(String message, Aircraft aircraft) {
        String pattern = "{0}#{1}({2}): ";
        Object[] arguments = { aircraft.getType(), aircraft.getName(), aircraft.getID() };
        String sender = MessageFormat.format(pattern, arguments);
        if (message == null) {
            out(sender + "Set your custom messages using setMessages.");
        } else {
            out(sender + message);
        }
    }

    public static void out(String message) {
        if (printOut == null) {
            System.out.println(message);
        } else {
            printOut.println(message);
        }
    }
}
