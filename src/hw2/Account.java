package hw2;

public class Account {
    private int balance = 0;

    public synchronized void deposit(int k) {
        balance += k;
        notifyAll();
    }

    public synchronized void withdraw(int k) {
        while (balance < k) {
            sleep();
        }
        balance -= k;
    }

    private void sleep() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
