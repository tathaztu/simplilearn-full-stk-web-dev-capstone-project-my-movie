package com.mymovie.service.user;

import java.util.List;

import com.mymovie.domain.User;

public interface UserServiceIF {

	User authenticate(String strUserId, String strPwd);

	User getUserById(String strId);

	User getUserByEmail(String strEmail);

	User saveUser(User user);

	List<User> getAllUsers();
}
