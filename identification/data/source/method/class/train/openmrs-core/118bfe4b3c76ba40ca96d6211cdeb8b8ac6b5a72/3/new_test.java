	@Test
	public void getRelationshipTypeByUuid_shouldFindObjectGivenValidUuid() throws Exception {
		String uuid = "6d9002ea-a96b-4889-af78-82d48c57a110";
		RelationshipType relationshipType = Context.getPersonService().getRelationshipTypeByUuid(uuid);
		Assert.assertEquals(1, (int) relationshipType.getRelationshipTypeId());
	}