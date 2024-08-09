	@Test
	public void refreshAuthenticatedUser_shouldGetFreshValuesFromTheDatabase() {
		User evictedUser = Context.getAuthenticatedUser();
		Context.evictFromSession(evictedUser);
		
		User fetchedUser = Context.getUserService().getUser(evictedUser.getUserId());
		fetchedUser.getPersonName().setGivenName("new username");
		
		Context.getUserService().saveUser(fetchedUser);
		
		// sanity check to make sure the cached object wasn't updated already
		Assert.assertNotSame(Context.getAuthenticatedUser().getGivenName(), fetchedUser.getGivenName());
		
		Context.refreshAuthenticatedUser();
		
		Assert.assertEquals("new username", Context.getAuthenticatedUser().getGivenName());
	}