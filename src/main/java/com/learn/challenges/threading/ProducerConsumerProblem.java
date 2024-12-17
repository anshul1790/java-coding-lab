package com.learn.challenges.threading;

public class ProducerConsumerProblem {
    public static void main(String[] args) {
        MessageReader messageReader = new MessageReader();
        new Thread(new ProducerRunnable(messageReader)).start();
        new Thread(new ConsumerRunnable(messageReader)).start();
    }
}

class MessageReader {

    String message;
    boolean isProduced = false;

    public void write(String message) throws InterruptedException {
        synchronized (this) {
            while (isProduced) {
                wait();
            }
            this.message = message;
            isProduced = true;
            notifyAll();
        }
    }

    public String read() throws InterruptedException {
        synchronized (this) {
            while(!isProduced) {
                wait();
            }
            isProduced = false;
            notifyAll();
            return message;
        }
    }
}


class ProducerRunnable implements Runnable {
    String[] messages = new String[]{
            "Anshul is my name",
            "this is example of threading",
            "consumer should consume the message one by one",
            "producer should produce the message"
    };

    private final MessageReader messageReader;

    public ProducerRunnable(MessageReader messageReader) {
        this.messageReader = messageReader;
    }

    @Override
    public void run() {
        for (String message : messages) {
            try {
                this.messageReader.write(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            this.messageReader.write("done");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ConsumerRunnable implements Runnable{

    private final MessageReader messageReader;

    public ConsumerRunnable(MessageReader messageReader) {
        this.messageReader = messageReader;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String mess = messageReader.read();
                System.out.println("Current message is " + mess);
                if (mess.equals("done")) {
                    return;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
