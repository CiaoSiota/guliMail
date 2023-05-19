package com.java.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java.common.utils.PageUtils;
import com.java.gulimall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author Ciao
 * @email siotasama@gmail.com
 * @date 2023-04-24 09:39:27
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

