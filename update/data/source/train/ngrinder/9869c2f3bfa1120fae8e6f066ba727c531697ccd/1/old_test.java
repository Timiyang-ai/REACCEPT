@Test
	public void testGetOne() {
		ModelMap model = new ModelMap();
		userController.getOne(getTestUser().getUserId(), model);
		User user = (User) model.get("user");
		assertThat(user.getId(), is(getTestUser().getId()));
	}