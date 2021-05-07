package com.mybatis.myservice.model;

import lombok.Data;

@Data
public class Dept {
    private String deptno; // 부서번호
    
    private String dname; // 부서이름
    
    private String loc; // 지역
}
