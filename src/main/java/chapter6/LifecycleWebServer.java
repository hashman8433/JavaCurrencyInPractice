package chapter6;

import sun.misc.Request;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.RejectedExecutionException;


public class LifecycleWebServer{
    private final ExecutorService exec = new PseudocodeExecutorService();

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);

        while(!exec.isShutdown()) {
            final Socket connection = socket.accept();
            try {
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        handleRequest(connection);
                    }
                });
            } catch (RejectedExecutionException e) {
                if (exec.isShutdown())
                    log("task submission reject", e);
            }

        }

    }

    public void stop() {
        exec.shutdown();
    }

    void handleRequest(Socket connection) {
        Request req = readRequest(connection);
        if (isShutdownRequest(req))
            stop();
        else
            dispatchRequest(req);
    }

    // +++++++++++++++++ no complete code start +++++++++++++++++++
    private void dispatchRequest(Request req) {
    }

    private boolean isShutdownRequest(Request req) {
        return exec.isShutdown();
    }

    private Request readRequest(Socket connection) {
        return null;
    }

    // +++++++++++++++++ no complete code end +++++++++++++++++++
    private void log(String task_submission_reject, RejectedExecutionException e) {
    }
}
