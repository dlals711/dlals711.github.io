package com.myspring.pro27.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.pro27.user.UserService;
import com.myspring.pro27.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}
}
