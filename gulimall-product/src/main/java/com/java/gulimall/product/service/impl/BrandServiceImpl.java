package com.java.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.java.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.common.utils.PageUtils;
import com.java.common.utils.Query;

import com.java.gulimall.product.dao.BrandDao;
import com.java.gulimall.product.entity.BrandEntity;
import com.java.gulimall.product.service.BrandService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String) params.get("key");
        LambdaQueryWrapper<BrandEntity> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.eq(BrandEntity::getBrandId, key)
                    .or()
                    .like(BrandEntity::getName, key);
        }


        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

//    保证冗余字段的数据一致
    @Transactional
    @Override
    public void updateDetail(BrandEntity brand) {
        this.updateById(brand);
        if (!StringUtils.isEmpty(brand.getName())) {
//            同步更新关联表中的数据
            categoryBrandRelationService.updateBrand(brand.getBrandId(),brand.getName());

            //TODO 更新其他关联
        }

    }

}