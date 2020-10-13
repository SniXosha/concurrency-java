package hw2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class JavaReadWriteLock implements ReadWriteLock {
    private final MyLock readLock = new ReadLock();
    private final MyLock writeLock = new WriteLock();
    private int readers = 0;
    private boolean writer = false;

    @Override
    public Lock readLock() {
        return readLock;
    }

    @Override
    public Lock writeLock() {
        return writeLock;
    }

    class ReadLock implements MyLock {
        @Override
        public void lock() {
            synchronized (JavaReadWriteLock.this) {
                while (writer) {
                    sleep();
                }
                readers++;
            }
        }
        @Override
        public void unlock() {
            synchronized (JavaReadWriteLock.this) {
                readers--;
                if (readers == 0) JavaReadWriteLock.this.notify();
            }
        }
    }

    class WriteLock implements MyLock {
        @Override
        public void lock() {
            synchronized (JavaReadWriteLock.this) {
                while (readers > 0 || writer) {
                    sleep();
                }
                writer = true;
            }
        }
        @Override
        public void unlock() {
            synchronized (JavaReadWriteLock.this) {
                writer = false;
                JavaReadWriteLock.this.notifyAll();
            }
        }
    }

    private void sleep() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
