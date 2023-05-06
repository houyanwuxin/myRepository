package com.bai.service.impl;

import com.bai.Dao.DrugDao;
import com.bai.Dao.OrderDao;
import com.bai.Dao.OrderItemDao;
import com.bai.Dao.impl.DrugDaoImpl;
import com.bai.Dao.impl.OrderDaoImpl;
import com.bai.Dao.impl.OrderItemDaoImpl;
import com.bai.pojo.*;
import com.bai.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {


    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private DrugDao drugDao = new DrugDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 订单号===唯一性
        String orderId = System.currentTimeMillis()+""+userId;
// 创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(), 0,userId);
// 保存订单
        orderDao.saveOrder(order);
// 遍历购物车中每一个商品项转换成为订单项保存到数据库
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
// 获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
// 转换为每一个订单项
            OrderItem orderItem = new
                    OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),
                    orderId);
// 保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);
// 更新库存和销量
            Drug drug = drugDao.queryDrugById(cartItem.getId());
            drug.setSales( drug.getSales() + cartItem.getCount() );
            drug.setStock( drug.getStock() - cartItem.getCount() );
            drugDao.updateDrug(drug);
        }
// 清空购物车
        cart.clear();
        return orderId;
    }
}
