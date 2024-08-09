@Test
	@Category(Smoke.class)
	public void getUser() {
		GetUserResult result = accountWebServiceClient.getUser(1L);
		assertEquals("admin", result.getUser().getLoginName());
	}