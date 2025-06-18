import java.util.*;
import java.util.concurrent.atomic.*;
class Main {
    public static void main(String[] args) throws InterruptedException{
        Worker w= new Worker(123);

        Thread t1= new Thread(w, "First");
        Thread t2= new Thread(w, "Second");
        Thread t3= new Thread(w, "Third");
        Thread t4= new Thread(w, "Fourth");

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("All threads have finished execution");
        System.out.println("Final Value of the counter is- "+w.getCount());
    }
}



class Worker implements Runnable{

    private AtomicInteger count;
    private Object lock= new Object();

    public Worker(int count){
        this.count= new AtomicInteger(count);
    }

    private int increment(AtomicInteger count){
        return count.incrementAndGet();
    }

    @Override
    public void run(){
        String name= Thread.currentThread().getName();
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(name+" thread is incrementing the counter by 1- "+count.get());
        int k= increment(count);
        System.out.println(name+" thread has incremented the counter by 1- "+k);

        System.out.println();
    }

    public AtomicInteger getCount(){
        return count;
    }
} 