	@Test
	public void getPersonAttributeType_shouldReturnNullWhenNoPersonAttributeWithTheGivenIdExist() throws Exception {
		PersonAttributeType attributeType = Context.getPersonService().getPersonAttributeType(10000);
		Assert.assertNull(attributeType);
	}