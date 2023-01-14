import java.util.Arrays;

public class Philosopher implements Runnable {

    int id;
    private final Fork[] forks;


    //constructor needs to be extended (two channels)
    public Philosopher(int id, Fork[] forks) {
        this.id = id;
        this.forks = forks;
        if (id >= forks.length) {
            throw new IllegalArgumentException("there are more philosophers then forks");
        }
    }

    @Override
    public void run() {
        while (true) {
            // think
            System.out.println(id + " thinking");

            Fork left = getFork(id);
            Fork right = getFork(id + 1);
            /* START EXCERCISE f ------------------------------------------------*/
            try {
                //right.pickup();
                //left.pickup();
                if (id % 2 == 0) {
                    right.pickup();
                    System.out.println("philosopher " + id + " picked up fork: " + (id + 1));
                    left.pickup();
                    System.out.println("philosopher " + id + " picked up fork: " + id);
                } else {
                    left.pickup();
                    System.out.println("philosopher " + id + " picked up fork: " + id);
                    right.pickup();
                    System.out.println("philosopher " + id + " picked up fork: " + (id + 1));
                }
                /* END EXCERCISE f ------------------------------------------------*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // eat
            System.out.println(id + " eating");
            try {
                left.putdown();
                System.out.println("philosopher " + id + " put down fork: " + id);
                right.putdown();
                System.out.println("philosopher " + id + " put down fork: " + (id + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Fork getFork(int id) {
        return forks[id % forks.length];
    }


    public static void main(String[] args) throws InterruptedException {
        int amountPhilsophers = 5;

        // Initialize forks
        Fork[] forks = new Fork[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork();
            new Thread(forks[i]).start();
        }

        // Initialize philosophers
        Philosopher[] philosophers = new Philosopher[amountPhilsophers];
        for (int i = 0; i < amountPhilsophers; i++) {
            philosophers[i] = new Philosopher(i, forks);
        }

        // Start dinner
        for (Philosopher philosopher : philosophers) {
            new Thread(philosopher).start();
        }
        Thread.sleep(5000);
        System.exit(0);
    }
}