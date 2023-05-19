package com.java.gulimall.member.dao;

import com.java.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author Ciao
 * @email siotasama@gmail.com
 * @date 2023-04-24 09:13:31
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
