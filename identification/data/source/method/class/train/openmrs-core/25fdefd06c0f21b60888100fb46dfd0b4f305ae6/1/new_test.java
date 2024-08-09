@Test
	@Verifies(value = "should change the hashed password for the given user", method = "changeHashedPassword(User,String,String)")
	public void changeHashedPassword_shouldChangeTheHashedPasswordForTheGivenUser() throws Exception {
		UserService userService = Context.getUserService();
		User user = userService.getUser(1);
		String salt = Security.getRandomToken();
		String hash = Security.encodeString("new password" + salt);
		userService.changeHashedPassword(user, hash, salt);
		
		// TODO Review this a little further
		// This is the assert - checks to see if current user can use the new password
		userService.changePassword("new password", "another new password"); // try to change the password with the new one
		
	}