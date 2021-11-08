
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
        Random r = new Random(System.currentTimeMillis());

        int id = 0;
        while (true) {
            
            id++;
            if (id > this.tamProducer){
                id = 1;
            }

            int a = r.nextInt(this.m-this.n+1)+this.n;
            int b = r.nextInt(this.m-this.n+1)+this.n;
            
            String product = opScheme(a,b);
            this.buffer.produce(product,id);
            System.out.println("Producer produced: " + product);
            
            try {
                Thread.sleep(this.waitProducer);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String opScheme(int a, int b){
        String alphabet = "+-*/";
        
        Random r = new Random(System.currentTimeMillis());
        char operador = alphabet.charAt(r.nextInt(alphabet.length()));
        
        String res = "("+operador+" "+a+" "+b+" )";
        return res; 
    }
    
}
