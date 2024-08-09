@Test
	public void testGetUserDetail() {
		ModelMap model = new ModelMap();
		userController.getOne(getTestUser(), model, getTestUser().getUserId());
		User user = (User) model.get("user");
		assertThat(user.getId(), is(getTestUser().getId()));
	}