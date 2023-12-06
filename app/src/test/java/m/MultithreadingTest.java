package m;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Test;

public class MultithreadingTest {
    @Test
    public void testClientServerInteraction() throws InterruptedException {
        int n = 100;
        Server server = new Server();
        Client client = new Client(n);
        CountDownLatch latch = new CountDownLatch(n);

        for (int i = 0; i < n; i++) {
            new Thread(() -> {
                try {
                    client.sendRequests(server);
                    latch.countDown();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }

        latch.await(); // Ожидаем завершения всех потоков
        assertEquals(n, server.getListSize()); // Проверяем, что размер списка сервера равен n
    }
}