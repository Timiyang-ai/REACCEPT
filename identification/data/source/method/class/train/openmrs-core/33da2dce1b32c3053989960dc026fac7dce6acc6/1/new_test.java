	@Test
	public void changeHashedPassword_shouldChangeTheHashedPasswordForTheGivenUser() {
		User user = userService.getUser(1);
		String salt = Security.getRandomToken();
		String hash = Security.encodeString("new password" + salt);
		userService.changeHashedPassword(user, hash, salt);

		// TODO Review this a little further
		// This is the assert - checks to see if current user can use the new password
		userService.changePassword("new password", "Another new password1"); // try to change the password with the new one

	}