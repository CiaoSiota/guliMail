package com.java.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java.common.utils.PageUtils;
import com.java.gulimall.ware.entity.WareOrderTaskEntity;

import java.util.Map;

/**
 * 库存工作单
 *
 * @author Ciao
 * @email siotasama@gmail.com
 * @date 2023-04-24 09:46:00
 */
public interface WareOrderTaskService extends IService<WareOrderTaskEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

