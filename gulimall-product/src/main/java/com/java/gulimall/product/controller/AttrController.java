package com.java.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.java.gulimall.product.vo.AttrRespVo;
import com.java.gulimall.product.vo.AttrVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.java.gulimall.product.entity.AttrEntity;
import com.java.gulimall.product.service.AttrService;
import com.java.common.utils.PageUtils;
import com.java.common.utils.R;


/**
 * 商品属性
 *
 * @author Ciao
 * @email siotasama@gmail.com
 * @date 2023-04-23 20:53:04
 */
@RestController
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;


//    /product/attr/base/list/{catelogId}
//    /product/attr/sale/list/{catelogId}
    @GetMapping("/{attrType}/list/{catelogId}")
    public R baseAttrList(@RequestParam Map<String, Object> params,
                          @PathVariable("catelogId") Long catelogId,
                          @PathVariable("attrType") String type) {
        PageUtils page = attrService.queryBaseAttrPage(params, catelogId, type);
        return R.ok().put("page", page);
    }


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */

//    /product/attr/info/{attrId}
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId) {
//        AttrEntity attr = attrService.getById(attrId);
        AttrRespVo respVo = attrService.getAttrInfo(attrId);

        return R.ok().put("attr", respVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr) {
        attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attr) {
        attrService.updateAttr(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds) {
        attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }

}
