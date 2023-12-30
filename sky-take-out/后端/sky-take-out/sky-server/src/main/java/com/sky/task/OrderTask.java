package com.sky.task;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/12/30 12:45
 */
@Component
@Slf4j
public class OrderTask {


    @Autowired
    private OrderMapper orderMapper;
    /**
     * 处理超时订单的方法
     */
    @Scheduled(cron = "0 * * * * ? ")
    public void processTimeOutOrder(){
        log.info("定时处理超时订单:{}", LocalDateTime.now());

        List<Orders> orderList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT, LocalDateTime.now().plusMinutes(-15));
        if(orderList!=null&&orderList.size()>0){
            for (Orders orders : orderList) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("支付超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }

    @Scheduled(cron = "0 0 1 * * ? ")
    public void processDeliveryOrder(){
        log.info("定时处理处于派送中的订单：{}",LocalDateTime.now());
        List<Orders> orderList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().plusMinutes(-60));
        if(orderList!=null&&orderList.size()>0){
            for (Orders orders : orderList) {
                orders.setStatus(Orders.CANCELLED);
                orders.setCancelReason("支付超时，自动取消");
                orders.setCancelTime(LocalDateTime.now());
                orderMapper.update(orders);
            }
        }
    }


}
