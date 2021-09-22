package multralProcess.demo01_scheduleExecutor;

import ch.qos.logback.core.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import multralProcess.demo01_scheduleExecutor.bean.rawDataBean;

import java.util.*;
import java.util.concurrent.*;

@Slf4j(topic = "Dreamkite")
public class ScheduleExecutor {

    private final long INITIAL_DELAY = 5L;

    private final long DELAY = 5L;

    private static ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();

    public void init() {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {
            log.debug("Start to execute scheduled task.");
            List<rawDataBean> rawDatas = new ArrayList<>();
            rawDatas.add(new rawDataBean("0001", "jun"));
            rawDatas.add(new rawDataBean("0002", "kai"));
            ExecutorService pool = Executors.newFixedThreadPool(rawDatas.size());
            for (rawDataBean rd : rawDatas) {
                if (concurrentMap.containsKey(rd.getId())) {
                    log.info("pass handle data： {}", rd.getId());
                    continue;
                }
                pool.submit(() -> {
                    try {
                        log.debug("Start run :" + rd.getId());
                        concurrentMap.put(rd.getId(), "");
                        new DataExecutor(rd.getId(), rd.getName()).run();
                        try {
                            Thread.sleep(6000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } finally {
                        log.debug("End run {}", rd.getId());
                        concurrentMap.remove(rd.getId());
                    }
                });
            }
            pool.shutdown();
            log.debug("End to shut down pool");

        }, INITIAL_DELAY, DELAY, TimeUnit.SECONDS);
        log.debug("End to init ScheduleExecutor.");
    }

    public static void main(String[] args) {
        new ScheduleExecutor().init();
        log.debug("end");
    }

}
