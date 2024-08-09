	@Test
	public void setUserProperty_shouldAddPropertyWithGivenKeyAndValueWhenKeyDoesNotAlreadyExist() {
		executeDataSet(XML_FILENAME);

		User user = userService.getUser(5505);
		
		// Check that it doesn't already exist
		Assert.assertEquals(user.getUserProperty("some new key"), "");
		
		userService.setUserProperty(user, "some new key", "some new value");
		
		user = userService.getUser(5505);
		Assert.assertEquals("some new value", user.getUserProperty("some new key"));
	}