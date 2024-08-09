	@Test
	public void getRelationshipMap_shouldReturnEmptyMapWhenNoRelationshipHasTheMatchingRelationshipType() throws Exception {
		executeDataSet("org/openmrs/api/include/PersonServiceTest-createRetiredRelationship.xml");
		
		PersonService personService = Context.getPersonService();
		
		RelationshipType relationshipType = personService.getRelationshipType(15);
		Map<Person, List<Person>> relationshipMap = personService.getRelationshipMap(relationshipType);
		Assert.assertNotNull(relationshipMap);
		assertTrue("There should be no element in the map", relationshipMap.isEmpty());
	}