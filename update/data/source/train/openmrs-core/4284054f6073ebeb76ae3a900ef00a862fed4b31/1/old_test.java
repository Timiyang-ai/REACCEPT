@Test
	@Verifies(value = "return person full name if person is not null", method = "getName()")
	public void getName_shouldReturnPersonFullNameIfPersonIsNotNull() throws Exception {
		final String providerName = "Provider Name";
		
		Provider provider = new Provider();
		provider.setName(providerName);
		Assert.assertEquals(providerName, provider.getName());
		
		Person person = new Person(1);
		person.addName(new PersonName("givenName", "middleName", "familyName"));
		provider.setPerson(person);
		Assert.assertEquals(person.getPersonName().getFullName(), provider.getName());
	}