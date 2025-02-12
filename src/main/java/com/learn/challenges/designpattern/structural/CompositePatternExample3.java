package com.learn.challenges.designpattern.structural;

import java.util.ArrayList;
import java.util.List;

public class CompositePatternExample3 {

    interface Item {
        int getPrice();
    }

    static class Product implements Item{
        String name;
        int price;

        public Product(String name, int price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public int getPrice() {
            return price;
        }
    }

    static class Box implements Item {

        int boxPrice;
        List<Item> items = new ArrayList<>();

        void addItem(Item item) {
            items.add(item);
        }


        @Override
        public int getPrice() {
            System.out.println("Calculating the box price and total items in it" +
                    "-> " + this.items.size());

            for (Item item : items) {
                boxPrice = boxPrice + item.getPrice();
            }
            System.out.println("Box price is " + boxPrice);
            return boxPrice;
        }

    }


    public static void main(String[] args) {
        Item mainBox = new Box();
        Item product1 = new Product("p1", 10);
        Item product2 = new Product("p1", 20);
        Item product3 = new Product("p1", 5);
        Item product4 = new Product("p1", 2);

        Item smallBox = new Box();
        ((Box) smallBox).addItem(product3);
        ((Box) smallBox).addItem(product4);

        ((Box) mainBox).addItem(product1);
        ((Box) mainBox).addItem(product2);
        ((Box) mainBox).addItem(smallBox);

        mainBox.getPrice();

    }

}
