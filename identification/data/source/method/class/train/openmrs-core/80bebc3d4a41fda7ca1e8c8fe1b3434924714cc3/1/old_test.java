@Test
	@Verifies(value = "should return positive if personName for person1 comes after that of person2", method = "compare(Person,Person)")
	public void compare_shouldReturnPositiveIfPersonNameForPerson1ComesAfterThatOfPerson2() throws Exception {
		Person person1 = new Person();
		person1.addName(new PersonName("givenName", "middleNamf", "familyName"));
		Person person2 = new Person();
		person2.addName(new PersonName("givenName", "middleName", "familyName"));
		int actualValue = new PersonByNameComparator().compare(person1, person2);
		Assert.assertTrue("Expected a positive value but it was: " + actualValue, actualValue > 0);
	}