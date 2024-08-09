	@Test
	public void purgeRelationshipType_shouldDeleteRelationshipTypeFromTheDatabase() throws Exception {
		PersonService personService = Context.getPersonService();
		
		RelationshipType relationshipType = new RelationshipType();
		relationshipType.setDescription("Test relationship");
		relationshipType.setaIsToB("Sister");
		relationshipType.setbIsToA("Brother");
		relationshipType = personService.saveRelationshipType(relationshipType);
		assertNotNull(relationshipType.getId());
		
		personService.purgeRelationshipType(relationshipType);
		
		RelationshipType deletedRelationshipType = personService.getRelationshipType(relationshipType.getId());
		Assert.assertNull(deletedRelationshipType);
	}