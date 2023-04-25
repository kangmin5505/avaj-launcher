package fr._42.avaj_launcher;

import java.util.LinkedList;
import java.util.List;

import fr._42.avaj_launcher.Aircraft.Flyable;

public class Tower {
        private List<Flyable> observers;

        protected Tower() {
                observers = new LinkedList<Flyable>();
        }

        public void register(Flyable p_flyable) {
                observers.add(p_flyable);
        }

        public void unregister(Flyable p_flyable) {
                observers.remove(p_flyable);
        }

        protected void conditionChanged() {

        }
}
