@Test
	public void testGetUserDetail() {
		ModelMap model = new ModelMap();
		userController.getUserDetail(getTestUser(), model, getTestUser().getUserId());
		User user = (User) model.get("user");
		assertThat(user.getId(), is(getTestUser().getId()));
	}