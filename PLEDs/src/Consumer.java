
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int waitConsumer;
    int tamConsumer;

    
    Consumer(Buffer buffer, int waitConsumer, int tamConsumer) {
        this.buffer = buffer;
        this.waitConsumer = waitConsumer;
        this.tamConsumer = tamConsumer;
        
    }
    
    @Override
    public void run() {
        System.out.println("Running Consumer...");
        

        Consumidor producto;
        int id_consumidor = 0;
        String product_res;
        
       while(true) {
           
           id_consumidor++;
           if (id_consumidor > tamConsumer){
               id_consumidor = 1;
           }

            producto = this.buffer.consume(id_consumidor);
            product_res = resuelveOp(producto.getOperacion());
            System.out.println("Consumer id: " + producto.getId_consumer());
            System.out.println("Producer id once consumed is: " + producto.getId_producer());
            System.out.println("Consumer consumed: " + product_res);
            
            try {
                Thread.sleep(this.waitConsumer);
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String resuelveOp(String scheme){

        int num1 = Integer.parseInt(String.valueOf(scheme.charAt(3)));
        int num2 = Integer.parseInt(String.valueOf(scheme.charAt(5)));

        switch (scheme.charAt(1)) {
            case '+':
                return String.valueOf(num1 + num2);
            case '-':
                return String.valueOf(num1 - num2);
            case '*':
                return String.valueOf(num1 * num2);
            case '/':
                try{
                    return ""+num1+"/"+num2+"";
                }
                catch(Exception e){
                    return "Indefinido";
                }
               
            default:
                break;
        }
        return "Error";
    } 
   
}
