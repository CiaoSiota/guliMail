package com.java.gulimall.product.service.impl;

import com.java.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java.common.utils.PageUtils;
import com.java.common.utils.Query;

import com.java.gulimall.product.dao.CategoryDao;
import com.java.gulimall.product.entity.CategoryEntity;
import com.java.gulimall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {


    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public Long[] findCatelogPath(Long catelogId) {

        List<Long> paths = new ArrayList<>();
        this.findParentPath(catelogId, paths);
        Collections.reverse(paths);
        return paths.toArray(new Long[0]);

    }

    /*
    级联更新所有关联数据
     */
    @Transactional
    @Override
    public void updateCascade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());


    }


    private void findParentPath(Long id, List<Long> paths) {
        paths.add(id);
        Long parentCid = this.getById(id).getParentCid();
        if (parentCid != 0) {
            this.findParentPath(parentCid, paths);
        }
    }

//    @Override
//    public List<CategoryEntity> listWithTree() {
//
//        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
//
//        return categoryEntities.stream()
//                .filter(entitie -> entitie.getParentCid() == 0)
//                .peek(entity -> entity.setChildren(getChilds(entity, categoryEntities)))
//                .sorted(Comparator.comparingInt(e -> (null == e.getSort() ? 0 : e.getSort())))
//                .collect(Collectors.toList());
//
//    }
//
//    //    递归查找所有父亲id
//    private List<CategoryEntity> getChilds(CategoryEntity root, List<CategoryEntity> all) {
//
//        return all.stream()
//                .filter(entity -> entity.getParentCid().equals(root.getCatId()))
//                .peek(entity -> entity.setChildren(getChilds(entity, all)))
//                .sorted(Comparator.comparingInt(entity -> (null == entity.getSort() ? 0 : entity.getSort())))
//                .collect(Collectors.toList());
//
//    }





    @Override
    public List<CategoryEntity> listWithTree() {
        //查询到全部的分类实体
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        //过滤出全部一级分类并且排序
        List<CategoryEntity> list = categoryEntities.stream()
                .filter(entitie -> entitie.getParentCid() == 0)
                .sorted(Comparator.comparingInt(e -> (null == e.getSort() ? 0 : e.getSort())))
                .collect(Collectors.toList());
        // 父类别集合
        Stack<CategoryEntity> stack = new Stack<>();
        stack.addAll(list);
        // 子类别集合
        List<CategoryEntity> childList;

        while (!stack.isEmpty()) {
            //把最后的元素弹出栈
            CategoryEntity entity = stack.pop();

            childList = categoryEntities.stream()
                    .filter(e -> e.getParentCid().equals(entity.getCatId()))
                    .sorted(Comparator.comparingInt(e -> (null == e.getSort() ? 0 : e.getSort())))
                    .collect(Collectors.toList());

            entity.setChildren(childList);

            stack.addAll(childList);
        }
        return list;
    }


}
















