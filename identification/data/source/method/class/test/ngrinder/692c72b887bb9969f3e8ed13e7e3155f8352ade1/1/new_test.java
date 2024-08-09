@Test
	public void testSaveOrUpdateUserDetail() {
		// test update
		ModelMap model = new ModelMap();
		User currUser = getTestUser();
		currUser.setUserName("new name");
		userController.save(currUser, currUser, model);
		userController.getOne(getTestUser(), model, currUser.getUserId());
		User user = (User) model.get("user");
		assertThat(user.getUserName(), is("new name"));
		assertThat(user.getPassword(), is(currUser.getPassword()));

		User admin = getAdminUser();
		User temp = new User("temp1", "temp1", "temp1", "temp@nhn.com", Role.USER);
		userController.save(admin, temp, model);
		temp = new User("temp2", "temp2", "temp2", "temp@nhn.com", Role.USER);
		userController.save(admin, temp, model);
		model.clear();
		currUser.setFollowersStr("temp1, temp2");
		userController.save(currUser, currUser, model);
		userController.getOne(getTestUser(), model, currUser.getUserId());
		user = (User) model.get("user");
		assertThat(user.getFollowers().size(), is(2));
		assertThat(user.getFollowers().get(0).getUserId(), is("temp1"));
	}