	@Test
	public void getRelationshipTypeByName_shouldReturnNullWhenNoRelationshipTypeMatchTheGivenName() throws Exception {
		RelationshipType relationshipType = Context.getPersonService().getRelationshipTypeByName("Supervisor");
		Assert.assertNull(relationshipType);
	}