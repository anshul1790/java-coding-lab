package com.learn.challenges.solid.dip;

public class DependencyInjection {

    interface Switchable {
        void turnOn();
        void turnOff();
    }

    static class LightBulb implements Switchable {
        @Override
        public void turnOn() {
            System.out.println("LightBulb is turned on");
        }

        @Override
        public void turnOff() {
            System.out.println("LightBulb is turned off");
        }
    }

    // Here the high level class doesn't depend on Lighbulb which is low level class directly
    // Instead Switch depends on Switchable rather.
    static class Switch {
        private final Switchable switchable;

        public Switch(Switchable switchable) {
            this.switchable = switchable;
        }

        public void flip(boolean on) {
            if (on) {
                switchable.turnOn();
            } else {
                switchable.turnOff();
            }
        }
    }
}
