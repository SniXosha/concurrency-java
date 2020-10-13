package hw2;

public class AdvancedAccount {
    private int balance = 0;
    private int preferred = 0;

    public synchronized void deposit(int k) {
        balance += k;
        notifyAll();
    }

    public synchronized void withdraw(int k) {
        while (balance < k || preferred > 0) {
            sleep();
        }
        balance -= k;
    }

    public synchronized void preferredWithdraw(int k) {
        preferred++;
        while (balance < k) {
            sleep();
        }
        balance -= k;
        preferred--;
        notifyAll();
    }

    private void sleep() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
