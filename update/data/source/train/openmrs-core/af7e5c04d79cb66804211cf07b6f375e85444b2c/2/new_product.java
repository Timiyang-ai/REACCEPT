public User saveUser(User user, String password) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		if (password != null) {
			//update the new user with the password
			String salt = Security.getRandomToken();
			String hashedPassword = Security.encodeString(password + salt);
			
			updateUserPassword(hashedPassword, salt, Context.getAuthenticatedUser().getUserId(), new Date(), user
			        .getUserId());
		}
		return user;
	}