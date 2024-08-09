	@Test
	public void getPersonAttribute_shouldReturnNullWhenGivenIdDoesNotExist() throws Exception {
		PersonAttribute personAttribute = Context.getPersonService().getPersonAttribute(10000);
		Assert.assertNull(personAttribute);
	}