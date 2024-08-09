	@Test
	public void getPeople_shouldGetNoOneByNull() {
		List<Person> people = hibernatePersonDAO.getPeople(null, false);
		logPeople(people);
		
		Assert.assertEquals(0, people.size());
	}