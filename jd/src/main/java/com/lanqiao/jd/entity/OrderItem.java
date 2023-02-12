package com.lanqiao.jd.entity;

import org.springframework.stereotype.Component;

@Component
public class OrderItem {
    private Integer orderItemId;

    private Integer orderId;

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", num=" + num +
                ", trafficNumber=" + trafficNumber +
                '}';
    }

    private Integer productId;

    private Integer num;

    private Integer trafficNumber;

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getTrafficNumber() {
        return trafficNumber;
    }

    public void setTrafficNumber(Integer trafficNumber) {
        this.trafficNumber = trafficNumber;
    }
}