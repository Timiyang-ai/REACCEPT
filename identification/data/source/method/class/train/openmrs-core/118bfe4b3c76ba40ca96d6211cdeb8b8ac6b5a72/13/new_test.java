	@Test
	public void getRelationshipTypes_shouldReturnEmptyListWhenNoRelationshipTypeMatchTheSearchString() throws Exception {
		List<RelationshipType> relationshipTypes = Context.getPersonService().getRelationshipTypes("Doctor");
		Assert.assertNotNull(relationshipTypes);
		assertTrue("There should be no relationship type for the given name", relationshipTypes.isEmpty());
	}