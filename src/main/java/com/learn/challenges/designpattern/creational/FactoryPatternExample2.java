package com.learn.challenges.designpattern.creational;

public class FactoryPatternExample2 {

    static interface Pastry {
        void bake();
    }

    static class Cake implements Pastry {

        @Override
        public void bake() {
            System.out.println("Baking Cake");
        }
    }

    static class Muffin implements Pastry {

        @Override
        public void bake() {
            System.out.println("Baking Muffin");
        }
    }

    abstract static class Bakery {
        protected abstract Pastry createProduct();
        void orderPastry() {
            Pastry pastry = createProduct();
            pastry.bake();
        }
    }

    static class CakeBake extends Bakery {

        @Override
        protected Pastry createProduct() {
            return new Cake();
        }
    }

    static class MuffinBake extends Bakery {

        @Override
        protected Pastry createProduct() {
            return new Muffin();
        }
    }

    public static void main(String[] args) {
        Bakery bakery = new MuffinBake();
        bakery.orderPastry();
    }

}
