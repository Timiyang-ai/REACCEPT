@Test
	@Category(Smoke.class)
	public void searchUser() {
		AccountWebService accountWebService = creatClient();

		UserListResponse result = accountWebService.searchUser(null, null);

		assertTrue(result.getUserList().size() >= 4);
		assertEquals("Jack", result.getUserList().get(0).getName());
	}