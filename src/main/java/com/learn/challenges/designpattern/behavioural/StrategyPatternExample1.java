package com.learn.challenges.designpattern.behavioural;

public class StrategyPatternExample1 {
    // similar to dependency injection principle

    // Strategy Interface
    public interface PaymentStrategy {
        void pay(int amount);
    }

    // Concrete Strategy for Credit Card Payment
    static public class CreditCardPayment implements PaymentStrategy {
        private String cardNumber;

        public CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        @Override
        public void pay(int amount) {
            System.out.println("Paid " + amount + " using Credit Card: " + cardNumber);
        }
    }

    // Concrete Strategy for PayPal Payment
    static public class PayPalPayment implements PaymentStrategy {
        private String email;

        public PayPalPayment(String email) {
            this.email = email;
        }

        @Override
        public void pay(int amount) {
            System.out.println("Paid " + amount + " using PayPal: " + email);
        }
    }

    // Concrete Strategy for Bitcoin Payment
    static public class BitcoinPayment implements PaymentStrategy {
        private String walletAddress;

        public BitcoinPayment(String walletAddress) {
            this.walletAddress = walletAddress;
        }

        @Override
        public void pay(int amount) {
            System.out.println("Paid " + amount + " using Bitcoin Wallet: " + walletAddress);
        }
    }

    // Context Class
    static public class PaymentContext {
        private PaymentStrategy strategy;

        public void setPaymentStrategy(PaymentStrategy strategy) {
            this.strategy = strategy;
        }

        public void executePayment(int amount) {
            strategy.pay(amount);
        }
    }

    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Pay using Credit Card
        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        context.executePayment(100);

        // Pay using PayPal
        context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.executePayment(200);

        // Pay using Bitcoin
        context.setPaymentStrategy(new BitcoinPayment("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"));
        context.executePayment(300);
    }


}
