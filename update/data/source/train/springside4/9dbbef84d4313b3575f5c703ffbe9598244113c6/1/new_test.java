@Test
	public void getAllUser() {
		List<UserDTO> userList = client.getAllUser("admin", "admin");
		assertTrue(userList.size() >= 6);
		UserDTO admin = userList.iterator().next();
		assertEquals("admin", admin.getLoginName());
	}