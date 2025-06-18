import java.util.*;
class Main {
    private static int BUFFER_SIZE;
    private static Queue<Integer> buffer= new LinkedList<>();

    public Main(int size, Queue<Integer> buffer){
        Main.BUFFER_SIZE= size;
        Main.buffer= buffer;
    }
    public Queue<Integer> getBuffer(){
        return buffer;
    }

    public static void main(String[] args) {
        Queue<Integer> buffeR= new LinkedList<>();
        Main main= new Main(5, buffeR);

        Producer prod= new Producer(main);
        Consumer cons= new Consumer(main);

        Thread t1= new Thread(prod, "Producer Thread");
        Thread t2= new Thread(cons, "Consumer Thread");

        t1.start();
        t2.start();
    }

    public int getSize(){
        return BUFFER_SIZE;
    }

    public void addToBuffer(int i){
        buffer.add(i);
    }
}

class Consumer implements Runnable{
    private Main main;
    public Consumer(Main main){
        this.main= main;
    }

    @Override
    public void run(){
        while(true){
            synchronized(main.getBuffer()){
                while(main.getBuffer().isEmpty()){
                    try{
                        main.getBuffer().wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }

                int value= main.getBuffer().poll();
                System.out.println("Consumer consumed: "+value);
                main.getBuffer().notifyAll();

                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}

class Producer implements Runnable{
    private Main main;
    public Producer(Main main){
        this.main= main;
    }

    @Override
    public void run(){
        int value= 0;
        while(true){
            synchronized(main.getBuffer()){
                while(main.getSize()==main.getBuffer().size()){
                    try{
                        main.getBuffer().wait();
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
                System.out.println("Producer produced- "+value);
                main.addToBuffer(value++);
                main.getBuffer().notifyAll();

                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
} 