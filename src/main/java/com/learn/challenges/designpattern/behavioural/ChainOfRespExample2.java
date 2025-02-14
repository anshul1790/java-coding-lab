package com.learn.challenges.designpattern.behavioural;

public class ChainOfRespExample2 {
    static abstract class OrderHandler {

        protected OrderHandler next;

        void setNext(OrderHandler next) {
            this.next = next;
        }

        abstract void handleRequest(String request);

    }


    static class AuthenticationHandler extends OrderHandler {

        @Override
        void handleRequest(String request) {

            System.out.println("Handled the authentication successfully");

            if (next != null)
                next.handleRequest(request);

        }
    }


    static class RateLimitHandler extends OrderHandler {

        @Override
        void handleRequest(String request) {

            System.out.println("Rate limit passed successfully");

            if (next != null)
                next.handleRequest(request);

        }
    }

    static class OrderRepositoryHandler extends OrderHandler {

        @Override
        void handleRequest(String request) {

            System.out.println("The SKU are available");

            if (next != null)
                next.handleRequest(request);

        }
    }



    public static void main(String[] args) {
        OrderHandler o1 = new AuthenticationHandler();
        OrderHandler o2 = new RateLimitHandler();
        OrderHandler o3 = new OrderRepositoryHandler();

        o1.setNext(o2);
        o2.setNext(o3);

        o1.handleRequest("process this order");
    }

}
