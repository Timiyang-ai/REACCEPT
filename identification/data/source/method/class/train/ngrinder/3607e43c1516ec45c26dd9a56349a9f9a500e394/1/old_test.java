@Test
	public void testGetUserList() {
		Pageable page = new PageRequest(1, 10);

		ModelMap model = new ModelMap();
		userController.getUserList(model, null, page, null);

		model.clear();
		userController.getUserList(model, "ADMIN", page, null);

		model.clear();
		userController.getUserList(model, null, page, "user");

	}