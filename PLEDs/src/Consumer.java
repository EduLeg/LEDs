//PROYECTO FINAL PRODUCER- CONSUMER
//A00227346 Lizbeth Ortiz López
//A01275836 Eduardo Alonso Legorreta Sedano
//A01635283 Miriam Paulina Palomera Curiel
//A01636706 Daniel Díaz López

import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {
    Buffer buffer;
    int waitConsumer;
    int tamConsumer;
    GUIFrame gui;
    
    Consumer(Buffer buffer, int waitConsumer, int tamConsumer,GUIFrame gui) {
        this.buffer = buffer;
        this.waitConsumer = waitConsumer;
        this.tamConsumer = tamConsumer;
        this.gui=gui;
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
            gui.putTabla2(id_consumidor,producto.getId_producer(),producto.getOperacion(), product_res);
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
