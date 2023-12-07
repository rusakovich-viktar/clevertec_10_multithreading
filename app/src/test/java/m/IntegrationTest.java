package m;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IntegrationTest {
    @Test
    public void testClientServerFinalResult() throws InterruptedException {
        int n = 100;
        Server server = new Server();
        Client client = new Client(n);
        client.sendRequests(server);

        // Проверяем, что список сервера содержит все значения от 1 до n
        for (int i = 1; i <= n; i++) {
            assertTrue(server.getList().contains(i));
        }

        // Проверяем итоговое значение accumulator
        int actual = server.getListSize() * (server.getListSize() + 1) / 2;
        int expected = (1 + n) * (n / 2);
        assertEquals(expected, actual);
    }
}
