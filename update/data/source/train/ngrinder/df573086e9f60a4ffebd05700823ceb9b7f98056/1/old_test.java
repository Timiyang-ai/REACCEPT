@Test
	public void testGetUserList() {
		ModelMap model = new ModelMap();
		userController.getUserList(model, null, null);

		model.clear();
		userController.getUserList(model, "ADMIN", null);

		model.clear();
		userController.getUserList(model, null, "user");

	}