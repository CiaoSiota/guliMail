package com.java.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.java.common.constant.ProductConstant;
import com.java.gulimall.product.dao.AttrAttrgroupRelationDao;
import com.java.gulimall.product.dao.AttrGroupDao;
import com.java.gulimall.product.dao.CategoryDao;
import com.java.gulimall.product.entity.AttrAttrgroupRelationEntity;
import com.java.gulimall.product.entity.AttrGroupEntity;
import com.java.gulimall.product.entity.CategoryEntity;
import com.java.gulimall.product.vo.AttrRespVo;
import com.java.gulimall.product.vo.AttrVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.common.utils.PageUtils;
import com.java.common.utils.Query;

import com.java.gulimall.product.dao.AttrDao;
import com.java.gulimall.product.entity.AttrEntity;
import com.java.gulimall.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    AttrAttrgroupRelationDao relationDao;
    @Autowired
    AttrGroupDao attrGroupDao;
    @Autowired
    CategoryDao categoryDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
//       保存基本数据
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.save(attrEntity);
//        保存关联关系

        if (attr.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {

            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attrEntity.getAttrId());

            relationDao.insert(relationEntity);
        }


    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId, String type) {
        QueryWrapper<AttrEntity> queryWrapper = new QueryWrapper<AttrEntity>().eq("attr_type", "base".equalsIgnoreCase(type) ? ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode() : ProductConstant.AttrEnum.ATTR_TYPE_SALE.getCode());

        if (catelogId != 0) {
            queryWrapper.eq("catelog_id", catelogId);
        }
        String key = (String) params.get("key");
        if (!StringUtils.isEmpty(key)) {
            queryWrapper.and(wrapper ->
                    wrapper.eq("attr_id", key)
                            .or()
                            .like("attr_name", key)
            );
        }
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                queryWrapper
        );
        PageUtils pageUtils = new PageUtils(page);
        List<AttrEntity> records = page.getRecords();


        List<AttrRespVo> respVos = records.stream()
                .map(attr -> {
                    AttrRespVo attrRespVo = new AttrRespVo();
                    BeanUtils.copyProperties(attr, attrRespVo);

//            设置分类和分组的名字

                    if ("base".equalsIgnoreCase(type)) {
                        AttrAttrgroupRelationEntity attrId = relationDao.selectOne(
                                new QueryWrapper<AttrAttrgroupRelationEntity>()
                                        .eq("attr_id", attr.getAttrId())
                        );
                        if (attrId != null) {
                            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrId.getAttrGroupId());
                            attrRespVo.setGroupName(attrGroupEntity.getAttrGroupName());
                        }
                    }
                    CategoryEntity categoryEntity = categoryDao.selectById(attr.getAttrId());

                    if (categoryEntity != null) {
                        attrRespVo.setCatelogName(categoryEntity.getName());
                    }

                    return attrRespVo;
                }).collect(Collectors.toList());
        pageUtils.setList(respVos);
        return pageUtils;

    }

    @Override
    public AttrRespVo getAttrInfo(Long attrId) {
        AttrRespVo respVo = new AttrRespVo();
        AttrEntity attrEntity = this.getById(attrId);

        BeanUtils.copyProperties(attrEntity, respVo);
//        respVo.setAttrGroupId();
//        respVo.setCatelogPath();

        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {

            AttrAttrgroupRelationEntity relationEntity = relationDao.selectOne(new QueryWrapper<AttrAttrgroupRelationEntity>()
                    .eq("attr_id", attrEntity.getAttrId())
            );
            AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrGroupDao.selectById(relationEntity.getAttrGroupId()));
            respVo.setAttrGroupId(relationEntity.getAttrGroupId());
            respVo.setGroupName(attrGroupEntity.getAttrGroupName());
        }


        List<Long> PathList = new ArrayList<>();
        this.getParentPath(respVo.getCatelogId(), PathList);
        Collections.reverse(PathList);
        respVo.setCatelogPath(PathList.toArray(new Long[0]));


        return respVo;
    }


    private void getParentPath(Long catelogId, List<Long> pathList) {
        CategoryEntity categoryEntity = categoryDao.selectOne(new QueryWrapper<CategoryEntity>().eq("cat_id", catelogId));
        pathList.add(categoryEntity.getCatId());

        if (categoryEntity.getParentCid() != 0) {
            this.getParentPath(categoryEntity.getParentCid(), pathList);
        }

    }

    @Transactional
    @Override
    public void updateAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);

        this.updateById(attrEntity);

        if (attrEntity.getAttrType() == ProductConstant.AttrEnum.ATTR_TYPE_BASE.getCode()) {

            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());
            Integer count = relationDao.selectCount(new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId()));
            if (count > 0) {
                //      1  修改分组关联
                relationDao.update(relationEntity,
                        new UpdateWrapper<AttrAttrgroupRelationEntity>().eq("attr_id", attr.getAttrId())
                );
            } else {
                relationDao.insert(relationEntity);
            }

        }

    }

    /*
        根据分组id查找关联的所有基本属性
     */
    @Override
    public List<AttrEntity> getRelationAttr(Long attrgroupId) {

        List<AttrAttrgroupRelationEntity> attrGroupEntities = relationDao.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>()
                .eq("attr_group_id", attrgroupId)
        );
        List<Long> collect = attrGroupEntities.stream()
                .map(AttrAttrgroupRelationEntity::getAttrId)
                .collect(Collectors.toList());

        return this.listByIds(collect);
    }
}
