	private void saveTestUser(String userId, String userName) {
		User newUser = new User();
		newUser.setUserId(userId);
		newUser.setUserName(userName);
		newUser.setEmail("junoyoon@gmail.com");
		newUser.setCreatedUser(getTestUser());
		newUser.setCreatedDate(new Date());
		newUser.setRole(Role.USER);
		userApiController.save(getAdminUser(), newUser);
	}