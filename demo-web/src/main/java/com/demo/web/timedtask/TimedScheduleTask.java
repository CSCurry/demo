package com.demo.web.timedtask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
public class TimedScheduleTask {

    @Scheduled(cron = "0 3/30 * * * ?")
    public void first() {
        log.info("从3分钟开始，每隔30分钟执行一次");
    }

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void second() {
        log.info("从0小时开始，每隔1小时执行一次");
    }
}