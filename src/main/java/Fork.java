import java.util.concurrent.SynchronousQueue;

public class Fork implements Runnable {
    private SynchronousChannel channel;
    private volatile boolean isInUse;

    public Fork() throws InterruptedException {
        this.channel = new SynchronousChannel();
        this.isInUse = false;
    }

    public void pickup() throws InterruptedException {
        channel.take();
        isInUse = true;
    }

    public void putdown() throws InterruptedException {
        channel.put(true);
        isInUse = false;
    }

    @Override
    public void run() {
        while(true){
            if(!isInUse){
                try {
                    putdown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
