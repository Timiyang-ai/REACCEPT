@Test
	public void getCountOfProviders_shouldFetchNumberOfProviderMatchingGivenQuery() throws Exception {
		assertEquals(1, service.getCountOfProviders("Hippo").intValue());
		Person person = Context.getPersonService().getPerson(502);
		Set<PersonName> names = person.getNames();
		for (Iterator<PersonName> iterator = names.iterator(); iterator.hasNext();) {
			PersonName name = (PersonName) iterator.next();
			name.setVoided(true);
			
		}
		PersonName personName = new PersonName("Hippot", "A", "B");
		personName.setPreferred(true);
		person.addName(personName);
		Context.getPersonService().savePerson(person);
		assertEquals(1, service.getCountOfProviders("Hippo").intValue());
	}