
package ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPoolExample {
   public static void main(String[] args) { 
// Create a thread pool with 3 threads 
ExecutorService executorService = Executors.newFixedThreadPool(3); 
// Submit tasks to the pool 
for (int i = 1; i <= 5; i++) { 
executorService.submit(new Task(i)); 
} 
// Shutdown the thread pool 
executorService.shutdown(); 
}  
}
