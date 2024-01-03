package com.sky.service.impl;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.mapper.UserMapper;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2024/1/1 16:50
 */
@Service
@Slf4j
public class ReportServiceImpl implements ReportService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dataList = new ArrayList<>();
        List<Double> turnoverList = new ArrayList<>();
        while (!begin.isAfter(end)) {
            dataList.add(begin);
            begin = begin.plusDays(1);
        }

        for (LocalDate localDate : dataList) {
            LocalDateTime beginTime = LocalDateTime.of(localDate, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(localDate, LocalTime.MAX);
            Map map = new HashMap();
            map.put("begin", beginTime);
            map.put("end", endTime);
            map.put("status", Orders.COMPLETED);
            Double turnover = orderMapper.sumByMap(map);
            if (turnover == null) {
                turnover = (double) 0;
            }
            turnoverList.add(turnover);
        }

        return TurnoverReportVO.builder().dateList(StringUtils.join(dataList, ",")).turnoverList(StringUtils.join(turnoverList, ",")).build();
    }

    @Override
    public UserReportVO getUserStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate> dataList = new ArrayList<>();
        while (!begin.isAfter(end)) {
            dataList.add(begin);
            begin = begin.plusDays(1);
        }
        List<Integer> newUserList = new ArrayList<>();
        List<Integer> totalUserList = new ArrayList<>();

        for (LocalDate date : dataList) {
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            Map map = new HashMap();
            map.put("end", endTime);
            ;
            Integer integer = userMapper.countByMap(map);
            totalUserList.add(integer);
            map.put("begin", beginTime);
            Integer integer1 = userMapper.countByMap(map);
            newUserList.add(integer1);
        }


        return UserReportVO.builder().dateList(StringUtils.join(dataList, ",")).newUserList(StringUtils.join(newUserList, ",")).totalUserList(StringUtils.join(totalUserList, ",")).build();
    }

    @Override
    public OrderReportVO getOrdersStatistics(LocalDate begin, LocalDate end) {

        List<LocalDate> dataList = new ArrayList<>();
        List<Double> turnoverList = new ArrayList<>();
        while (!begin.isAfter(end)) {
            dataList.add(begin);
            begin = begin.plusDays(1);
        }
        List<Integer> orderCountList = new ArrayList<>();
        List<Integer> validOrderCountList = new ArrayList<>();
        int all = 0;
        int valid = 0;
        for (LocalDate date : dataList) {
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            Integer orderCount = getOrderCount(beginTime, endTime, null);
            Integer validOrderCount = getOrderCount(beginTime, endTime, Orders.COMPLETED);
            all += orderCount;
            valid += validOrderCount;
            orderCountList.add(orderCount);
            validOrderCountList.add(validOrderCount);
        }
        double rate = 0;
        if (all != 0)
            rate = valid * 1.0 / all;
        return OrderReportVO.builder().dateList(StringUtils.join(dataList, ",")).orderCountList(StringUtils.join(orderCountList, ","))
                .validOrderCountList(StringUtils.join(validOrderCountList, ",")).validOrderCount(valid).totalOrderCount(all).orderCompletionRate(rate).build();
    }

    private Integer getOrderCount(LocalDateTime begin, LocalDateTime end, Integer status) {
        Map map = new HashMap();
        map.put("begin", begin);
        map.put("end", end);
        map.put("status", status);
        Integer integer = orderMapper.countByMap(map);
        return integer;
    }
}
