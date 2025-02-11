package com.learn.challenges.solid.lsp;

public class Liskov {
    /*
        This is violation of Liskov principle because while Penguin wants
        to override the fly method, it doesnt support that operation
        So we need to make superclasses more generic to be replaced by subclasses
     */
    static abstract class BirdViolate {
        // instead to comply with Liskov, we need to have generic
        // interface or abstract class that should provide support to both
        // Sparrow and Penguin -> like we can have move method instead
        public void fly() {
            System.out.println("Bird is flying");
        }
    }

    static class SparrowViolate extends BirdViolate {
        @Override
        public void fly() {
            System.out.println("Sparrow is flying");
        }
    }

    static class PenguinViolate extends BirdViolate {
        @Override
        public void fly() {
            throw new UnsupportedOperationException("Penguins can't fly");
        }
    }

    // Liskov Impl
    // https://www.baeldung.com/java-liskov-substitution-principle
    // And this similar pattern can be applicable to Interface segregation principal
    static interface Account {
        void deposit(Integer amount);
    }

    static interface WithDrawableAccount extends Account {
        void withdraw(Integer withdrawAmount);
    }

    static class FixedTermDeposit implements Account {

        // cant implement here withdraw because FDs account doesnt support regular withdrawl
        // they support close of FD only
        @Override
        public void deposit(Integer amount) {
        }
    }

    static class SavingsAccount implements WithDrawableAccount {

        // Implements both withdraw and deposit hence it's interface is different
        @Override
        public void deposit(Integer amount) {
        }

        @Override
        public void withdraw(Integer withdrawAmount) {
        }
    }

}
