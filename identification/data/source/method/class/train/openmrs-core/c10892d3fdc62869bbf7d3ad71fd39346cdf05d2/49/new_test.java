	@Test(expected = APIException.class)
	public void retireRelationshipType_shouldFailIfGivenReasonIsNull() {
		
		personService.retireRelationshipType(new RelationshipType(), null);
	}