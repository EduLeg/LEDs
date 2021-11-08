
import java.util.logging.Level;
import java.util.logging.Logger;

public class Buffer {
    
    private char buffer;
    
    Buffer() {
        this.buffer = 0;
    }
    
    synchronized char consume() {
        char product = 0;
        
        if(this.buffer == 0) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        product = this.buffer;
        this.buffer = 0;
        notifyAll();
        
        return product;
    }
    
    synchronized void produce(String product) {
        if(this.buffer != 0) {
            try {
                wait(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //this.buffer = product;
        
        notifyAll();
    }
   
}
