package com.java.gulimall.product.exception;

import com.java.common.excepiton.BizCodeEnum;
import com.java.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*
 * 集中处理所有异常
 * */
@Slf4j
@RestControllerAdvice(basePackages = "com.java.gulimall.product.controller")
//@ControllerAdvice(basePackages = "com.java.gulimall.product.controller")
public class GulimallExceptionControllerAdvice {


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题{}，异常类型：{}", e.getMessage(), e.getClass());
        BindingResult bindingResult = e.getBindingResult();

        Map<String, String> map = new HashMap<>();

        bindingResult.getFieldErrors()
                .forEach(items -> {
                    map.put(items.getField(), items.getDefaultMessage());
                });
        return R.error(BizCodeEnum.VAILE_EXCEPTION.getCode(), BizCodeEnum.VAILE_EXCEPTION.getMsg()).put("data", map);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        log.error("错误：",throwable);
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(), BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }
}
