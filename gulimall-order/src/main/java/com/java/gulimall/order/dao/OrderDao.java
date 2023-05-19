package com.java.gulimall.order.dao;

import com.java.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author Ciao
 * @email siotasama@gmail.com
 * @date 2023-04-24 09:39:27
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
