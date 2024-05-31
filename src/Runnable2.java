public class Runnable2 extends MyRunnable {
    @Override
    public void run() {
        System.out.println("job2 started");
        try {
            Thread.sleep(2000);//имитация другой активной деятельности
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("job2 ended");
        jobEnd();
    }
}
