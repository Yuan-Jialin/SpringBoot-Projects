package com.sky.service;

import com.sky.vo.TurnoverReportVO;

import java.time.LocalDate;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2024/1/1 16:50
 */
public interface ReportService {

    TurnoverReportVO getTurnoverStatistics(LocalDate begin,LocalDate end);
}
