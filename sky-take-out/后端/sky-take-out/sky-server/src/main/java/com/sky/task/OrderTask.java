package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/12/30 12:45
 */
@Component
@Slf4j
public class OrderTask {
    /**
     * 处理超时订单的方法
     */
    @Scheduled(cron = "0 * * * * ? ")
    public void processTimeOutOrder(){

    }


}
