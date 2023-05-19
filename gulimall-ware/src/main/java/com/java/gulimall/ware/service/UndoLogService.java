package com.java.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.java.common.utils.PageUtils;
import com.java.gulimall.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author Ciao
 * @email siotasama@gmail.com
 * @date 2023-04-24 09:46:00
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

