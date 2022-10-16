
package com.mymovie.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mymovie.domain.User;
import com.mymovie.repository.UserRespository;

@Service(value = "User Service")
public class UserServiceImpl implements UserServiceIF {

	@Autowired
	private UserRespository userRespository;

	@Override
	public User authenticate(String strUserId, String strPwd) {
		return userRespository.findByStrUserIdAndStrPassword(strUserId, strPwd);
	}

	@Override
	public User getUserById(String strId) {
		Optional<User> user = userRespository.findById(strId);
		return user.isPresent() ? user.get() : null;
	}

	@Override
	public User getUserByEmail(String strEmail) {
		return userRespository.findByStrEMail(strEmail);
	}

	@Override
	public User saveUser(User user) {
		return userRespository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRespository.findAll();
	}

}
