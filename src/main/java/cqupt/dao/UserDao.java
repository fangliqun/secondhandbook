package cqupt.dao;

import org.springframework.stereotype.Repository;

import cqupt.exception.UserException;
import cqupt.po.User;
//@Repository
public interface UserDao {
	//注册
	int insertUser(User user)throws Exception;
	//登录，并且拿到userid
	User loginUser(User user)throws Exception;
	
	User selectUserByUsername(String username)throws Exception;
	//更新user
	int modifyUser(User user)throws Exception;
	//得到user信息
	User getUserInfoByUserId(int userid)throws Exception;
	//删除user
	int deleteUser(int userid)throws Exception;
	
	
}
