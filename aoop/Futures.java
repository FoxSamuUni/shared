import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Futures {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // A future holds a value that is still being generated on another thread
        // It's a "future value"
        CompletableFuture<String> messageFuture = new CompletableFuture<>();

        // This thread will wait a second and then generate "Hello world"
        Thread t = new Thread(() -> {
            try {
                System.out.println("Generating message...");
                Thread.sleep(1000);
                System.out.println("Generated message!");

                // Done, tell threads that need the value we've got it:
                messageFuture.complete("Hello world");
            } catch (InterruptedException e) {
                // In case of an exception, you can delegate that through
                // the future as well
                messageFuture.completeExceptionally(e);
            }
        });

        t.start();

        // 'get()' blocks until the future is completed (exceptionally or not), or the thread is interrupted
        String msg = messageFuture.get();
        System.out.println("Message generated: " + msg);
    }
}
