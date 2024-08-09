@SuppressWarnings("unchecked")
	@Test
	public void testDelete() {
		// save new user for test
		saveTestUser("NewUserId1", "NewUserName1");
		saveTestUser("NewUserId2", "NewUserName2");
		saveTestUser("NewUserId3", "NewUserName3");

		Pageable page = PageRequest.of(0, 10);

		// search
		Page<User> userList = userController.getAll(null, page, "NewUserName");
		assertThat(userList.getContent().size(), is(3));

		// test to delete one
		userController.delete(testUser, "NewUserId1");
		userList = userController.getAll(Role.USER, page, "NewUserName");
		assertThat(userList.getContent().size(), is(2));

		// test to delete more
		userController.deleteUsers(testUser, "NewUserId2,NewUserId3");
		userList = userController.getAll(Role.USER, page, "NewUserName");
		assertThat(userList.getContent().size(), is(0));
	}