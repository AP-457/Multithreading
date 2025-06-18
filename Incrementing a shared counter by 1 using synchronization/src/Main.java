class Main {
    public static void main(String[] args) throws InterruptedException{
        Worker w= new Worker(123);
        Thread t1= new Thread(w, "First");
        Thread t2= new Thread(w, "Second");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Both threads have finished execution");
    }
}
class Worker implements Runnable{
    private int count;
    private Object lock= new Object();
    public Worker(int count){
        this.count= count;
    }

    @Override
    public void run(){
        String name= Thread.currentThread().getName();
        try{
            Thread.sleep(100);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        synchronized(lock){
            System.out.println(name+" thread is incrementing the counter by 1- "+count);
            count+=1;
            System.out.println(name+" thread has incremented the counter by 1- "+count);
        }
        System.out.println();
    }
} 