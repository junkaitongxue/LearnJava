package company.own;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterHjk2 implements Runnable {
    private int rid;

    private volatile static AtomicInteger num = new AtomicInteger(1);

    private static final Lock lock = new ReentrantLock();

    private static final Map<String, Condition> conditionMap = new Hashtable<>();


    public PrinterHjk2(int i) {
        Condition condition = lock.newCondition();
        conditionMap.put(String.valueOf(i), condition);
        rid = i;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (num.get() < 36) {
            lock.lock();
            try {
                int i = num.get();
                if (num.get() >= 36) {
                    conditionMap.remove(String.valueOf(rid));
                    Optional.ofNullable(conditionMap.get(getNext(rid))).ifPresent(cond -> cond.signal());
                    System.out.println("hjk");
                    return;
                }
//                System.out.println("thread[hjk] "+ rid + ": " + i);
                if (turnToMe(i)) {
                    System.out.println("thread " + rid + ": " + i);
                    System.out.println("thread " + rid + ": " + (i + 1));
                    System.out.println("thread " + rid + ": " + (i + 2));
                    num.getAndAdd(3);
                    if (conditionMap.containsKey(getNext(rid))) {
                        conditionMap.get(getNext(rid)).signal();
                        conditionMap.get(String.valueOf(rid)).await();
                    } else {
                        lock.wait(10); // 防止疯狂唤醒自己，导致cpu太高
                    }
                } else {
                    conditionMap.get(String.valueOf(rid)).await();
                }
            } finally {
                Optional.ofNullable(conditionMap.get(getNext(rid))).ifPresent(cond -> cond.signal());
                lock.unlock();
            }
        }
        conditionMap.remove(String.valueOf(rid));
        System.out.println("rid" + rid);

    }

    private boolean turnToMe(int i) {
        // 1 => 1 4=> 2 7 => 3 10 ==> 1 13 ==> 1
        // 1      2     3      4         5
        return ((i / 3) + 1) % 3 == 0 ? 3 == rid : ((i / 3) + 1) % 3 == rid;
    }

    private String getNext(int i) {
        if (i == 1) {
            return String.valueOf(2);
        } else if (i == 2) {
            return String.valueOf(3);
        } else if (i == 3) {
            return String.valueOf(1);
        }
        return "";
    }
}
