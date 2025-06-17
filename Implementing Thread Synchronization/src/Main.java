import java.util.*;
class Main{
    public static void main(String[] args) throws InterruptedException{
        String[] arr= {"1","2","3","4","5","6"};
        Process pr =new Process(arr);

        Thread t1= new Thread(pr,"t1");
        Thread t2= new Thread(pr,"t2");
        Thread t3= new Thread(pr,"t3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(Arrays.asList(arr));
    }
}

class Process extends Thread{
    private String[] arr;
    private Object lock= new Object();
    public Process(String[] arr){
        this.arr= arr;
    }

    @Override
    public void run(){
        String name= Thread.currentThread().getName();
        for(int i=0; i<arr.length; i++){
            addToArr(i, name);
        }
    }

    public void addToArr(int i, String name){
        synchronized(lock){
            arr[i]+=":"+name;
        }
    }
} 