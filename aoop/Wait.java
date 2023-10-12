import java.util.concurrent.atomic.AtomicBoolean;

public class Wait {
    public static void main(String[] args) throws InterruptedException {
        // An object to synchronize on
        Object lock = new Object();

        // A condition that is false at first
        AtomicBoolean cond = new AtomicBoolean(false);

        // This thread wants to wait for 'cond' to become true
        Thread t1 = new Thread(() -> {
            // We are now polling the value of cond continuously, but...
            while (!cond.get()) {
                synchronized (lock) {
                    try {
                        // ...we wait for 'lock'!

                        // This makes the thread suspend until either of these happens:
                        // - The thread is interrupted, throwing InterruptedException
                        // - The lock is notified using notify or notifyAll
                        // - The OS randomly wakes up the thread, a so-called "Spurious wakeup"
                        //   (this is why we use a loop!)
                        lock.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }

            // From here on, the condition is MUST be true
            System.out.println("Condition is now: " + cond.get());
        });
        t1.start();

        System.out.println("Sleeping...");
        // Now in our main thread we wait a second, and then set cond to true
        Thread.sleep(1000);
        cond.set(true);
        System.out.println("Done sleeping...");

        // But, since another thread is waiting for this change, notify them about
        // it through the lock. (You can also use 'cond' as a lock)
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
