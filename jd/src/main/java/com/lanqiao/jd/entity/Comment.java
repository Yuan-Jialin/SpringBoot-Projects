package com.lanqiao.jd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer commentId;

    private Integer commentLevel;

    private String commentDetail;

    private String commentTime;

    private Integer productId;

    private Integer userId;

    public Comment(Integer commentLevel, String commentDetail, String commentTime, Integer productId, Integer userId) {
        this.commentLevel = commentLevel;
        this.commentDetail = commentDetail;
        this.commentTime = commentTime;
        this.productId = productId;
        this.userId = userId;
    }
}