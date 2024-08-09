	@Test
	public void getRelationshipByUuid_shouldFindObjectGivenValidUuid() throws Exception {
		String uuid = "c18717dd-5d78-4a0e-84fc-ee62c5f0676a";
		Relationship relationship = Context.getPersonService().getRelationshipByUuid(uuid);
		Assert.assertEquals(1, (int) relationship.getRelationshipId());
	}