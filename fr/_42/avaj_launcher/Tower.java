package fr._42.avaj_launcher;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fr._42.avaj_launcher.Aircraft.Aircraft;
import fr._42.avaj_launcher.Aircraft.Flyable;

public class Tower {
        private List<Flyable> observers;

        protected Tower() {
                observers = new LinkedList<Flyable>();
        }

        public void register(Flyable p_flyable) {
                Aircraft aircraft = (Aircraft) p_flyable;
                String format = "{0}#{1}({2}) registered to weather tower.";
                Object[] arguments = { aircraft.getType(), aircraft.getName(), aircraft.getID() };
                String message = MessageFormat.format(format, arguments);
                Print.out(message, this);
                observers.add(p_flyable);
        }

        public void unregister(Flyable p_flyable) {
                Aircraft aircraft = (Aircraft) p_flyable;
                String format = "{0}#{1}({2}) unregistered from weather tower.";
                Object[] arguments = { aircraft.getType(), aircraft.getName(), aircraft.getID() };
                String message = MessageFormat.format(format, arguments);
                Print.out(message, this);
                observers.remove(p_flyable);
        }


        protected void conditionChanged() {
                List<Flyable> copyOfObservers = new ArrayList<>(observers);
                for (Flyable observer : copyOfObservers) {
                        observer.updateConditions();
                }
        }

        public int getRegisteredCount() {
                return observers.size();
        }
}
