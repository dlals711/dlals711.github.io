package com.mybatis.myservice.service;

import com.mybatis.myservice.model.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> selectList();
}
