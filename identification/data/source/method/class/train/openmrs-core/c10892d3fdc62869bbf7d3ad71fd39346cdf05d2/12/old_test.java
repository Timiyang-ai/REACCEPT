	@Test
	public void purgePerson_shouldDeletePersonFromTheDatabase() throws Exception {
		PersonService personService = Context.getPersonService();
		
		User user = Context.getAuthenticatedUser();
		Person person = new Person();
		person.setPersonCreator(user);
		person.setPersonDateCreated(new Date());
		person.setPersonChangedBy(user);
		person.setPersonDateChanged(new Date());
		person.setGender("F");
		Assert.assertNull(person.getId());
		person.addName(new PersonName("givenName", "middleName", "familyName"));
		person = personService.savePerson(person);
		Assert.assertNotNull(person.getId());
		
		personService.purgePerson(person);
		
		Person deletedPerson = personService.getPerson(person.getId());
		Assert.assertNull(deletedPerson);
	}