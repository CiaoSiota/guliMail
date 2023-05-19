package com.java.gulimall.ware.dao;

import com.java.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author Ciao
 * @email siotasama@gmail.com
 * @date 2023-04-24 09:46:00
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
