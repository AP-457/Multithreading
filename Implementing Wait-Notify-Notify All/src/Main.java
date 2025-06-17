public class Main{
    public static void main(String[] args) throws InterruptedException{
        Message msg= new Message("A shared Resource");

        Waiter w1= new Waiter(msg);
        Waiter w2= new Waiter(msg);
        Notifier not= new Notifier(msg);

        Thread t1= new Thread(w1, "w1");
        Thread t2= new Thread(w2, "w2");
        Thread t3= new Thread(not, "notifier");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("All threads have been executed");
    }
}

class Message{
    private String msg;

    public Message(String msg){
        this.msg= msg;
    }

    public void setMsg(String msg){
        this.msg= msg;
    }

    public String getMsg(){
        return msg;
    }
}

class Waiter extends Thread{
    private Message msg;

    public Waiter(Message msg){
        this.msg= msg;
    }

    @Override
    public void run(){
        String name= Thread.currentThread().getName();
        synchronized(msg){
            try{
                System.out.println(name+ " thread is going to wait state at- "+System.currentTimeMillis() );
                msg.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(name+" thread is notified at- "+System.currentTimeMillis());
            msg.setMsg(name+" thread is done with it's processing at- "+System.currentTimeMillis());
            System.out.println(msg.getMsg());

            // System.out.println("Notifying the other thread");
            // msg.notify();
        }
    }
}

class Notifier extends Thread{
    private Message msg;

    public Notifier(Message msg){
        this.msg= msg;
    }

    @Override
    public void run(){
        String name= Thread.currentThread().getName();
        try{
            Thread.sleep(1000);
            synchronized(msg){
                System.out.println(name+" thread has notified all");
                msg.notify();
                msg.notify();
                // msg.notifyAll();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
} 