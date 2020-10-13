package hw2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public interface MyLock extends Lock {
    default void lockInterruptibly() {
    }

    @Override
    default boolean tryLock() {
        return false;
    }

    @Override
    default boolean tryLock(long time, TimeUnit unit) {
        return false;
    }

    @Override
    default Condition newCondition() {
        return null;
    }
}
