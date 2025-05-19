
package ThreadPool;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 
public class Task  implements Runnable  {
   


private int taskId; 
public Task(int taskId) { 
this.taskId = taskId; 
} 

@Override 
public void run() { 
System.out.println("Task " + taskId + " is being processed by " + 
Thread.currentThread().getName()); 
} 
} 

