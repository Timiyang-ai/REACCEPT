@Test
	public void testGetOne() {
		User user = userApiController.getOne(getTestUser().getUserId());
		assertThat(user.getId(), is(getTestUser().getId()));
	}