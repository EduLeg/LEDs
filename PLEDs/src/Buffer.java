
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
    
    private Queue<Productos> buffer;
    private int waitConsumer;
    private int waitProducer;
    private int tam;
    private GUIFrame gui;
       
    Buffer(int tam, int waitP, int waitC,GUIFrame gui) {
        this.waitConsumer = waitC;
        this.waitProducer = waitP;
        this.buffer = new LinkedList<Productos>(); 
        this.tam = tam;
        this.gui=gui;
    }
    
    synchronized Consumidor consume(int id) {
        Consumidor product = null;

        while(this.buffer.isEmpty()) {
            try {
                System.out.println("Estoy esperando para consumir");

                wait(this.waitConsumer);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        product = new Consumidor(id,this.buffer.element().getId(), this.buffer.element().getOperacion());
        this.buffer.remove();
        notifyAll();       
        return product;
    }
    
    synchronized void produce(String product, int id) {
       
        while(this.buffer.size() == this.tam) {
            System.out.println("Estoy esperando para seguir produciendo");

            try {
                wait(this.waitProducer);
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Productos producto = new Productos(id, product);
        this.buffer.add(producto);
        //jTable1.getModel().setValueAt(value, row, column);
        gui.putTabla1(id, product);
        
        notifyAll();
    }
}

class Consumidor{
    
    private int id_consumer;
    private int id_producer;
    private String operacion;

    public Consumidor(int id_consumer, int id_producer, String operacion) {
        this.id_consumer = id_consumer;
        this.id_producer = id_producer;
        this.operacion = operacion;
    }

    public int getId_consumer() {
        return id_consumer;
    }

    public void setId_consumer(int id_consumer) {
        this.id_consumer = id_consumer;
    }

    public int getId_producer() {
        return id_producer;
    }

    public void setId_producer(int id_producer) {
        this.id_producer = id_producer;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }   
}
class Productos{
    
    private int id;
    private String operacion;

    public Productos(int id, String operacion) {
        this.id = id;
        this.operacion = operacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    } 
} 


