	@Test
	public void savePerson_shouldCreateNewObjectWhenPersonIdIsNull() throws Exception {
		User user = Context.getAuthenticatedUser();
		Person person = new Person();
		person.setPersonCreator(user);
		person.setPersonDateCreated(new Date());
		person.setPersonChangedBy(user);
		person.setPersonDateChanged(new Date());
		person.setGender("F");
		Assert.assertNull(person.getId());
		person.addName(new PersonName("givenName", "middleName", "familyName"));
		Person personSaved = Context.getPersonService().savePerson(person);
		Assert.assertNotNull(personSaved.getId());
	}