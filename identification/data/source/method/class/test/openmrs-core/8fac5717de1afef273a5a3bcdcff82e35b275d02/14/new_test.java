	@Test
	public void getRelationshipType_shouldReturnRelationshipTypeWithTheGivenRelationshipTypeId() throws Exception {
		RelationshipType relationshipType = Context.getPersonService().getRelationshipType(1);
		Assert.assertNotNull(relationshipType);
		assertTrue("Expecting the return is of a relationship type", relationshipType.getClass().equals(
		    RelationshipType.class));
	}