package company.own;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ConcurrentPrintHjk {


    public static void main(String[] args) {
        LinkedBlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 0, TimeUnit.SECONDS, workQueue);
        try{
            for (int i = 0; i < 3; i++) {
                threadPoolExecutor.execute(new PrinterHjk(i + 1 ));
            }
        }finally {
            threadPoolExecutor.shutdown();
        }
    }

}
