	@Test
	public void validate_shouldBeValidIfIdentifierIsNotSet() {
		//given
		provider.setIdentifier(null);
		
		Person person = new Person();
		Set<PersonName> personNames = new HashSet<>(1);
		PersonName personName = new PersonName();
		personName.setFamilyName("name");
		personNames.add(personName);
		person.setNames(personNames);
		provider.setPerson(person);
		
		//when
		providerValidator.validate(provider, errors);
		
		//then
		Assert.assertFalse(errors.hasErrors());
	}