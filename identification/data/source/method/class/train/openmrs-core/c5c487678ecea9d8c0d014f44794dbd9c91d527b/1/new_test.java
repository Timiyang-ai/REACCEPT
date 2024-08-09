	@Test
	public void comparePersonsByName_shouldReturnNegativeIfPersonNameForPerson1ComesBeforeThatOfPerson2() {
		Person person1 = new Person();
		person1.addName(new PersonName("givenName", "middleName", "familyName"));
		Person person2 = new Person();
		person2.addName(new PersonName("givenName", "middleNamf", "familyName"));
		int actualValue = PersonByNameComparator.comparePersonsByName(person1, person2);
		Assert.assertTrue("Expected a negative value but it was: " + actualValue, actualValue < 0);
	}