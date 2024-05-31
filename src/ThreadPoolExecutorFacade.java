import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Component
public class ThreadPoolExecutorFacade {
    /*private static ThreadPoolExecutorFacade instance = null;
    static ThreadPoolExecutorFacade getInstance()
    {
        if(instance==null)
        {
            instance = new ThreadPoolExecutorFacade();
        }
        return instance;
    }*/

    FiniteStateMachine fsm;

    private Executor executor;

    public ThreadPoolExecutorFacade()
    {
        executor = new MyThreadPoolExecutor();
    }

    public void subscribe(FiniteStateMachine fsm)
    {
        this.fsm = fsm;
    }

    public void jobEnded(int n)
    {
        fsm.handleEvent(new EventEndAlg(n));
    }

    public void executeJob(MyRunnable job)
    {
        job.subscribe(this);
        executor.execute(job);
    }
}
