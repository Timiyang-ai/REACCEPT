	@Test
	public void getPersonAttributeByUuid_shouldFindObjectGivenValidUuid() throws Exception {
		String uuid = "0768f3da-b692-44b7-a33f-abf2c450474e";
		PersonAttribute person = Context.getPersonService().getPersonAttributeByUuid(uuid);
		Assert.assertEquals(1, (int) person.getPersonAttributeId());
	}