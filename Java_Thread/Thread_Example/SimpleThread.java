
package javathread;


public class simpleThread extends Thread{
    
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getId() + " is executing the thread");
    }
    public static void main(String []args)
    {
    simpleThread thread1= new simpleThread();
    simpleThread thread2= new simpleThread();
    
    thread1.start();// thread1 start
    thread2.start();// thread2 start
    
    }
    
}
