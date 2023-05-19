package com.java.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java.common.utils.PageUtils;
import com.java.gulimall.coupon.entity.CouponSpuRelationEntity;

import java.util.Map;

/**
 * 优惠券与产品关联
 *
 * @author Ciao
 * @email siotasama@gmail.com
 * @date 2023-04-24 08:56:36
 */
public interface CouponSpuRelationService extends IService<CouponSpuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

