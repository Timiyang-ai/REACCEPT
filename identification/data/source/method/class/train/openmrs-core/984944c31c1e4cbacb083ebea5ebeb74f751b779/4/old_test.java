	@Test
	public void getRelationships_shouldFetchRelationshipsMatchingTheGivenFromPerson() throws Exception {
		PersonService personService = Context.getPersonService();
		
		Person firstPerson = personService.getPerson(502);
		List<Relationship> relationships = personService.getRelationships(firstPerson, null, null);
		Assert.assertNotNull(relationships);
		assertTrue("There should be relationship found given the from person", relationships.size() > 0);
	}