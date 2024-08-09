@Test
	public void getPeople_shouldGetOnePersonByRandomCaseAttribute() throws Exception {
		globalPropertiesTestHelper.setGlobalProperty(OpenmrsConstants.GLOBAL_PROPERTY_PERSON_ATTRIBUTE_SEARCH_MATCH_MODE,
		    OpenmrsConstants.GLOBAL_PROPERTY_PERSON_ATTRIBUTE_SEARCH_MATCH_ANYWHERE);
		Assert.assertTrue(personAttributeHelper.personAttributeExists("Story teller"));
		
		List<Person> people = hibernatePersonDAO.getPeople("sToRy TeLlEr", false);
		logPeople(people);
		
		Assert.assertEquals(1, people.size());
		Assert.assertEquals("Bilbo Odilon", people.get(0).getGivenName());
	}