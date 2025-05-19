/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javathread;

/**
 *
 * @author thisa
 */
public class RunnableTask implements Runnable{

    public void run(){
    
        System.out.println(Thread.currentThread().getId() + " is executing");
    }
    
    
    public static void main(String[] args) {
        
        RunnableTask task1 = new RunnableTask();
        RunnableTask task2=  new RunnableTask();
        
        Thread thread1= new Thread(task1);
        Thread thread2 = new Thread(task2);
        
        thread1.start();// starts thread 1
        thread2.start();// starts thread 2
    }
    
}
