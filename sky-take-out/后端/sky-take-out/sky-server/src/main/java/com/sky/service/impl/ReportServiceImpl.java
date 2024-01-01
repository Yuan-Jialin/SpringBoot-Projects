package com.sky.service.impl;

import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import com.sky.service.ReportService;
import com.sky.vo.TurnoverReportVO;
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
    @Override
    public TurnoverReportVO getTurnoverStatistics(LocalDate begin, LocalDate end) {
        List<LocalDate>dataList=new ArrayList<>();
        List<Double>turnoverList=new ArrayList<>();
        while (!begin.isAfter(end)){
            dataList.add(begin);
            begin=begin.plusDays(1);
        }

        for (LocalDate localDate : dataList) {
            LocalDateTime beginTime = LocalDateTime.of(localDate, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(localDate, LocalTime.MAX);
            Map map=new HashMap();
            map.put("begin",beginTime);
            map.put("end",endTime);
            map.put("status", Orders.COMPLETED);
            Double turnover=orderMapper.sumByMap(map);
            if(turnover==null){
                turnover= (double) 0;
            }
            turnoverList.add(turnover);
        }

        return TurnoverReportVO.builder().dateList(StringUtils.join(dataList, ",")).turnoverList(StringUtils.join(turnoverList,",")).build();
    }
}
