@Test
	@Category(Smoke.class)
	public void getUser() {
		GetUserResult response = accountWebServiceClient.getUser(1L);
		assertEquals("admin", response.getUser().getLoginName());
	}