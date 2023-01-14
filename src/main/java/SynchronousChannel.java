import java.util.concurrent.SynchronousQueue;

public class SynchronousChannel extends SynchronousQueue<Boolean>{

    private static final long serialVersionUID = 8975785526517311554L;

    public SynchronousChannel() throws InterruptedException {
        super(true);

    }

    /**
     * sends data through the synchronous channel
     * @param data
     * @throws InterruptedException
     */
    public void send(Boolean data) throws InterruptedException{
        put(data);
    }

    /**
     * receives data from the synchronous channel
     * @return
     * @throws InterruptedException
     */
    public Boolean receive() throws InterruptedException{
        return take();
    }

}
