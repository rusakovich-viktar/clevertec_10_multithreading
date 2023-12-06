package m;

import java.util.concurrent.Callable;

class Request implements Callable<Integer> {
    private final Server server;
    private final int value;

    public Request(Server server, int value) {
        this.server = server;
        this.value = value;
    }

    @Override
    public Integer call() throws Exception {
        return server.processRequest(value);
    }
}
