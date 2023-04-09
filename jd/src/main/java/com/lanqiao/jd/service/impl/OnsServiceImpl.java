package com.lanqiao.jd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lanqiao.jd.dao.OneMapper;
import com.lanqiao.jd.entity.Product;
import com.lanqiao.jd.service.OneService;
import org.springframework.stereotype.Service;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/4/7 16:22
 */
@Service
public class OnsServiceImpl extends ServiceImpl<OneMapper, Product>implements OneService {
}
