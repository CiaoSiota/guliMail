package com.java.gulimall.coupon.dao;

import com.java.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author Ciao
 * @email siotasama@gmail.com
 * @date 2023-04-24 08:56:36
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
