	@Test
	public void getPeople_shouldMatchSearchToFamilyName2() throws Exception {
		executeDataSet("org/openmrs/api/include/PersonServiceTest-extranames.xml");
		updateSearchIndex();
		
		List<Person> people = Context.getPersonService().getPeople("Johnson", false);
		Assert.assertEquals(3, people.size());
		assertTrue(TestUtil.containsId(people, 2));
		assertTrue(TestUtil.containsId(people, 4));
		assertTrue(TestUtil.containsId(people, 5));
	}