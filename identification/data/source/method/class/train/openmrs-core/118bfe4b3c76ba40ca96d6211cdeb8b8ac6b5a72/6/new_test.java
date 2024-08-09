	@Test
	public void getPersonNameByUuid_shouldFindObjectGivenValidUuid() throws Exception {
		String uuid = "399e3a7b-6482-487d-94ce-c07bb3ca3cc7";
		PersonName personName = Context.getPersonService().getPersonNameByUuid(uuid);
		Assert.assertEquals(2, (int) personName.getPersonNameId());
	}