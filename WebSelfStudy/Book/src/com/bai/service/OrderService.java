package com.bai.service;

import com.bai.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
