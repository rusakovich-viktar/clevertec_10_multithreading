package m;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Client {
    private final List<Integer> data;
    private final ExecutorService executor;
    private final Lock lock;
    private final Random random;

    public Client(int n) {
        data = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            data.add(i);
        }
        executor = Executors.newFixedThreadPool(10);
        lock = new ReentrantLock();
        random = new Random();
    }

    public void sendRequests(Server server) throws InterruptedException {
        List<Future<Integer>> futures = new ArrayList<>();
        while (!data.isEmpty()) {
            lock.lock();
            try {
                if (!data.isEmpty()) {
                    int index = random.nextInt(data.size());
                    int value = data.remove(index);
                    futures.add(executor.submit(new Request(server, value)));
                }
            } finally {
                lock.unlock();
            }
            Thread.sleep(100 + random.nextInt(400));
        }
        executor.shutdown();
        for (Future<Integer> future : futures) {
            try {
                future.get();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }
}