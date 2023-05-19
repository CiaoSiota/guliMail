package com.java.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java.common.utils.PageUtils;
import com.java.gulimall.product.entity.AttrGroupEntity;
import com.java.gulimall.product.vo.AttrGroupRelationVo;

import java.util.Map;

/**
 * 属性分组
 *
 * @author Ciao
 * @email siotasama@gmail.com
 * @date 2023-04-23 19:55:38
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);

    void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVos);

}

