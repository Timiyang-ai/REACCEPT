	@Test
	public void getAllRelationshipTypes_shouldReturnAllRelationshipTypes() throws Exception {
		executeDataSet("org/openmrs/api/include/PersonServiceTest-createRetiredRelationship.xml");
		
		List<RelationshipType> relationshipTypes = Context.getPersonService().getAllRelationshipTypes();
		assertTrue("Number of relationship type are 6", relationshipTypes.size() == 6);
	}