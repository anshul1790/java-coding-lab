package com.learn.challenges.designpattern.behavioural;

public class ChainOfRespExample3 {

    abstract static class Middleware {
        Middleware next;

        Middleware linkWith(Middleware next) {
            this.next = next;
            return next;
        }

        boolean checkNext(String email, String password) {
            if (this.next != null) {
                return next.handleRequest(email, password);
            }
            return true;
        }

        abstract boolean handleRequest(String email, String password);

    }

    static class UserExistsMiddleware extends Middleware {

        @Override
        boolean handleRequest(String email, String password) {
            System.out.println("User exists");
            return checkNext(email, password);
        }
    }


    static class AuthMiddleware extends Middleware {

        @Override
        boolean handleRequest(String email, String password) {
            System.out.println("Email and password is correct");
            return checkNext(email, password);
        }
    }

    static class ThrottlingMiddleware extends Middleware {

        @Override
        boolean handleRequest(String email, String password) {
            System.out.println("Throttling works all fine");
            return checkNext(email, password);
        }

    }



    public static void main(String[] args) {
        Middleware m1 = new UserExistsMiddleware();
        m1.linkWith(new AuthMiddleware()).linkWith(new ThrottlingMiddleware());
        m1.handleRequest("test1", "test2");
    }

}
