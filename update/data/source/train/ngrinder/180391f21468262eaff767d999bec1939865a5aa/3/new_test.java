@Test
	public void testGetAll() {
		Pageable page = PageRequest.of(1, 10);

		ModelMap model = new ModelMap();
		userController.getAll(null, page, null);

		model.clear();
		userController.getAll(Role.ADMIN, page, null);

		model.clear();
		userController.getAll(null, page, "user");

	}