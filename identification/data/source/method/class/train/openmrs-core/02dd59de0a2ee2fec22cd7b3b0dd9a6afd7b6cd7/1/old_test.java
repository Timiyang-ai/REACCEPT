	@Test
	public void getName_shouldReturnPersonFullNameIfPersonIsNotNullOrNullOtherwise() {
		Provider provider = new Provider();
		
		Person person = new Person(1);
		person.addName(new PersonName("givenName", "middleName", "familyName"));
		provider.setPerson(person);
		Assert.assertEquals(person.getPersonName().getFullName(), provider.getName());
	}