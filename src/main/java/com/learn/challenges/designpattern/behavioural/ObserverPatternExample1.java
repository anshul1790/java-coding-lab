package com.learn.challenges.designpattern.behavioural;

import java.util.ArrayList;
import java.util.List;

public class ObserverPatternExample1 {

    static class ProductPublisher {

        List<CustomerSubscriber> subscribers = new ArrayList<>();

        void subscribeToProduct(CustomerSubscriber subscriber) {
            subscribers.add(subscriber);
        }

        void addProduct(String productName) {
            System.out.println("A new product is added to the repository");
            for (CustomerSubscriber subscriber : subscribers) {
                subscriber.notification(productName);
            }
        }
    }

    static class CustomerSubscriber {

        String subscriberName;

        public CustomerSubscriber(String subscriberName) {
            this.subscriberName = subscriberName;
        }

        void notification(String product) {
            System.out.println(subscriberName + " product available -> " + product);
        }
    }

    public static void main(String[] args) {
        CustomerSubscriber c1 = new CustomerSubscriber("user1");
        CustomerSubscriber c2 = new CustomerSubscriber("user2");
        CustomerSubscriber c3 = new CustomerSubscriber("user3");

        ProductPublisher publisher = new ProductPublisher();
        publisher.subscribeToProduct(c1);
        publisher.subscribeToProduct(c2);

        publisher.addProduct("product1");
        publisher.addProduct("product2");

    }

}
