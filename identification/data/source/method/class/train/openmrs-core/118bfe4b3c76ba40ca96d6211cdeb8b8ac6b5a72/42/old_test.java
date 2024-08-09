	@Test
	public void getPersonByUuid_shouldFindObjectGivenValidUuid() throws Exception {
		String uuid = "ba1b19c2-3ed6-4f63-b8c0-f762dc8d7562";
		Person person = Context.getPersonService().getPersonByUuid(uuid);
		Assert.assertEquals(1, (int) person.getPersonId());
	}