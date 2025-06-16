import java.util.*;

public class ThreadImplementation {

    public static void main(String[] args){
        /*
            1. From Thread Class
            2. From Runnable Interface
            3. From Lambda expression
            4. From Anonymous Class
        */
        Scanner sc= new Scanner(System.in);
        System.out.println("Choose thread implementation- ");

        System.out.println("1. From Thread Class\n2. From Runnable Interface\n3. From Lambda Expressions\n4. From Anonymous Class");
        System.out.print("\nChoose- ");
        int n= sc.nextInt();

        switch (n){
            case 1:
                new WorkerThread("Thread Class- Fish").start();
                new WorkerThread("Thread Class- Sushi").start();
                new WorkerThread("Thread Class- Tuna").start();

                break;

            case 2:
                new Thread(new RunnableWorker("Runnable Interface- Fish")).start();
                new Thread(new RunnableWorker("Runnable Interface- Sushi")).start();
                new Thread(new RunnableWorker("Runnable Interface- Tuna")).start();

                break;

            case 3:
                Runnable r= ()->{
                    System.out.println("-------");
                    for(int i=1; i<=5; i++) System.out.println(Thread.currentThread().getName()+": "+i);

                    System.out.println("\n"+Thread.currentThread().getName()+" is finished");
                };

                Thread t1= new Thread(r);
                Thread t2= new Thread(r);
                Thread t3= new Thread(r);

                t1.setName("Lambda- Tuna");
                t2.setName("Lambda- Sushi");
                t3.setName("Lambda- Fish");

                t1.start();
                t2.start();
                t3.start();

                break;

            case 4:
                Runnable r2= new Runnable(){
                    public void run(){
                        System.out.println("-------");
                        for(int i=1; i<=5; i++) System.out.println(Thread.currentThread().getName()+": "+i);
                        System.out.println("\n"+Thread.currentThread().getName()+" is finished");
                    }
                };

                Thread t4= new Thread(r2);
                Thread t5= new Thread(r2);
                Thread t6= new Thread(r2);

                t4.setName("Anonymous Class- Tuna");
                t5.setName("Anonymous Class- Sushi");
                t6.setName("Anonymous Class- Fish");
                t4.start();
                t5.start();
                t6.start();

                break;
        }
    }
}

class RunnableWorker implements Runnable{
    private String name;

    public RunnableWorker(String name){
        this.name= name;
    }

    public void run(){
        Thread.currentThread().setName(name);
        System.out.println("-------");
        for(int i=1; i<=5; i++) System.out.println(Thread.currentThread().getName()+": "+i);

        System.out.println("\n"+Thread.currentThread().getName()+" is finished");
    }
}

class WorkerThread extends Thread{
    private String name;

    public WorkerThread(String name){
        this.name= name;
    }

    public void run(){
        Thread.currentThread().setName(name);
        System.out.println("-------");
        for(int i=1; i<=5; i++) System.out.println(Thread.currentThread().getName()+": "+i);

        System.out.println("\n"+Thread.currentThread().getName()+" is finished");
        System.out.println("---------");
    }
}