@Test
	@SuppressWarnings("unchecked")
	public void testSave() {
		// test update
		User currUser = getTestUser();
		currUser.setUserName("new name");
		currUser.setOwners(null);
		userApiController.save(currUser, currUser);
		User user = userApiController.getOne(currUser.getUserId());
		assertThat(user.getUserName(), is("new name"));
		assertThat(user.getPassword(), is(currUser.getPassword()));

		User admin = getAdminUser();
		User temp = new User("temp1", "temp1", "temp1", "temp@nhn.com", Role.USER);
		userApiController.save(admin, temp);
		temp = new User("temp2", "temp2", "temp2", "temp@nhn.com", Role.USER);
		userApiController.save(admin, temp);

		currUser.setFollowersStr("temp1, temp2");
		userApiController.save(currUser, currUser);
		user = userApiController.getOne(currUser.getUserId());
		assertThat(user.getFollowers().size(), is(2));
		assertThat(user.getFollowers().get(0).getUserId(), is("temp1"));
	}