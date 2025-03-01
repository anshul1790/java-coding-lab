package com.learn.challenges.designpattern.structural;

public class AdapterPatternExample3 {

    static class PayPalService {
        public void makePayment(double amount) {
            System.out.println("Making payment using Paypal");
        }
    }

    static class StripeService {
        public void chargePayment(double amount) {
            System.out.println("Making stripe payment");
        }
    }

    interface PaymentProcessor {
        void makePayment(double amount);
    }

    static class PayPalAdapter implements PaymentProcessor {

        PayPalService payPalService;

        public PayPalAdapter(PayPalService payPalService) {
            this.payPalService = payPalService;
        }

        @Override
        public void makePayment(double amount) {
            payPalService.makePayment(amount);
        }
    }
}
