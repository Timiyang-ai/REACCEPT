@Test
	public void testSave() {		// TODO: Resolve lazy initialize exception
		// test update
		User currUser = getTestUser();
		currUser.setUserName("new name");
		currUser.setOwners(null);
		userController.save(currUser, currUser);
		String userJson = userController.getOne(currUser.getUserId()).getBody();
		User user = gson.fromJson(userJson, User.class);
		assertThat(userJson, containsString("new name"));
		assertThat(user.getPassword(), is(currUser.getPassword()));

		User admin = getAdminUser();
		User temp = new User("temp1", "temp1", "temp1", "temp@nhn.com", Role.USER);
		userController.save(admin, temp);
		temp = new User("temp2", "temp2", "temp2", "temp@nhn.com", Role.USER);
		userController.save(admin, temp);

		currUser.setFollowersStr("temp1, temp2");
		userController.save(currUser, currUser);
		userJson = userController.getOne(currUser.getUserId()).getBody();
		user = gson.fromJson(userJson, User.class);
		assertThat(user.getFollowers().size(), is(2));
		assertThat(user.getFollowers().get(0).getUserId(), is("temp1"));
	}