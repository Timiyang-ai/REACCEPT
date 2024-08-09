	@Test
	public void getPersonAttributeTypeByName_shouldReturnPersonAttributeTypeWhenNameMatchesGivenTypeName() throws Exception {
		PersonAttributeType attributeType = Context.getPersonService().getPersonAttributeTypeByName("Birthplace");
		Assert.assertNotNull(attributeType);
	}