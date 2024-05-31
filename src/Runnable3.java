public class Runnable3 extends MyRunnable {
    @Override
    public void run() {
        System.out.println("job3 started");
        try {
            Thread.sleep(3000);//имитация СОВСЕМ ДРУГОЙ активной деятельности
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("job3 ended");
        jobEnd();
    }
}
