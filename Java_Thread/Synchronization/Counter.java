
package javathread.Synchronization;

public class Counter {
    
    private int count = 0; 
// Synchronized method to ensure thread-safe access to the counter 
public synchronized void increment() { 
count++; 
} 
public int getCount() { 
return count; 
} 
}
