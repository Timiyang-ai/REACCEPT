@Test
	public void searchUser() {

		SearchUserResult response = accountWebServiceClient.searchUser(null, null);

		assertTrue(response.getUserList().size() >= 4);
		assertEquals("Admin", response.getUserList().get(0).getName());
	}