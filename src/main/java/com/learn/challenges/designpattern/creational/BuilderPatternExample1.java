package com.learn.challenges.designpattern.creational;

public class BuilderPatternExample1 {

    static class House {
        String foundation;
        String creationDate;
        String roof;
        boolean painted;

        public void setFoundation(String foundation) {
            this.foundation = foundation;
        }

        public void setCreationDate(String creationDate) {
            this.creationDate = creationDate;
        }

        public void setRoof(String roof) {
            this.roof = roof;
        }

        public void setPainted(boolean painted) {
            this.painted = painted;
        }
    }

    static interface HouseBuilder {
        void buildFoundation();
        void buildCreationDate();
        void buildRoof();
        void paintHouse();
        House getHouse();
    }


    static class ConcreteHouseBuilder implements HouseBuilder {

        private final House house;

        public ConcreteHouseBuilder() {
            this.house = new House();
        }

        @Override
        public void buildFoundation() {
            house.setFoundation("Concrete");
        }

        @Override
        public void buildCreationDate() {
            house.setCreationDate("01");
        }

        @Override
        public void buildRoof() {
            house.setRoof("true");
        }

        @Override
        public void paintHouse() {
            house.setPainted(true);
        }

        @Override
        public House getHouse() {
            return house;
        }
    }


    static class Director {
        HouseBuilder houseBuilder;

        public Director(HouseBuilder houseBuilder) {
            this.houseBuilder = houseBuilder;
        }

        public void buildHouse() {
            houseBuilder.buildFoundation();
            houseBuilder.buildCreationDate();
            houseBuilder.buildRoof();
            houseBuilder.paintHouse();
        }

        public House getHouse() {
            return houseBuilder.getHouse();
        }

    }

    public static void main(String[] args) {
        HouseBuilder builder = new ConcreteHouseBuilder();
        Director director = new Director(builder);
        director.buildHouse();
        House house = director.getHouse();
        System.out.println(house);
    }

}
