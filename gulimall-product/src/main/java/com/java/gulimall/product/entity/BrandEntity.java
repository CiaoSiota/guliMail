package com.java.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.java.common.valid.AddGroup;
import com.java.common.valid.ListValue;
import com.java.common.valid.UpdateGroup;
import com.java.common.valid.UpdateStatusGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import javax.validation.constraints.*;

/**
 * 品牌
 *
 * @author Ciao
 * @email siotasama@gmail.com
 * @date 2023-04-23 19:55:38
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @TableId
    @NotNull(message = "修改时不能为空",groups = {UpdateGroup.class})
    @Null(message = "新增时必须为空",groups = {AddGroup.class})
    private Long brandId;
    /**
     * 品牌名
     */
    //不能为空或空格
    @NotBlank(message = "品牌名必须提交",groups = {AddGroup.class,UpdateGroup.class})
    private String name;
    /**
     * 品牌logo地址
     */
//	必须是一个合法的url
    @NotBlank(groups = {AddGroup.class})
    @URL(message = "logo必须是一个合法的url地址",groups = {AddGroup.class,UpdateGroup.class})
    private String logo;
    /**
     * 介绍
     */


    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @NotNull(groups = {AddGroup.class, UpdateStatusGroup.class})
    @ListValue(vals = {0,1},groups = {AddGroup.class, UpdateStatusGroup.class},message = "必须提交指定的值")
    private Integer showStatus;
    /**
     * 检索首字母
     */
//	自定义的规则
    @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母")
    @NotEmpty(groups = {AddGroup.class})
    private String firstLetter;
    /**
     * 排序
     */
//   int只能用@notnull
    @Min(value = 0, message = "排序必须大于等于零",groups = {AddGroup.class,UpdateGroup.class})
    @NotNull(groups = {AddGroup.class})
    private Integer sort;

}
