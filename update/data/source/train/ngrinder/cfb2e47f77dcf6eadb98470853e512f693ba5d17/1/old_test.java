@Test
	public void testGetOne() {
		Map<String, Object> result = userApiController.getOne(getTestUser().getUserId());
		User user = (User) result.get("user");
		assertThat(user.getId(), is(getTestUser().getId()));
	}