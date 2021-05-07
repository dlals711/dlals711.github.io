package com.mybatis.myservice.dao;

import com.mybatis.myservice.model.Dept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptDao {
    public List<Dept> selectList();
}
