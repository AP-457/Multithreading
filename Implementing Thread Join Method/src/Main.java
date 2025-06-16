import java.util.*;
public class Main{

    public static void main(String[] args) throws InterruptedException{
        // 3 join methods-
        // 1. public void synchronized join()
        // 2. public void synchronized join(long milli)
        // 3. public void synchronized join(long mill, long nano)
        long begin= System.currentTimeMillis();
        Worker w= new Worker();

        Thread t1= new Thread(w);
        Thread t2= new Thread(w);
        Thread t3= new Thread(w);

        t1.start();
        long start= System.currentTimeMillis();
        t1.join();
        System.out.println("Time Elapsed after t1- "+(System.currentTimeMillis()-start));

        t2.start();
        start= System.currentTimeMillis();
        t2.join(3000);
        System.out.println("Time Elapsed after t2- "+(System.currentTimeMillis()-start));

        t3.start();
        start= System.currentTimeMillis();
        t3.join(3000,3000);

        System.out.println("Time Elapsed after t3- "+(System.currentTimeMillis()-start));
        System.out.println("This is the Main Thread- "+(System.currentTimeMillis()-begin));
    }
}

class Worker extends Thread{
    int i;
    public Worker(){
        i=1;
    }

    public void run(){
        i+=1;
        System.out.println(Thread.currentThread().getName()+"- "+i);
    }
} 