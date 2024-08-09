@Test
	@Category(Smoke.class)
	public void getUser() {
		GetUserResponse response = accountWebServiceClient.getUser(1L);
		assertEquals("admin", response.getUser().getLoginName());
	}