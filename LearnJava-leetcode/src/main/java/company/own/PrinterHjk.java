package company.own;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

public class PrinterHjk implements Runnable {
    private int rid;

    private volatile static AtomicInteger num = new AtomicInteger(1);

    private static final Object lock = new Object();

    public PrinterHjk(int i) {
        rid = i;
    }

    @SneakyThrows
    @Override
    public void run() {
        while(num.get() < 36){
            synchronized (lock) {
                int i = num.get();
                if (num.get() >= 36){
                    return;
                }
//                System.out.println("thread[hjk] "+ rid + ": " + i);
                if(turnToMe(i)){
                    System.out.println("thread "+ rid + ": " + i);
                    System.out.println("thread "+ rid + ": " + (i + 1));
                    System.out.println("thread "+ rid + ": " + (i + 2));
                    num.getAndAdd(3);
                    lock.notifyAll();
                }else{
                    lock.wait(10); // 防止疯狂唤醒自己，导致cpu太高
                }
            }
        }
    }

    private boolean turnToMe(int i) {
        // 1 => 1 4=> 2 7 => 3 10 ==> 1 13 ==> 1
        // 1      2     3      4         5
        return ((i / 3) + 1 ) % 3 == 0 ? 3 == rid : ((i / 3) + 1 ) % 3 == rid;
    }
}
