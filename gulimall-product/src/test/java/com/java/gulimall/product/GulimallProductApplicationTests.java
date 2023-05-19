package com.java.gulimall.product;


import com.java.gulimall.product.service.BrandService;

import com.java.gulimall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Comparator;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class GulimallProductApplicationTests {


    @Autowired
    public BrandService brandService;

    @Autowired
    public CategoryService categoryService;


    @Test
    void contextLoads() {


    }

    @Test
    void testFindPath() {
        Long[] catelogPath = categoryService.findCatelogPath(225l);
        log.info("完整路径{}", Arrays.asList(catelogPath));

    }


}
