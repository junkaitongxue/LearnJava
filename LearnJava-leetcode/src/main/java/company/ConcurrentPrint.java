package company;

import lombok.SneakyThrows;

import java.util.concurrent.*;

public class ConcurrentPrint {

    /**
     * 不推荐使用executorService，因为默认保存任务的队列是无界的，使用原生 new ThreadPoolExecutor() 创建线程池根据自己的需求设置参数来创建线程池比较好。
     * 总结：
     * FixedThreadPool和SingleThreadExecutor => 允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而引起OOM异常。
     * CachedThreadPool => 允许创建的线程数为Integer.MAX_VALUE，可能会创建大量的线程，从而引起OOM异常。
     * https://blog.csdn.net/qq_28042463/article/details/108090315
     */
    @SneakyThrows
    public static void main(String[] args) {
        BlockingQueue<Runnable> linkedBlockingDeque = new LinkedBlockingDeque<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 1L, TimeUnit.SECONDS, linkedBlockingDeque);

        try {
            for (int i = 0; i < 3; i++) {
                threadPoolExecutor.execute(new ConcurrentPrinter(i + 1));
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
