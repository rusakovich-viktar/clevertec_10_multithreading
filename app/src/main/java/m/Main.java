/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package m;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int n = 100;
        Client client = new Client(n);
        Server server = new Server();
        client.sendRequests(server);
        // Проверка accumulator
        int accumulator = server.getListSize() * (server.getListSize() + 1) / 2;
        System.out.println("Accumulator: " + accumulator);
        System.out.println("Expected: " + ((1 + n) * (n / 2)));
    }
}
