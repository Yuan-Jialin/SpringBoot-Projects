package com.lanqiao.jd.entity;

import java.math.BigDecimal;
import java.util.List;

public class OrderVo {
    private List<OrderItem> orderItem;
    private int userId;
    private int userAddressId;
    private BigDecimal totalPrice;

    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserAddressId() {
        return userAddressId;
    }

    public void setUserAddressId(int userAddressId) {
        this.userAddressId = userAddressId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderVo{" +
                "orderItem=" + orderItem +
                ", userId=" + userId +
                ", userAddressId=" + userAddressId +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
