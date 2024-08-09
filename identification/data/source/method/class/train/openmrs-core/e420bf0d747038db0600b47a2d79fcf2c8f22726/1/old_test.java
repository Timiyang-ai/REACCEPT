@Test
	@Verifies(value = "should match on incorrectly hashed sha1 stored password", method = "changePassword(String,String)")
	public void changePassword_shouldMatchOnIncorrectlyHashedSha1StoredPassword() throws Exception {
		executeDataSet(XML_FILENAME);
		Context.logout();
		Context.authenticate("incorrectlyhashedSha1", "test");
		
		UserService us = Context.getUserService();
		us.changePassword("test", "test2");
		
		Context.logout(); // so that the next test reauthenticates
	}