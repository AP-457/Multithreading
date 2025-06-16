import java.util.*;
public class Main{
    public void execute(String name){

        for(int i=1; i<=5; i++){
            long start= System.currentTimeMillis();
            System.out.println("Iteration-"+i+" started("+name+")");
            try{
                Thread.sleep(3000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("Time elapsed("+name+")- "+(System.currentTimeMillis()-start));
            System.out.println("Iteration-"+i+" completed("+name+")");
            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        WorkerThread wt= new WorkerThread();

        Runnable rt= new RunnableWorker();
        Thread t1= new Thread(wt);
        Thread t2= new Thread(rt);

        t1.start();
        t2.start();
    }
}

class WorkerThread extends Thread{
    Main main= new Main();

    public void run(){
        Thread.currentThread().setName("WT");
        String name= Thread.currentThread().getName();
        main.execute(name);
    }
}

class RunnableWorker implements Runnable{
    Main main= new Main();
    public void run(){
        Thread.currentThread().setName("RT");
        String name= Thread.currentThread().getName();
        main.execute(name);
    }
} 