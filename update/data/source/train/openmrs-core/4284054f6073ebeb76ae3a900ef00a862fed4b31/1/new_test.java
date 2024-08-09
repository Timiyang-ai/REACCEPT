@Test
	@Verifies(value = "return person full name if person is not null or null otherwise", method = "getName()")
	public void getName_shouldReturnPersonFullNameIfPersonIsNotNullOrNullOtherwise() throws Exception {
		Provider provider = new Provider();
		
		Person person = new Person(1);
		person.addName(new PersonName("givenName", "middleName", "familyName"));
		provider.setPerson(person);
		Assert.assertEquals(person.getPersonName().getFullName(), provider.getName());
	}