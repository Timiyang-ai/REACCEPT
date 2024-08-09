@Test
	public void testGetUserList() {
		Pageable page = new PageRequest(1, 10);

		ModelMap model = new ModelMap();
		userController.getUsers(model, null, page, null);

		model.clear();
		userController.getUsers(model, Role.ADMIN, page, null);

		model.clear();
		userController.getUsers(model, null, page, "user");

	}