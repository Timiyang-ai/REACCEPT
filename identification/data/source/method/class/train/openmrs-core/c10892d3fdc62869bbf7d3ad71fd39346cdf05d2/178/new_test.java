	@Test(expected = APIException.class)
	public void saveRelationship_shouldThrowAPIException() {
		Relationship relationship = new Relationship();
		Person person = new Person();
		relationship.setPersonA(person);
		relationship.setPersonB(person);
		
		personService.saveRelationship(relationship);
		
	}