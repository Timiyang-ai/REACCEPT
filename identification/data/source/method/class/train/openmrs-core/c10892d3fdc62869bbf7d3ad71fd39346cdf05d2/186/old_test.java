	@Test
	public void saveRelationshipType_shouldCreateNewObjectWhenRelationshipTypeIdIsNull() throws Exception {
		RelationshipType relationshipType = new RelationshipType();
		relationshipType.setDescription("Test relationship");
		relationshipType.setaIsToB("Sister");
		relationshipType.setbIsToA("Brother");
		Assert.assertNull(relationshipType.getRelationshipTypeId());
		RelationshipType savedRelationshipType = personService.saveRelationshipType(relationshipType);
		Assert.assertNotNull(savedRelationshipType.getRelationshipTypeId());
	}