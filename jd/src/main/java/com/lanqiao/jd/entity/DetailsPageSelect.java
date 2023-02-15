package com.lanqiao.jd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsPageSelect {
    private Integer productId;

    private String productName;

    private Integer productPrice;

    private String businessName;

    private  Integer commentCount;

    private Integer productItemId;

    private String detail1;

    private String detail2;

    private String detail3;

    private String detail4;

    private String detail5;

    private String detail6;


}
