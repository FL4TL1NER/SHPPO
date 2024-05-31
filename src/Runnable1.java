public class Runnable1 extends MyRunnable {
    @Override
    public void run() {
        System.out.println("job1 started");
        try {
            Thread.sleep(1000);//имитация активной деятельности
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("job1 ended");
        jobEnd();
    }
}
