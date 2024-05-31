import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;

public class MyThreadPoolExecutor implements Executor {
    private Queue<Runnable> queue;
    private int nOfThreads;
    private boolean isRunning;

    public MyThreadPoolExecutor()
    {
        queue = new ConcurrentLinkedQueue<Runnable>();
        nOfThreads = 4;
        isRunning = true;
        for(int i=0;i<nOfThreads;i++)
        {
            //System.out.println("thread created");
            new Thread(new WorkerRunnable()).start();
        }
    }

    public void shutdown() {
        isRunning = false;
    }

    @Override
    public void execute(Runnable command) {
        queue.add(command);
        //System.out.println("command added");
    }

    private class WorkerRunnable implements Runnable
    {
        @Override
        public void run() {
            //System.out.println("worker runnable");
            while(isRunning)
            {
                if(!queue.isEmpty())
                {
                    Runnable job = queue.poll();
                    //System.out.println("queue polled");
                    if(job != null)
                    {
                        job.run();
                    }
                    //System.out.println("job runned");
                }
                /*else
                {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }*/
            }
        }
    }
}
