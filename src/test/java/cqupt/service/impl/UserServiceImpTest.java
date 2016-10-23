package cqupt.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import cqupt.exception.UserException;
import cqupt.po.User;

public class UserServiceImpTest {

	@Test
	public void test() {
		UserServiceImp u=new UserServiceImp();
		User user=new User();
		user.setUsername("chen");
		user.setPassword("123456");
		try {
			User user_=u.login(user);
			System.out.println(user_.getUsername());
		} catch (UserException e) {
			e.printStackTrace();
		}
	}

}
