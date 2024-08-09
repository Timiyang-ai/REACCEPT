	@Test
	public void getRoleByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "3480cb6d-c291-46c8-8d3a-96dc33d199fb";
		Role role = userService.getRoleByUuid(uuid);
		Assert.assertEquals("Provider", role.getRole());
	}