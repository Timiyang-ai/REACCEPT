	@Test
	public void getPersonAttributeTypeByUuid_shouldFindObjectGivenValidUuid() throws Exception {
		String uuid = "b3b6d540-a32e-44c7-91b3-292d97667518";
		PersonAttributeType personAttributeType = Context.getPersonService().getPersonAttributeTypeByUuid(uuid);
		Assert.assertEquals(1, (int) personAttributeType.getPersonAttributeTypeId());
	}