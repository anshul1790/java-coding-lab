package com.learn.challenges.designpattern.creational;

public class FactoryPatternExample1 {

    interface Transport {
        void deliver();
    }

    static class Truck implements Transport {

        @Override
        public void deliver() {
            System.out.println("Truck delivers");
        }
    }

    static class Ship implements Transport {

        @Override
        public void deliver() {
            System.out.println("Ship delivers");
        }
    }

    static abstract class Logistics {

        public abstract Transport createTransport();

        public void planDelivery() {
            Transport transport = createTransport();
            transport.deliver();
        }
    }

    static class TruckLogistics extends Logistics {

        @Override
        public Transport createTransport() {
            return new Truck();
        }
    }

    static class SeaLogistics extends Logistics {

        @Override
        public Transport createTransport() {
            return new Ship();
        }
    }

    public static void main(String[] args) {

        Logistics roadLog = new TruckLogistics();
        roadLog.planDelivery();

    }

}
