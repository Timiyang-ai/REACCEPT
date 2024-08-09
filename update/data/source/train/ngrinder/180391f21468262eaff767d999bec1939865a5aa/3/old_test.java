@Test
	public void testGetAll() {
		Pageable page = PageRequest.of(1, 10);

		ModelMap model = new ModelMap();
		userController.getAll(model, null, page, null);

		model.clear();
		userController.getAll(model, Role.ADMIN, page, null);

		model.clear();
		userController.getAll(model, null, page, "user");

	}