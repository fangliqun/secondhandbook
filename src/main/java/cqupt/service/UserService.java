package cqupt.service;

import cqupt.exception.UserException;
import cqupt.po.User;

public interface UserService {
	public int addUser(User user)throws UserException;
	public int deleteUser(int userid)throws UserException;
	public int modifyUser(User user)throws UserException;
	public User login(User user)throws UserException;
	public User getUserInfoByUserId(int userid)throws UserException;
	public User selectUserByUsername(String username)throws UserException;
}
