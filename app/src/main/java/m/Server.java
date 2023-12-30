package m;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Getter;

@Getter
class Server {
    private final List<Integer> list;
    private final Lock lock;
    private final Random random;

    public Server() {
        list = new ArrayList<>();
        lock = new ReentrantLock();
        random = new Random();
    }

    public int processRequest(int value) throws InterruptedException {
        Thread.sleep(100 + random.nextInt(900));
        lock.lock();
        try {
            list.add(value);
            return list.size();
        } finally {
            lock.unlock();
        }
    }

    public int getListSize() {
        lock.lock();
        try {
            return list.size();
        } finally {
            lock.unlock();
        }
    }

}