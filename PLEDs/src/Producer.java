
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Producer extends Thread {
    Buffer buffer;
    private int waitProducer;
    private int n,m;
    private int tamProducer;
    //int id;
    
    Producer(Buffer buffer, int waitP, int n, int m, int tamProducer) {
        this.buffer = buffer;
        this.waitProducer = waitP;
        this.n = n;
        this.m = m;
        this.tamProducer = tamProducer;
    }
    
    @Override
    public void run() {
        System.out.println("Running Producer...");
        String products = "AEIOU";
        Random r = new Random(System.currentTimeMillis());
        char product;
        
        for(int i=0 ; i<5 ; i++) {
            product = products.charAt(r.nextInt(5));
            this.buffer.produce(product);
            System.out.println("Producer produced: " + product);
              
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
