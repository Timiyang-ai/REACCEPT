	@Test
	public void purgeRelationship_shouldDeleteRelationshipFromTheDatabase() throws Exception {
		PersonService personService = Context.getPersonService();
		
		Relationship relationship = personService.getRelationship(1);
		personService.purgeRelationship(relationship);
		
		Relationship deletedRelationship = personService.getRelationship(1);
		Assert.assertNull(deletedRelationship);
	}