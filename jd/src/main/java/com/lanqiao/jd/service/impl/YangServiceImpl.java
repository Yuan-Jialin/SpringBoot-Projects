package com.lanqiao.jd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lanqiao.jd.dao.YangMapper;
import com.lanqiao.jd.entity.Order;
import com.lanqiao.jd.service.YangService;
import org.springframework.stereotype.Service;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/5/7 21:36
 */
@Service
public class YangServiceImpl extends ServiceImpl<YangMapper, Order> implements YangService {
}
