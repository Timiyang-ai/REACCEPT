@Test
	@SuppressWarnings("unchecked")
	public void testSave() {		// TODO: Resolve lazy initialize exception
		// test update
		User currUser = getTestUser();
		currUser.setUserName("new name");
		currUser.setOwners(null);
		userApiController.save(currUser, currUser);
		User user = (User) userApiController.getOneDetail(currUser.getUserId()).get("user");
		assertThat(user.getUserName(), is("new name"));
		assertThat(user.getPassword(), is(currUser.getPassword()));

		User admin = getAdminUser();
		User temp = new User("temp1", "temp1", "temp1", "temp@nhn.com", Role.USER);
		userApiController.save(admin, temp);
		temp = new User("temp2", "temp2", "temp2", "temp@nhn.com", Role.USER);
		userApiController.save(admin, temp);

		currUser.setFollowersStr("temp1, temp2");
		userApiController.save(currUser, currUser);
		List<UserApiController.UserSearchResult> followers = (List<UserApiController.UserSearchResult>) userApiController.getOneDetail(currUser.getUserId()).get("followers");
		assertThat(followers.size(), is(2));
		assertThat(followers.get(0).getId(), is("temp1"));
	}