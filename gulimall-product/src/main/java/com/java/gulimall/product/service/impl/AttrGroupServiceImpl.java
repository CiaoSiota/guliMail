package com.java.gulimall.product.service.impl;

import com.java.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.java.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.java.gulimall.product.vo.AttrGroupRelationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.common.utils.PageUtils;
import com.java.common.utils.Query;

import com.java.gulimall.product.dao.AttrGroupDao;
import com.java.gulimall.product.entity.AttrGroupEntity;
import com.java.gulimall.product.service.AttrGroupService;
import org.springframework.util.StringUtils;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    AttrAttrgroupRelationDao relationDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                new Query<AttrGroupEntity>().getPage(params),
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {

        String key = (String) params.get("key");
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.and(obj -> obj
                    .eq("attr_group_id", key)
                    .or()
                    .like("attr_group_name", key)
            );
        }
        if (catelogId == 0) {
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    wrapper
            );
            return new PageUtils(page);
        } else {
            wrapper.eq("catelog_id", catelogId);
            IPage<AttrGroupEntity> page = this.page(
                    new Query<AttrGroupEntity>().getPage(params),
                    wrapper
            );
            return new PageUtils(page);


        }

    }

    @Override
    public void deleteRelation(AttrGroupRelationVo[] attrGroupRelationVos) {

        List<AttrAttrgroupRelationEntity> collect = Arrays.stream(attrGroupRelationVos)
                .map(item -> {
                    AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
                    relationEntity.setAttrId(item.getAttrId());
                    relationEntity.setAttrGroupId(item.getAttrGroupId());
                    return relationEntity;
                }).collect(Collectors.toList());

        relationDao.deleteBatchRelation(collect);

    }

}