package com.java.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.java.common.valid.AddGroup;
import com.java.common.valid.UpdateGroup;
import com.java.common.valid.UpdateStatusGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.gulimall.product.entity.BrandEntity;
import com.java.gulimall.product.service.BrandService;
import com.java.common.utils.PageUtils;
import com.java.common.utils.R;


/**
 * 品牌
 *
 * @author Ciao
 * @email siotasama@gmail.com
 * @date 2023-04-23 20:53:04
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId) {
        BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@Validated({AddGroup.class}) @RequestBody BrandEntity brand/*, BindingResult result*/) {

//        if (result.hasErrors()) {
////            获取校验的错误结果
//            Map<String, String> map = new HashMap<>();
//            result.getFieldErrors().forEach(items -> {
//                String message = items.getDefaultMessage();
//                String field = items.getField();
//                map.put(field, message);
//            });
//
//            return R.error(400, "提交的数据不合法").put("data", map);
//        }

        brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand) {
        brandService.updateDetail(brand);

        return R.ok();
    }

    @RequestMapping("/update/status")
    public R updateUpdateStatus(@Validated(UpdateStatusGroup.class) @RequestBody BrandEntity brand) {
        brandService.updateById(brand);

        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds) {
        brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
