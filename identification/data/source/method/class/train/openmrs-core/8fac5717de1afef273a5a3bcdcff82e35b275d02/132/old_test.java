	@Test
	public void removeUserProperty_shouldRemoveUserPropertyForGivenUserAndKey() {
		executeDataSet(XML_FILENAME);

		User user = userService.getUser(5505);
		Assert.assertNotSame("", user.getUserProperty("some key"));
		
		userService.removeUserProperty(user, "some key");
		
		user = userService.getUser(5505);
		Assert.assertEquals("", user.getUserProperty("some key"));
	}