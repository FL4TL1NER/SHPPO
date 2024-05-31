public abstract class MyRunnable implements Runnable {
    private int n;
    public int getN()
    {
        return n;
    }

    private ThreadPoolExecutorFacade observer;
    public void subscribe(ThreadPoolExecutorFacade observer)
    {
        this.observer = observer;
    }

    protected void jobEnd()
    {
        observer.jobEnded(n);
    }
}
