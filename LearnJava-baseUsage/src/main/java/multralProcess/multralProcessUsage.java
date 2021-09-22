package multralProcess;

import lombok.extern.slf4j.Slf4j;
import lombok.Data;
import dreamkite.common.Exception.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.Collections;

@Slf4j(topic = "Dreamkite")
public class multralProcessUsage {

    private static List<String> bigData;

    private static List<List<String>> splitBigDatas = new ArrayList<>();

    private static Map<String, String> common_map;

    private static List<String> common_list;

    private static Object OBJECT = new Object();

    static {
        bigData = Arrays.asList("a", "b", "c", "d");
        splitBigDatas.add(bigData.subList(0, 2));
        splitBigDatas.add(bigData.subList(2, 4));
        common_map = new Hashtable<>();
        common_map.put("commonMapKey", "commonMapValue");
        common_list = Collections.synchronizedList(new ArrayList<>());
        common_list.add("commonList");
    }

    public static void main(String[] args) throws InterruptedException, DreamKiteException {
        main01(); // 使用固定线程池去执行任务Runnable，不能捕获异常
//        main02(); // 使用定时线程池去执行定时任务
//        main03(); // 使用固定线程池去执行任务Callable，能获得异常
//        main04(); //尝试守护线程
    }

    private static void main04() throws InterruptedException {
        log.debug("开始运行...");
        Thread t1 = new Thread(() -> {
            log.debug("开始运行...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("运行结束...");
        }, "daemon");
        // 设置该线程为守护线程
        t1.setDaemon(true);
        t1.start();

        Thread.sleep(2000);
        log.debug("运行结束...");
    }


    private static void main03() throws DreamKiteException {
        @Data
        class Solution{
            Solution(String s){
                rawData = s;
            }
            private String rawData;
        }
        @Data
        class ResultModel{
            ResultModel(String s){
                handledData = s;
            }
            private String handledData;
        }
        List<Future<ResultModel>> results=new ArrayList<>(10);
        List<Solution> solutions = new ArrayList<Solution>();
        for (int i = 0; i < 10; i++) {
            solutions.add(new Solution("a"));
        }
        solutions.forEach(e -> e.setRawData("a"));
        solutions.get(4).setRawData("z");
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (Solution solution : solutions) {
            Callable call = (Callable<ResultModel>)() ->{
                    log.debug("solution: {}", solution.getRawData());
                    if (solution.getRawData().equals("z"))throw new Exception();
                    return new ResultModel(solution.getRawData().toUpperCase());
                };

             results.add(executor.submit(call));
        }
        executor.shutdown();
        for (Future<ResultModel> result : results) {
            try {
                log.debug("Result: {}",
                        result.get().getHandledData());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                throw new DreamKiteException("");
            }
        }
    }

    private static void main02() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);
//        for (int i = 0; i < 10; i++) {
//            int finalI = i;
            scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    synchronized (OBJECT) {
//                        try {
                            System.out.println("Thread-01");
//                            Thread.sleep(100);
//                            if (finalI == 9) {
//                                OBJECT.notifyAll();
//                            }
//                            OBJECT.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }
            }, 1000, 10000, TimeUnit.MICROSECONDS);
//        }
//        for (int j = 0; j < 10; j++) {
//            scheduledExecutorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    synchronized (OBJECT) {
//                        try {
//                            System.out.println("Thread-02");
//                            Thread.sleep(500);
//                            OBJECT.notifyAll();
////                            OBJECT.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            });
//        }
//        scheduledExecutorService.shutdown();
        scheduledExecutorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("hjk");
    }

    private static void main01() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (List<String> splitBigData : splitBigDatas) {
            executorService.execute(new Runnable() {
                @lombok.SneakyThrows
                @Override
                public void run() {
                    splitBigData.forEach(e -> System.out.println(e));
                    Thread.sleep(2000);
                    System.out.println(common_map);
                    System.out.println(common_list);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("hjk");
    }
}
