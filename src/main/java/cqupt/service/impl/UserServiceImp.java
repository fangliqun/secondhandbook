package cqupt.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cqupt.dao.UserDao;
import cqupt.exception.UserException;
import cqupt.po.User;
import cqupt.service.UserService;
@Service
public class UserServiceImp implements UserService {
	//@Autowired区别：，一个是spring。先按类型来，+qualifier
	//@Resource一个是j2ee按照名字来
	@Autowired
	private UserDao userDao;
	public int addUser(User user) throws UserException {
		try {
			return userDao.insertUser(user);
		} catch (Exception e) {
			throw new UserException("添加失败");
		}
	}
	public int deleteUser(int userid) throws UserException {
		try {
			return userDao.deleteUser(userid);
		} catch (Exception e) {
			throw new UserException("删除失败");
		}
	}
	public int modifyUser(User user) throws UserException {
		try {
			return userDao.modifyUser(user);
		} catch (Exception e) {
			throw new UserException("更新失败");
		}
	}
	public User login(User user) throws UserException {
		try {
			return userDao.loginUser(user);
		} catch (Exception e) {
			throw new UserException("登录失败");
		}
	}
	public User getUserInfoByUserId(int userid) throws UserException {
		try {
			return userDao.getUserInfoByUserId(userid);
		} catch (Exception e) {
			throw new UserException("通过id查询失败");
		}
	}
	public User selectUserByUsername(String username) throws UserException {
		try {
			return userDao.selectUserByUsername(username);
		} catch (Exception e) {
			throw new UserException("通过用户名查询失败");
		}
	}
}
