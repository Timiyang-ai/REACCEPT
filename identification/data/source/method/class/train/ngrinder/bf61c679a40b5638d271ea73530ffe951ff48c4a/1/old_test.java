@SuppressWarnings("unchecked")
	@Test
	public void testDelete() {
		ModelMap model = new ModelMap();
		// save new user for test
		saveTestUser("NewUserId1", "NewUserName1");
		saveTestUser("NewUserId2", "NewUserName2");
		saveTestUser("NewUserId3", "NewUserName3");

		Pageable page = new PageRequest(0, 10);

		// search
		userController.getAll(model, null, page, "NewUserName");
		PageImpl userList = (PageImpl<User>) model.get("users");
		assertThat(userList.getContent().size(), is(3));

		// test to delete one
		model.clear();
		userController.delete(model, "NewUserId1");
		model.clear();
		userController.getAll(model, Role.USER, page, "NewUserName");
		userList = (PageImpl<User>) model.get("users");
		assertThat(userList.getContent().size(), is(2));

		// test to delete more
		model.clear();
		userController.delete(model, "NewUserId2,NewUserId3");
		model.clear();
		userController.getAll(model, Role.USER, page, "NewUserName");
		userList = (PageImpl<User>) model.get("users");
		assertThat(userList.getContent().size(), is(0));
	}