package com.mybatis.myservice.service;

import com.mybatis.myservice.dao.DeptDao;
import com.mybatis.myservice.model.Dept;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DeptServiceImpl implements DeptService{
    @Autowired
    DeptDao deptDao;

    @Override
    public List<Dept> selectList() {
        return deptDao.selectList();
    }
}
