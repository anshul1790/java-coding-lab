package com.learn.challenges.threading.challenges;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankTransferMultiThread {
    public static void main(String[] args) {
        BankAccount bankAccount1 = new BankAccount("123", 100);
        BankAccount bankAccount2 = new BankAccount("125", 50);

        new Thread(new Transfer(bankAccount1, bankAccount2, 50)).start();
        new Thread(new Transfer(bankAccount2, bankAccount1, 100)).start();
    }
}

class BankAccount {
    String accountNumber;
    double balance;
    Lock bankAccountLock = new ReentrantLock();

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double withdraw(double amount) throws InterruptedException {
        if (bankAccountLock.tryLock(1, TimeUnit.SECONDS)) {
            try {
                if (this.balance <= 0) {
                    System.out.println("Can't withdraw, the current balance is 0: " + this.balance);
                    return 0;
                } else if (this.balance - amount <= 0) {
                    System.out.println("Can't withdraw, as withdraw amount is more than current balance: "
                            + this.balance);
                    return 0;
                }
                this.balance = balance - amount;
                System.out.println("Withdrew from account " + this.accountNumber + " and final amount is " + this.balance);
                return this.balance;
            } finally {
                bankAccountLock.unlock();
            }
        }
        return 0;
    }

    public double deposit(double amount) throws InterruptedException {
        if (bankAccountLock.tryLock(1, TimeUnit.SECONDS)) {
            try {
                this.balance = this.balance + amount;
                System.out.println("Current balance in account " + this.accountNumber + " is " + this.balance);
                return balance;
            } finally {
                bankAccountLock.unlock();
            }
        }
        return 0;
    }

    public boolean transfer(BankAccount destinationBankAccount, double amount) {
        boolean success = false;
        try {
            //if (this.bankAccountLock.tryLock(1, TimeUnit.SECONDS)) {
                try {
                    if (destinationBankAccount.bankAccountLock.tryLock(1, TimeUnit.SECONDS)) {
                        try {
                            System.out.println("Requested transfer from "
                                    + this.accountNumber + " to " + destinationBankAccount.accountNumber);
                            double newAmount = this.withdraw(amount);
                            if (newAmount > 0) {
                                destinationBankAccount.deposit(newAmount);
                                success = true;
                            }
                        } finally {
                            destinationBankAccount.bankAccountLock.unlock();
                        }
                    }
                } finally {
                    System.out.println("Final amounts in current and destination accounts are " +
                            this.balance + " and " + destinationBankAccount.balance);
                    //this.bankAccountLock.unlock();
                }
            //}
        } catch (InterruptedException e) {
            System.out.println("Transfer interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        return success;
    }
}

class Transfer implements Runnable {
    BankAccount a, b;
    double amount;

    public Transfer(BankAccount a, BankAccount b, double amount) {
        this.a = a;
        this.b = b;
        this.amount = amount;
    }

    @Override
    public void run() {
        /*while (!a.transfer(b, amount)) {
            continue;
        }*/
        a.transfer(b, amount);
    }
}