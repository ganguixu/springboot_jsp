package com.ganguixu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void findUserAll(@Param("name") String name, @Param("pass") String pass);
}
