@Test
	public void testSaveOrUpdateUserDetail() {
		//test update
		ModelMap model = new ModelMap();
		User currUser = getTestUser();
		currUser.setUserName("new name");
		userController.saveOrUpdateUserDetail(currUser, model, currUser,null);
		
		userController.getUserDetail(getTestUser(), model, currUser.getUserId());
		User user = (User)model.get("user");
		assertThat(user.getUserName(), is("new name"));
		assertThat(user.getPassword(), is(currUser.getPassword()));
	}