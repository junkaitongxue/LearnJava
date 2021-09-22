package company;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentPrinter implements Runnable {

    private int cid;//记录当前线程的标记
    private int sid = 0;//应该轮到哪一个线程
    private static volatile AtomicInteger sum = new AtomicInteger(0);

    //用不到无参构造，也就没写
    //有参构造
    public ConcurrentPrinter(int cid) {
        this.cid = cid;//当前线程标记
    }

    public static void inCreate() {
        sum.incrementAndGet();
    }

    @SneakyThrows
    @Override
    public void run() {
        while (sum.get() < 36) {
            synchronized (ConcurrentPrinter.class) {
                sid = sum.get() / 3 % 3 + 1;//计算当前应该轮到哪个线程
                if (sid == cid) {//当前线程就是应该轮到的
                    for (int i = 0; i < 3; i++) {
                        inCreate();
                        System.out.println(Thread.currentThread().getName() + ": " + sum.get());
                    }
                    System.out.println();
                    ConcurrentPrinter.class.notifyAll();//我这一轮完事了，你们谁来？看看下一轮我还能不能上！
                } else {//当前线程不是应该轮到的
                    try {
                        ConcurrentPrinter.class.wait();//当前线程别着急再等等！快轮到你了！
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
