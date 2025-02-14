package com.learn.challenges.designpattern.behavioural;

public class ChainOfRespExample1 {
    static abstract class Logger {

        protected Logger next;

        public void setNext(Logger next) {
            this.next = next;
        }

        public abstract void log(String message);
    }


    static class ConsoleLogger extends Logger {

        @Override
        public void log(String message) {
            System.out.println("Console logger " + message);
            if (next != null)
                next.log(message);
        }
    }

    static class EmailLogger extends Logger {

        @Override
        public void log(String message) {
            System.out.println("Email logger " + message);
            if (next != null)
                next.log(message);
        }
    }

    public static void main(String[] args) {
        Logger consoleLogger = new ConsoleLogger();
        Logger emailLogger = new EmailLogger();
        consoleLogger.setNext(emailLogger);
        // this message will be passed to series of objects chained using setNext
        consoleLogger.log("my custom message");
    }

}
