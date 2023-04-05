package com.lanqiao.jd.controller;

import com.lanqiao.jd.entity.Dish;
import com.lanqiao.jd.entity.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 袁佳林
 * @Description
 * @Date 2023/4/5 16:03
 */
@RestController
@RequestMapping("/dish")
public class DishController {

        @PostMapping
public R<String> save(@RequestBody Dish dish)
        {
            String s = dish.toString();
            System.out.println(s);
             return R.success("新增菜品成功");
        }

}
