public User saveUser(User user, String password) {
		
		// only change the user's password when creating a new user
		boolean isNewUser = user.getUserId() == null;
		
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		
		if (isNewUser && password != null) {
			//update the new user with the password
			String salt = Security.getRandomToken();
			String hashedPassword = Security.encodeString(password + salt);
			
			updateUserPassword(hashedPassword, salt, Context.getAuthenticatedUser().getUserId(), new Date(), user
			        .getUserId());
		}
		
		return user;
	}